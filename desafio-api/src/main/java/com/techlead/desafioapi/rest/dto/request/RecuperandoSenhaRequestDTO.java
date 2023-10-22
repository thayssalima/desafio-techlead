package com.techlead.desafioapi.rest.dto.request;

import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "DTO de requisição para recuperação de senha do usuário")
public class RecuperandoSenhaRequestDTO {
    @NotNull(message = "O campo Cpf precisa ser preenchido")
	private String cpf;

    @NotNull(message = "O campo Nova Senha precisa ser preenchido")
	private String novaSenha;
}
