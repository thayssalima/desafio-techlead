package com.techlead.desafioapi.rest.dto.response;

import org.springframework.beans.BeanUtils;
import com.techlead.desafioapi.entity.Usuario;
import com.techlead.desafioapi.entity.enums.PerfilEnum;
import io.swagger.annotations.ApiModel;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("DTO de resposta de usuário")
public class UsuarioRespondeDTO {
    private String nome;
    private String cpf;
    private String email;
    private PerfilEnum perfilEnum;

    public static UsuarioRespondeDTO converterUsuarioDTO(Usuario usuario) {
        UsuarioRespondeDTO usuarioRespondeDTO =new UsuarioRespondeDTO();
        BeanUtils.copyProperties(usuario,usuarioRespondeDTO);
        return usuarioRespondeDTO;
    }
}
