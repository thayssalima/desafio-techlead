package com.techlead.desafioapi.rest.dto.request;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "DTO para devolução de livro")
public class DevolucaoLivroRequestDTO {
	private Boolean perdaDano;
}
