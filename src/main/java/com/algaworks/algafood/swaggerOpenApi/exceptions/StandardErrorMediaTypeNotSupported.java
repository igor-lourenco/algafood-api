package com.algaworks.algafood.swaggerOpenApi.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

/** Essa classe foi criada para documentar os campos do status code 415 retornado para o usuário. */
@Schema(name = "Erro 415") // cria tag para o SpringDoc poder enxergar essa classe e poder mapear os campos para ser mostrado na documentação
@Getter
public abstract class StandardErrorMediaTypeNotSupported {

    @Schema(example = "415")
    private Integer status;

    @Schema(example = "http://localhost:8080/media-type-invalido")
    private String type;

    @Schema(example = "Media Type não suportado")
    private String title;

    @Schema(example = "O Content-Type 'application/xml' não é suportado. Corrija e informe um valor válido.")
    private String detail;

    @Schema(example = "2024-10-20T14:30:34Z")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime timestamp;

}
