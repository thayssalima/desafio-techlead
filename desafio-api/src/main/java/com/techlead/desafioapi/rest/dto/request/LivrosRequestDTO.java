package com.techlead.desafioapi.rest.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import io.swagger.annotations.ApiModel;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ApiModel("DTO de requisição de livros")
public class LivrosRequestDTO {

    @NotEmpty(message = "Informe o campo Nome. ")
    @Size(min=1 ,max = 100,message = "O campo Nome terá no mínimo {min} e no máximo {max} caracteres")
    private String nome;

    @NotEmpty(message = "Informe o campo Autor. ")
    @Size(min=1 ,max = 100,message = "O campo Autor terá no mínimo {min} e no máximo {max} caracteres")
    private String autor;
}
