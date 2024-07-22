package com.algaworks.algafood.domain.exceptions;

import lombok.Getter;

@Getter
public class EntidadeNaoEncontradaException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public EntidadeNaoEncontradaException(String message){
        super(message);
    }
}
