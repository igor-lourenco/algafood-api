package com.algaworks.algafood.domain.exceptions;

import lombok.Getter;

@Getter
public class SenhaInvalidaException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public SenhaInvalidaException(String message){
        super(message);
    }
}
