package com.techlead.desafioapi.exceptions;

public class DesafioException extends RuntimeException{
    private static final long serialVersionUID = -7080805033510550218L;

    public DesafioException(String exceptionMessage) {
        super(exceptionMessage);
    }
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
