package com.algaworks.algafood.swaggerOpenApi.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

/** Essa classe foi criada para documentar os campos do status code 410 retornado para o usuário. */
@Schema(name = "Erro 410") // cria tag para o SpringDoc poder enxergar essa classe e poder mapear os campos para ser mostrado na documentação
@Getter
public abstract class StandardErrorGone {

    @Schema(example = "410")
    private Integer status;

    @Schema(example = "http://localhost:8080/recurso-depreciado")
    private String type;

    @Schema(example = "Recurso depreciado")
    private String title;

    @Schema(example = "Recurso solicitado foi removido permanentemente do servidor e não está mais disponível")
    private String detail;

    @Schema(example = "2024-10-20T14:30:34Z")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime timestamp;

}
