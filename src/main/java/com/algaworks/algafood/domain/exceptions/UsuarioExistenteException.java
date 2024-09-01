package com.algaworks.algafood.domain.exceptions;

import lombok.Getter;

@Getter
public class UsuarioExistenteException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public UsuarioExistenteException(String message){
        super(message);
    }
}
