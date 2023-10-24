package com.techlead.desafioapi.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.techlead.desafioapi.entity.Emprestimo;
import com.techlead.desafioapi.entity.Livros;
import com.techlead.desafioapi.entity.Usuario;
import com.techlead.desafioapi.exceptions.DesafioException;
import com.techlead.desafioapi.repository.EmprestimoRepository;
import com.techlead.desafioapi.repository.LivrosRepository;
import com.techlead.desafioapi.rest.dto.request.DevolucaoLivroRequestDTO;
import com.techlead.desafioapi.rest.dto.request.SolicitarEmprestimoRequestDTO;
import com.techlead.desafioapi.rest.dto.response.EmprestimoResponseDTO;
@Service
public class EmprestimoSrv {
    
    @Autowired
    EmprestimoRepository repository;

    @Autowired
    LivrosSrv livrosSrv;

    @Autowired
    LivrosRepository livrosRepository;

    @Autowired
    UsuarioSrv usuarioSrv;
    public Livros disponibilidadeLivro (Long idLivro){
        Livros livro = livrosSrv.getById(idLivro);
        if(livro.getLivroDisponivel().equals(false) || livro.getQuantidadeEstoque()<=0){
            throw new DesafioException("Livro indiponível para empréstimo!");
        }
        return livro;
    }

    public EmprestimoResponseDTO solicitarEmprestimo(SolicitarEmprestimoRequestDTO dto, String token){
        Livros livro = this.disponibilidadeLivro(dto.getIdLivro());

        Usuario usuario = livrosSrv.getUsuarioByToken(token);
        if(usuario.getDiasPenalidade()!= null){
            throw new DesafioException("Usuário está impossibilitado de fazer novos empréstimos!");
        }

        Emprestimo emprestimo = new Emprestimo(dto.getDiasEmprestados());
        if(emprestimo.getEmprestimoAtivo().equals(true)){
            throw new DesafioException("Empréstimo já executado para esse livro");
        }
        emprestimo.setLivro(livro);
        emprestimo.setUsuario(usuario);
        repository.save(emprestimo);
        return EmprestimoResponseDTO.converterEmprestimoDTO(emprestimo);
    }

    public List<EmprestimoResponseDTO> listaEmprestimo() {
        List<Emprestimo> listaEmprestimos = repository.findByDevolvidoLivroOrderByEmprestimoAtivoAscDataDesc(false);

        return listaEmprestimos.stream().map(emprestimo -> new EmprestimoResponseDTO(emprestimo)).collect(Collectors.toList());
    }

    public void aceitarSolitacoes (Long id){
        Emprestimo emprestimo = this.findById(id);
        Livros livro = livrosSrv.getById(emprestimo.getLivro().getIdLivros());
        livro.setQuantidadeEstoque(0);
        livro.setLivroDisponivel(true);
        livrosRepository.save(livro);

        emprestimo.setEmprestimoAtivo(true);
        emprestimo.setData(LocalDateTime.now());
        repository.save(emprestimo);
    }

    public Emprestimo findById(Long id){
        return repository.findById(id).orElseThrow(() -> new DesafioException("Empréstimo não encontrado."));
    }

    public void validarDataAtraso(Emprestimo emprestimo){
        LocalDate dataDevolucaoEsperada = emprestimo.getData().plusDays(emprestimo.getDiasEmprestados()).toLocalDate();
        LocalDate dataDevolucaoDoLivro = LocalDate.now();
        
        if (dataDevolucaoDoLivro.isAfter(dataDevolucaoEsperada)) {
            long daysBetween = ChronoUnit.DAYS.between(dataDevolucaoEsperada, dataDevolucaoDoLivro);

            if(daysBetween<= 10){
                usuarioSrv.penalizarUsuario(emprestimo.getUsuario(), 2);
            } else if(daysBetween>10 && daysBetween<=20){
                usuarioSrv.penalizarUsuario(emprestimo.getUsuario(), 7);
            }else if(daysBetween>20 && daysBetween<=30){
                usuarioSrv.penalizarUsuario(emprestimo.getUsuario(), 14);
            }else if(daysBetween>30 && daysBetween<=40){
                usuarioSrv.penalizarUsuario(emprestimo.getUsuario(), 21);
            }else{
                usuarioSrv.penalizarUsuario(emprestimo.getUsuario(), 28);
            }
        }
    }
    public void validaperdaDanos(Boolean perdaDano, Emprestimo emprestimo){
        if(perdaDano.equals(true)){
            usuarioSrv.penalizarUsuario(emprestimo.getUsuario(), 28);
            usuarioSrv.bloqueaUsuario(emprestimo.getUsuario());
        }
    }
    public void devolucaoEmprestimo(Long id,DevolucaoLivroRequestDTO dto){
        Emprestimo emprestimo = this.findById(id);
        this.validarDataAtraso(emprestimo);
        this.validaperdaDanos(dto.getPerdaDano(), emprestimo);
        emprestimo.setDevolvidoLivro(true);
        livrosSrv.retornaEstoque(emprestimo.getLivro().getIdLivros());
        repository.save(emprestimo);
    }
}
