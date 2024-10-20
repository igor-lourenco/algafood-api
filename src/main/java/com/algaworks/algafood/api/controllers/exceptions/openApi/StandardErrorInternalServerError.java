package com.algaworks.algafood.api.controllers.exceptions.openApi;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.LocalDateTime;

/** Essa classe foi criada para documentar os campos do status code 500 retornado para o usuário. */
@ApiModel(value = "StandardErrorInternalServerError")// cria tag para o SpringFox poder enxergar essa classe e poder mapear os campos para ser mostrado na documentação
@Getter
public abstract class StandardErrorInternalServerError {

    @ApiModelProperty(example = "500", position = 0)
    private Integer status;
    @ApiModelProperty(example = "http://localhost:8080/erro-interno", position = 5)
    private String type;
    @ApiModelProperty(example = "Erro interno", position = 10)
    private String title;
    @ApiModelProperty(example = "Ocorreu um erro interno no sistema. Tente novamente e se o erro persistir, entre em contato com o administrador do sistema.", position = 15)
    private String detail;

    @ApiModelProperty(example = "2024-10-20T14:30:34Z", position = 20)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime timestamp;

}
