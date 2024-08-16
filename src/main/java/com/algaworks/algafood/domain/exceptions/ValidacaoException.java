package com.algaworks.algafood.domain.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
public class ValidacaoException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private BindingResult bindingResult;

    public ValidacaoException(BindingResult bindingResult){
        this.bindingResult = bindingResult;
    }
}
