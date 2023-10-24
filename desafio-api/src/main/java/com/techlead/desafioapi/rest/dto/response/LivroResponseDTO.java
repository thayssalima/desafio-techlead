package com.techlead.desafioapi.rest.dto.response;

import java.time.LocalDate;
import org.springframework.beans.BeanUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.techlead.desafioapi.entity.Livros;
import com.techlead.desafioapi.entity.Usuario;
import com.techlead.desafioapi.entity.enums.PerfilEnum;
import io.swagger.annotations.ApiModel;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("DTO de resposta de livros")
public class LivroResponseDTO {
    private Long id;
    private String nome;
    private String autor;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataCadastro;
    private Boolean usuarioCadastrou;
    private Boolean usuarioBibliotecario;
    private Boolean livroDisponivel;

    public static LivroResponseDTO converterLivrosDTO(Livros livros) {
        LivroResponseDTO livroResponseDTO =new LivroResponseDTO();
        BeanUtils.copyProperties(livros,livroResponseDTO);
        livroResponseDTO.setId(livros.getIdLivros());
        return livroResponseDTO;
    }
    
    public LivroResponseDTO (Livros livros, Usuario usuario) {
        BeanUtils.copyProperties(livros,this);
        this.setId(livros.getIdLivros());
        if(livros.getUsuario().equals(usuario) || usuario.getPerfil().equals(PerfilEnum.ADMINISTRADOR)){
            this.setUsuarioCadastrou(true);
        }else{
            this.setUsuarioCadastrou(false);
        }
        if(usuario.getPerfil().equals(PerfilEnum.BIBLIOTECARIO)){
            this.setUsuarioBibliotecario(true);
        }else{
            this.setUsuarioBibliotecario(false);
        }
    }   
}
