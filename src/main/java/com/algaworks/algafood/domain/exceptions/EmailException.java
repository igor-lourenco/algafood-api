package com.algaworks.algafood.domain.exceptions;

public class EmailException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EmailException(String mensagem) {
        super(mensagem);
    }

    public EmailException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
