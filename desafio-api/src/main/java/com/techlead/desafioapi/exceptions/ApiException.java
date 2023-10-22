package com.techlead.desafioapi.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;   
@EqualsAndHashCode(callSuper = true)
@Getter
@Data
public class ApiException extends RuntimeException{
    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    protected ApiException(String message, Throwable cause,
                            boolean enableSuppression,
                            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
