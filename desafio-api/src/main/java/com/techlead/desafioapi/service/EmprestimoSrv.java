package com.techlead.desafioapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.techlead.desafioapi.entity.Emprestimo;
import com.techlead.desafioapi.entity.Livros;
import com.techlead.desafioapi.entity.Usuario;
import com.techlead.desafioapi.exceptions.DesafioException;
import com.techlead.desafioapi.repository.EmprestimoRepository;
import com.techlead.desafioapi.rest.dto.request.SolicitarEmprestimoRequestDTO;
import com.techlead.desafioapi.rest.dto.response.SolicitarEmprestimoResponseDTO;
@Service
public class EmprestimoSrv {
    
    @Autowired
    EmprestimoRepository repository;

    @Autowired
    LivrosSrv livrosSrv;

    public Livros disponibilidadeLivro (Long idLivro){
        Livros livro = livrosSrv.getById(idLivro);
        if(livro.getLivroDisponivel().equals(false) && livro.getQuantidadeEstoque()>0){
            throw new DesafioException("Livro indiponível para empréstimo!");
        }
        return livro;
    }

    public SolicitarEmprestimoResponseDTO solicitarEmprestimo(SolicitarEmprestimoRequestDTO dto, String token){
        Livros livro = this.disponibilidadeLivro(dto.getIdLivro());

        Usuario usuario = livrosSrv.getUsuarioByToken(token);
        Emprestimo emprestimo = new Emprestimo(dto.getDiasEmprestados());
        emprestimo.setLivro(livro);
        emprestimo.setUsuario(usuario);
        repository.save(emprestimo);
        return SolicitarEmprestimoResponseDTO.converterEmprestimoDTO(emprestimo);
    }
}
