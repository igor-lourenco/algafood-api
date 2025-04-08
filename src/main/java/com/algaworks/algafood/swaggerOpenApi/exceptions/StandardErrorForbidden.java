package com.algaworks.algafood.swaggerOpenApi.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

/** Essa classe foi criada para documentar os campos do status code 404 retornado para o usuário. */
@Schema(name = "Erro 403") // cria tag para o SpringDoc poder enxergar essa classe e poder mapear os campos para ser mostrado na documentação
@Getter
public abstract class StandardErrorForbidden {

    @Schema(example = "403")
    private Integer status;

    @Schema(example = "http://localhost:8080/acesso-negado")
    private String type;

    @Schema(example = "Acesso negado")
    private String title;

    @Schema(example = "Você não tem permissão para executar essa operação.")
    private String detail;

    @Schema(example = "2024-10-20T14:30:34Z")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime timestamp;

}
