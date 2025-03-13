package com.algaworks.algafood.swaggerOpenApi.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

/** Essa classe foi criada para documentar os campos do status code 404 retornado para o usuário. */
@Schema(name = "Erro 404") // cria tag para o SpringDoc poder enxergar essa classe e poder mapear os campos para ser mostrado na documentação
@Getter
public abstract class StandardErrorNotFound {

//    @ApiModelProperty(example = "404", position = 0)
    @Schema(example = "404")
    private Integer status;
//    @ApiModelProperty(example = "http://localhost:8080/recurso-nao-encontrada", position = 5)
    @Schema(example = "http://localhost:8080/recurso-nao-encontrada")
    private String type;
//    @ApiModelProperty(example = "Recurso nao encontrado", position = 10)
    @Schema(example = "Recurso nao encontrado")
    private String title;
//    @ApiModelProperty(example = "Não existe cadastro com esse id.", position = 15)
    @Schema(example = "Não existe cadastro com esse id.")
    private String detail;

//    @ApiModelProperty(example = "2024-10-20T14:30:34Z", position = 20)
    @Schema(example = "2024-10-20T14:30:34Z")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime timestamp;

}
