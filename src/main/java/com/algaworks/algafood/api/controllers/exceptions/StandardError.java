package com.algaworks.algafood.api.controllers.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@ApiModel(value = "StandardError")
@JsonInclude(JsonInclude.Include.NON_NULL) // remove os campos nulos no retorno da resposta
@Getter
@Builder
public class StandardError { // corpo de resposta padrão RFC 7807

    @ApiModelProperty(example = "400", position = 0)
    private Integer status;
    @ApiModelProperty(example = "http://localhost:8080/recurso-nao-encontrada", position = 5)
    private String type;
    @ApiModelProperty(example = "Recurso nao encontrada", position = 10)
    private String title;
    @ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente", position = 15)
    private String detail;

    @ApiModelProperty(example = "2024-10-18T17:08:16Z", position = 20)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime timestamp;

    @ApiModelProperty(value = "lista de objeto ou campos que geraram erro (opcional)", position = 25)
    private List<Object> objects; // Para adicionar as propriedades com as constraints violadas

    @ApiModel(value = "ObjectError")
    @Getter
    @Builder
    public static class Object {

        @ApiModelProperty(example = "nome")
        private String name;
        @ApiModelProperty(example = "não deve estar em branco")
        private String userMessage;
    }
}
