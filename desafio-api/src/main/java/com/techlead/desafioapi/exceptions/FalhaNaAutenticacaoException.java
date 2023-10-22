package com.techlead.desafioapi.exceptions;

public class FalhaNaAutenticacaoException extends RuntimeException{
        public FalhaNaAutenticacaoException() {
        super("Usuário ou senha inválidos");
    }
}
