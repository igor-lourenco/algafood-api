package com.algaworks.algafood.domain.exceptions;

import lombok.Getter;

@Getter
public class FiltroException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public FiltroException(String message){
        super(message);
    }
}
