package com.algaworks.algafood.api.controllers.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL) // remove os campos nulos no retorno da resposta
@Getter
@Builder
public class StandardError { // corpo de resposta padrão RFC 7807

    private Integer status;
    private String type;
    private String title;
    private String detail;
    private LocalDateTime timestamp;

    private List<Object> objects; // Pra adicionar as propriedades com as constraints violadas

    @Getter
    @Builder
    public static class Object {
        private String name;
        private String userMessage;
    }
}
