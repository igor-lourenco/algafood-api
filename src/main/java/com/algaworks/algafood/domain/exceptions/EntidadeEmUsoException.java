package com.algaworks.algafood.domain.exceptions;

import lombok.Getter;

@Getter
public class EntidadeEmUsoException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public EntidadeEmUsoException(String message){
        super(message);
    }
}
