package com.algaworks.algafood.api.controllers.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL) // remove os campos nulos no retorno da resposta
@Getter
@Builder
public class StandardError { // corpo de resposta padrão RFC 7807

    private Integer status;
    private String type;
    private String title;
    private String detail;
    private LocalDateTime timestamp;
}
