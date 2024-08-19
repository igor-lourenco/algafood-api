package com.algaworks.algafood.domain.exceptions;

public class EntidadeComIdException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public EntidadeComIdException(String msg) {
        super(msg);
    }
}
