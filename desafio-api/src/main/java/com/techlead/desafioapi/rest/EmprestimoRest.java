package com.techlead.desafioapi.rest;

import org.springframework.web.bind.annotation.RestController;
import com.techlead.desafioapi.rest.dto.request.SolicitarEmprestimoRequestDTO;
import com.techlead.desafioapi.rest.dto.response.SolicitarEmprestimoResponseDTO;
import com.techlead.desafioapi.service.EmprestimoSrv;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoRest {
    @Autowired
    EmprestimoSrv emprestimoSrv;

    @ApiOperation(value = "Solicitar empréstimo")
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<SolicitarEmprestimoResponseDTO> solicitarEmprestimo(@RequestHeader(value = "Authorization") String token,
                                                        @RequestBody @Valid SolicitarEmprestimoRequestDTO dto) {

        SolicitarEmprestimoResponseDTO response = emprestimoSrv.solicitarEmprestimo(dto,token);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
