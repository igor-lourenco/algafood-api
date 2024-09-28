package com.algaworks.algafood.domain.exceptions;

public class StorageException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public StorageException(String msg) {
        super(msg);
    }

    public StorageException(String msg, Throwable cause){
        super(msg, cause);
    }
}
