package com.algaworks.algafood.domain.exceptions;

import lombok.Getter;

@Getter
public class ReportPDFException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ReportPDFException(String message){
        super(message);
    }

    public ReportPDFException(String message, Throwable cause){
        super(message, cause);
    }
}
