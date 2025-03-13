package com.algaworks.algafood.swaggerOpenApi.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

/** Essa classe foi criada para documentar os campos do status code 400 retornado para o usuário. */
@Schema(name = "Erro 400")// cria tag para o SpringDoc poder enxergar essa classe e poder mapear os campos para ser mostrado na documentação
@Getter
public abstract class StandardErrorBadRequest {

    @Schema(example = "400")
    private Integer status;

    @Schema(example = "http://localhost:8080/parametro-invalido")
    private String type;

    @Schema(example = "Parametro invalido")
    private String title;

    @Schema(example = "O parâmetro de URL 'id' recebeu o valor 'a', que é do tipo inválido. Corrija e informe um valor compatível com o tipo Long.")
    private String detail;

    @Schema(example = "2024-10-20T14:30:34Z")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime timestamp;

}
