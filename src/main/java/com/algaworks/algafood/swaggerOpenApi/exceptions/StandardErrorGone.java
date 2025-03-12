package com.algaworks.algafood.swaggerOpenApi.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

/** Essa classe foi criada para documentar os campos do status code 410 retornado para o usuário. */
@Schema(name = "Erro 410") // cria tag para o SpringDoc poder enxergar essa classe e poder mapear os campos para ser mostrado na documentação
@Getter
public abstract class StandardErrorGone {

//    @ApiModelProperty(example = "400", position = 0)
    @Schema(example = "410")
    private Integer status;
//    @ApiModelProperty(example = "http://localhost:8080/parametro-invalido", position = 5)
    @Schema(example = "http://localhost:8080/recurso-depreciado")
    private String type;
//    @ApiModelProperty(example = "Parametro invalido", position = 10)
    @Schema(example = "Recurso depreciado")
    private String title;
//    @ApiModelProperty(example = "O parâmetro de URL 'id' recebeu o valor 'a', que é do tipo inválido. Corrija e informe um valor compatível com o tipo Long.", position = 15)
    @Schema(example = "Recurso solicitado foi removido permanentemente do servidor e não está mais disponível")
    private String detail;

//    @ApiModelProperty(example = "2024-10-20T14:30:34Z", position = 20)
    @Schema(example = "2024-10-20T14:30:34Z")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime timestamp;

}
