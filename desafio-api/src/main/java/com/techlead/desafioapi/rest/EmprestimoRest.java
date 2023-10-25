package com.techlead.desafioapi.rest;

import org.springframework.web.bind.annotation.RestController;
import com.techlead.desafioapi.rest.dto.request.DevolucaoLivroRequestDTO;
import com.techlead.desafioapi.rest.dto.request.SolicitarEmprestimoRequestDTO;
import com.techlead.desafioapi.rest.dto.response.EmprestimoResponseDTO;
import com.techlead.desafioapi.rest.dto.response.RetornoDTO;
import com.techlead.desafioapi.service.EmprestimoSrv;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<EmprestimoResponseDTO> solicitarEmprestimo(@RequestHeader(value = "Authorization") String token,
                                                        @RequestBody @Valid SolicitarEmprestimoRequestDTO dto) {

        EmprestimoResponseDTO response = emprestimoSrv.solicitarEmprestimo(dto,token);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

    @ApiOperation(value = "Lista de empréstimos ")
    @GetMapping("/lista")
    public List<EmprestimoResponseDTO> listar(){
        return emprestimoSrv.listaEmprestimo();
    }

    @ApiOperation(value = "Aceitar solicitações do cliente")
    @PutMapping(value = "/{id}")
    public RetornoDTO aceitarSolitacoes(@PathVariable Long id) {
    
        emprestimoSrv.aceitarSolitacoes(id);

        return new RetornoDTO("Solicitação aceita!");
    }

    @ApiOperation(value = "Devoluções de livros")
    @PutMapping(value = "/devolucao/{id}")
    public RetornoDTO devolucaoEmprestimo(@PathVariable Long id, @RequestBody DevolucaoLivroRequestDTO dto) {
    
        emprestimoSrv.devolucaoEmprestimo(id, dto);

        return new RetornoDTO("Livro devolvido à biblioteca!");
    }

    @ApiOperation(value = "Desaprova a solicitação de um empréstimo de livro")
    @PutMapping(value = "/desaprova/{id}")
    public RetornoDTO desaprovaSolicitacao(@PathVariable Long id) {
    
        emprestimoSrv.desaprovaSolicitacao(id);

        return new RetornoDTO("Solicitação de empréstimo negada!");
    }
}
