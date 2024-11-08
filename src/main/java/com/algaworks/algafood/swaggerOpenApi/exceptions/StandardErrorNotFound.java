package com.algaworks.algafood.swaggerOpenApi.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.LocalDateTime;

/** Essa classe foi criada para documentar os campos do status code 404 retornado para o usuário. */
@ApiModel(value = "Erro 404")// cria tag para o SpringFox poder enxergar essa classe e poder mapear os campos para ser mostrado na documentação
@Getter
public abstract class StandardErrorNotFound {

    @ApiModelProperty(example = "404", position = 0)
    private Integer status;
    @ApiModelProperty(example = "http://localhost:8080/recurso-nao-encontrada", position = 5)
    private String type;
    @ApiModelProperty(example = "Recurso nao encontrado", position = 10)
    private String title;
    @ApiModelProperty(example = "Não existe cadastro com esse id.", position = 15)
    private String detail;

    @ApiModelProperty(example = "2024-10-20T14:30:34Z", position = 20)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime timestamp;

}
