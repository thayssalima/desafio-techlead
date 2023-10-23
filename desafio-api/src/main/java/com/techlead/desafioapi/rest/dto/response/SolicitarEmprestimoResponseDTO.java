package com.techlead.desafioapi.rest.dto.response;

import java.time.LocalDateTime;
import org.springframework.beans.BeanUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.techlead.desafioapi.entity.Emprestimo;
import io.swagger.annotations.ApiModel;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("DTO de resposta de livros")
public class SolicitarEmprestimoResponseDTO {
    private Long idEmprestimo;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime data;
    private Integer diasEmprestados;
    private Boolean emprestimoAtivo;
    private String nomeUsuario;
    private String nomeLivro;

    public static SolicitarEmprestimoResponseDTO converterEmprestimoDTO(Emprestimo emprestimo) {
        SolicitarEmprestimoResponseDTO solitarEmprestimoResponseDTO =new SolicitarEmprestimoResponseDTO();
        BeanUtils.copyProperties(emprestimo,solitarEmprestimoResponseDTO);
        solitarEmprestimoResponseDTO.setNomeUsuario(emprestimo.getUsuario().getNome());
        solitarEmprestimoResponseDTO.setNomeLivro(emprestimo.getLivro().getNome());
        return solitarEmprestimoResponseDTO;
    }
}
