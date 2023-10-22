package com.techlead.desafioapi.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import com.techlead.desafioapi.exceptions.DesafioException;
import com.techlead.desafioapi.exceptions.FalhaNaAutenticacaoException;
import com.techlead.desafioapi.rest.dto.response.ErrorDTO;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerConfig {
    @ExceptionHandler(DesafioException.class)
    protected ResponseEntity<ErrorDTO> handleDesafioException(DesafioException ex) {

        ErrorDTO defaultErrorDTO = this.buildDefaultErrorRet(ex.getMessage(), ex);

        return new ResponseEntity<>(defaultErrorDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FalhaNaAutenticacaoException.class)
    protected ResponseEntity<ErrorDTO> handleDesafioException(FalhaNaAutenticacaoException ex) {

        ErrorDTO defaultErrorDTO = this.buildDefaultErrorRet(ex.getMessage(), ex);

        return new ResponseEntity<>(defaultErrorDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    protected ErrorDTO buildDefaultErrorRet(String mensagem, Exception exception) {
        ErrorDTO defaultErrorDTO = new ErrorDTO();
        log.error("Error: {}", mensagem);
        defaultErrorDTO.setMessage(mensagem);
        defaultErrorDTO.setException(exception.getClass().getSimpleName());
        return defaultErrorDTO;
    }
}
