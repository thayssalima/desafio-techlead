package com.techlead.desafioapi.rest.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import com.techlead.desafioapi.entity.enums.PerfilEnum;
import io.swagger.annotations.ApiModel;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ApiModel("DTO de requisição de usuário")
public class UsuarioRequestDTO {

    @NotEmpty(message = "Informe o campo Nome. ")
    @Size(min=1 ,max = 100,message = "O campo Nome terá no mínimo {min} e no máximo {max} caracteres")
    private String nome;

    @NotEmpty(message = "Informe o campo Cpf. ")
    @Size(min=11 ,message = "O campo Cpf terá no mínimo {min} caracteres")
    private String cpf;

    @NotEmpty(message = "Informe o campo Senha. ")
    @Size(min=1 ,max = 100,message = "O campo Senha terá no mínimo {min} e no máximo {max} caracteres")
    private String senha;

    @NotEmpty(message = "Informe o campo Email. ")
    @Size(min=1 ,max = 100,message = "O campo Email terá no mínimo {min} e no máximo {max} caracteres")
    private String email;

    private PerfilEnum perfilEnum;
}
