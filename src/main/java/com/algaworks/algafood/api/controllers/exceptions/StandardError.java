package com.algaworks.algafood.api.controllers.exceptions;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class StandardError {

    private LocalDateTime dataHora;
    private String mensagem;
}
