package com.algaworks.algafood.domain.exceptions;

import lombok.Getter;

@Getter
public class StatusException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public StatusException(String message){
        super(message);
    }
}
