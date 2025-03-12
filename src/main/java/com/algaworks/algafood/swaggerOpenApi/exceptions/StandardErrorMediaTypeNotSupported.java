package com.algaworks.algafood.swaggerOpenApi.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

/** Essa classe foi criada para documentar os campos do status code 415 retornado para o usuário. */
//@ApiModel(value = "Erro 415")// cria tag para o SpringFox poder enxergar essa classe e poder mapear os campos para ser mostrado na documentação
@Schema(name = "Erro 415")
@Getter
public abstract class StandardErrorMediaTypeNotSupported {

//    @ApiModelProperty(example = "415", position = 0)
    @Schema(example = "415")
    private Integer status;
//    @ApiModelProperty(example = "http://localhost:8080/media-type-invalido", position = 5)
    @Schema(example = "http://localhost:8080/media-type-invalido")
    private String type;
//    @ApiModelProperty(example = "Media Type não suportado", position = 10)
    @Schema(example = "Media Type não suportado")
    private String title;
//    @ApiModelProperty(example = "O Content-Type 'application/xml' não é suportado. Corrija e informe um valor válido.", position = 15)
    @Schema(example = "O Content-Type 'application/xml' não é suportado. Corrija e informe um valor válido.")
    private String detail;

//    @ApiModelProperty(example = "2024-10-20T14:30:34Z", position = 20)
    @Schema(example = "2024-10-20T14:30:34Z")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime timestamp;

}
