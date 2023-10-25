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
@ApiModel("DTO de resposta de empréstimo")
public class EmprestimoResponseDTO {
    private Long idEmprestimo;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime data;
    private Integer diasEmprestados;
    private Boolean emprestimoAtivoAceite;
    private String nomeUsuario;
    private String nomeLivro;
    private String statusEmprestimo;
    private Boolean emprestimoAtivo;

    public static EmprestimoResponseDTO converterEmprestimoDTO(Emprestimo emprestimo) {
        EmprestimoResponseDTO solitarEmprestimoResponseDTO =new EmprestimoResponseDTO();
        BeanUtils.copyProperties(emprestimo,solitarEmprestimoResponseDTO);
        solitarEmprestimoResponseDTO.setNomeUsuario(emprestimo.getUsuario().getNome());
        solitarEmprestimoResponseDTO.setNomeLivro(emprestimo.getLivro().getNome());
        if(emprestimo.getEmprestimoAtivo().equals(true)){
            solitarEmprestimoResponseDTO.setStatusEmprestimo("Em andamento");
        }else{
            solitarEmprestimoResponseDTO.setStatusEmprestimo("Iniciar");
        }
        if(emprestimo.getEmprestimoAtivo().equals(false)){
            solitarEmprestimoResponseDTO.setEmprestimoAtivoAceite(true);
        }else{
            solitarEmprestimoResponseDTO.setEmprestimoAtivoAceite(false);
        }
        return solitarEmprestimoResponseDTO;
    }

    public EmprestimoResponseDTO (Emprestimo emprestimo) {
        BeanUtils.copyProperties(emprestimo,this);
        this.setNomeUsuario(emprestimo.getUsuario().getNome());
        this.setNomeLivro(emprestimo.getLivro().getNome());
        if(emprestimo.getEmprestimoAtivo().equals(true)){
            this.setStatusEmprestimo("Em andamento");
        }else{
            this.setStatusEmprestimo("Iniciar");
        }
        if(emprestimo.getEmprestimoAtivo().equals(false)){
            this.setEmprestimoAtivoAceite(true);
        }else{
            this.setEmprestimoAtivoAceite(false);
        }
    }  
}
