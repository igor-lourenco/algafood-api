package com.algaworks.algafood.swaggerOpenApi.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

/** Essa classe foi criada para documentar os campos do status code 500 retornado para o usuário. */
//@ApiModel(value = "Erro 500")// cria tag para o SpringFox poder enxergar essa classe e poder mapear os campos para ser mostrado na documentação
@Schema(name = "Erro 500")
@Getter
public abstract class StandardErrorInternalServerError {

//    @ApiModelProperty(example = "500", position = 0)
    @Schema(example = "500")
    private Integer status;
//    @ApiModelProperty(example = "http://localhost:8080/erro-interno", position = 5)
    @Schema(example = "http://localhost:8080/erro-interno")
    private String type;
//    @ApiModelProperty(example = "Erro interno", position = 10)
    @Schema(example = "Erro interno")
    private String title;
//    @ApiModelProperty(example = "Ocorreu um erro interno no sistema. Tente novamente e se o erro persistir, entre em contato com o administrador do sistema.", position = 15)
    @Schema(example = "Ocorreu um erro interno no sistema. Tente novamente e se o erro persistir, entre em contato com o administrador do sistema.")
    private String detail;

//    @ApiModelProperty(example = "2024-10-20T14:30:34Z", position = 20)
    @Schema(example = "2024-10-20T14:30:34Z")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime timestamp;

}
