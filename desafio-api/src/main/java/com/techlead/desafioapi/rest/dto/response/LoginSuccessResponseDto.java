package com.techlead.desafioapi.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.techlead.desafioapi.entity.enums.PerfilEnum;
import lombok.Builder;
import lombok.Data;
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
public class LoginSuccessResponseDto {
    private String cpf;
    private String token;
    private  PerfilEnum perfil;
    private String senha;
    private String email;
    private String nome;
    private Long idUsuario;
}
