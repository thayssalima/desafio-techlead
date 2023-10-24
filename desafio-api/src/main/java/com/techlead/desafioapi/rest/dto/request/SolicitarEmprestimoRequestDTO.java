package com.techlead.desafioapi.rest.dto.request;

import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ApiModel("DTO de requisição de solicitação de empréstimo de livro")
public class SolicitarEmprestimoRequestDTO {
    @NotNull(message = "Informe o campo Id. ")
    private Long idLivro;

    @NotNull(message = "Informe o campo Dias Emprestados. ")
    private Integer diasEmprestados;
}
