package com.algaworks.algafood.domain.exceptions;

public class UsuarioNaoAutenticadoException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public UsuarioNaoAutenticadoException(String message) {
        super(message);
    }
}
