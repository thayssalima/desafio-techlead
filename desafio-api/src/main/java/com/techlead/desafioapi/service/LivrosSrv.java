package com.techlead.desafioapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.techlead.desafioapi.entity.Livros;
import com.techlead.desafioapi.entity.Usuario;
import com.techlead.desafioapi.entity.enums.PerfilEnum;
import com.techlead.desafioapi.exceptions.DesafioException;
import com.techlead.desafioapi.repository.LivrosRepository;
import com.techlead.desafioapi.rest.dto.request.LivrosRequestDTO;
import com.techlead.desafioapi.rest.dto.response.LivroResponseDTO;
@Service
public class LivrosSrv {
    
    @Autowired
    LivrosRepository repository;

    @Autowired
    UsuarioSrv usuarioSrv;

    public LivroResponseDTO cadastrar(LivrosRequestDTO dto, String token){
        Optional<Livros> livrosOpt = repository.findByNomeAndAutor(dto.getNome(), dto.getAutor());
        if(livrosOpt.isPresent()){
            throw new DesafioException("Livro com o mesmo nome e autor já está cadastrado");
        }

        Usuario usuario = this.getUsuarioByToken(token);

        Livros livros = new Livros();
        BeanUtils.copyProperties(dto, livros);
        livros.setUsuario(usuario);
        livros.setLivroDisponivel(true);
        livros.setQuantidadeEstoque(1);
        livros.setNome(dto.getNome().substring(0,1).toUpperCase().concat(dto.getNome().substring(1)));
        livros.setAutor(dto.getAutor().substring(0,1).toUpperCase().concat(dto.getAutor().substring(1)));
        repository.save(livros);
        return LivroResponseDTO.converterLivrosDTO(livros);
    }

    public LivroResponseDTO editar (LivrosRequestDTO dto, Long id, String token){
        Optional<Livros> livrosOpt = repository.findById(id);
        if(!livrosOpt.isPresent()){
            throw new DesafioException("Não foi possível encontrar este livro!");
        }

        Usuario usuario = this.getUsuarioByToken(token);

        try{
            if(livrosOpt.get().getUsuario().equals(usuario) || usuario.getPerfil().equals(PerfilEnum.ADMINISTRADOR)){
                BeanUtils.copyProperties(dto, livrosOpt.get(),"id");
                return LivroResponseDTO.converterLivrosDTO(repository.save(livrosOpt.get()));
            }
            throw new DesafioException( "Para a edição precisa ser o administrador/criador do cadastro.");
        }catch(Exception e){
            throw new DesafioException( "Erro ao editar! Verificar com o administrador do sistema.");
        }
    }

    public void deletar(Long id, String token) {
        Usuario usuario = this.getUsuarioByToken(token);
        Optional<Livros> livrosOpt = repository.findById(id);

        
        if(livrosOpt.get().getUsuario().equals(usuario) || usuario.getPerfil().equals(PerfilEnum.ADMINISTRADOR)){
            repository.deleteById(id);
        }else{
            throw new DesafioException( "Para a edição precisa ser o administrador/criador do cadastro.");
        }
    }

    public LivroResponseDTO findById(Long id){
        Livros livros = this.getById(id);
        return LivroResponseDTO.converterLivrosDTO(livros);
    }

    public List<LivroResponseDTO> listaLivros(String token) {
        Usuario usuario = this.getUsuarioByToken(token);

        List<Livros> listaLivros = repository.findByLivroDisponivelOrderByNome(false);

        return listaLivros.stream().map(livro -> new LivroResponseDTO(livro, usuario)).collect(Collectors.toList());
    }

    public Usuario getUsuarioByToken(String token){
        String cpfUsuario = usuarioSrv.lerToken(token).getSubject();
        return usuarioSrv.getByCpf(cpfUsuario);
    }

    public Livros getById(Long id){
        return repository.findById(id).orElseThrow(() -> new DesafioException("Livro não encontrado."));
    }

    public void retornaEstoque(Long id){
        Livros livro = this.getById(id);
        livro.setQuantidadeEstoque(1);
        livro.setLivroDisponivel(true);
        repository.save(livro);
    }
}
