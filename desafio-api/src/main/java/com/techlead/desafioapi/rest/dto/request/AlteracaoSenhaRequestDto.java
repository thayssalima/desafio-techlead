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
@ApiModel(description = "DTO para a alteração de senha")
public class AlteracaoSenhaRequestDto {
    @NotNull(message = "O campo Senha Atual precisa ser preenchido")
	private String senhaAtual;

    @NotNull(message = "O campo Nova Senha precisa ser preenchido")
	private String novaSenha;
}
