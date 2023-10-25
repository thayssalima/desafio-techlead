package com.techlead.desafioapi.rest;

import org.springframework.web.bind.annotation.RestController;
import com.techlead.desafioapi.rest.dto.request.LivrosRequestDTO;
import com.techlead.desafioapi.rest.dto.response.LivroResponseDTO;
import com.techlead.desafioapi.rest.dto.response.RetornoDTO;
import com.techlead.desafioapi.service.LivrosSrv;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/livros")
public class LivrosRest {
    @Autowired
    LivrosSrv livrosService;

    @ApiOperation(value = "Cadastro de livros")
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<LivroResponseDTO> cadastrar(@RequestHeader(value = "Authorization") String token,
                                                        @RequestBody @Valid LivrosRequestDTO dto) {

        LivroResponseDTO response = livrosService.cadastrar(dto,token);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
    @ApiOperation(value = "Edição de livros")
    @PutMapping(value = "/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<LivroResponseDTO> editar(@RequestHeader(value = "Authorization") String token,
                                                    @PathVariable Long id ,@RequestBody @Valid LivrosRequestDTO dto) {
    
        LivroResponseDTO response = livrosService.editar(dto,id, token);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @ApiOperation(value = "Deletar")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("isAuthenticated()")
    public RetornoDTO excluir(@RequestHeader(value = "Authorization") String token, @PathVariable Long id) {
        livrosService.deletar(id, token);
        return new RetornoDTO("Livro excluído com sucesso!");
    }
    @ApiOperation(value = "Busca de livro pelo id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<LivroResponseDTO> findById (
            @PathVariable Long id) {
        return ResponseEntity.ok(livrosService.findById(id));
    }
    @ApiOperation(value = "Lista dos livros ")
    @GetMapping("/lista")
    @PreAuthorize("isAuthenticated()")
    public List<LivroResponseDTO> listar(@RequestHeader(value = "Authorization") String token){
        return livrosService.listaLivros(token);
    }
}
