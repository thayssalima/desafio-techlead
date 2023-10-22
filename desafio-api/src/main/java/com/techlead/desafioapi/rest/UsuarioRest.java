package com.techlead.desafioapi.rest;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.techlead.desafioapi.rest.dto.request.AlteracaoSenhaRequestDto;
import com.techlead.desafioapi.rest.dto.request.RecuperandoSenhaRequestDTO;
import com.techlead.desafioapi.rest.dto.request.UsuarioRequestDTO;
import com.techlead.desafioapi.rest.dto.response.RetornoDTO;
import com.techlead.desafioapi.rest.dto.response.UsuarioRespondeDTO;
import com.techlead.desafioapi.service.UsuarioSrv;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/usuario")
public class UsuarioRest {
    @Autowired
    UsuarioSrv usuarioService;

    @ApiOperation(value = "Cadastro de usuário do tipo cliente")
    @PostMapping
    public ResponseEntity<UsuarioRespondeDTO> cadastrarClientes(@RequestBody @Valid UsuarioRequestDTO dto) {

        UsuarioRespondeDTO response = usuarioService.cadastrarClientes(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
    @ApiOperation(value = "Alterar a senha do usuário")
    @PutMapping("/alterar_senha")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    public RetornoDTO alterarSenha(@RequestHeader(value = "Authorization") String token,
                                    @RequestBody AlteracaoSenhaRequestDto senha) {
        usuarioService.alterarSenha(senha,token);
        return new RetornoDTO("Senha alterada com sucesso");
    }
    @ApiOperation(value = "Recuperando a senha do usuário")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public RetornoDTO recuperandoSenha(@RequestBody RecuperandoSenhaRequestDTO dto) {
        usuarioService.recuperandoSenha(dto);
        return new RetornoDTO("Senha alterada com sucesso");
    }
}
