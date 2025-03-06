package com.algaworks.algafood.swaggerOpenApi.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

/** Essa classe foi criada para documentar os campos do status code 400 retornado para o usuário. */
//@ApiModel(value = "Erro 400")// cria tag para o SpringFox poder enxergar essa classe e poder mapear os campos para ser mostrado na documentação
@Getter
public abstract class StandardErrorBadRequest {

//    @ApiModelProperty(example = "400", position = 0)
    private Integer status;
//    @ApiModelProperty(example = "http://localhost:8080/parametro-invalido", position = 5)
    private String type;
//    @ApiModelProperty(example = "Parametro invalido", position = 10)
    private String title;
//    @ApiModelProperty(example = "O parâmetro de URL 'id' recebeu o valor 'a', que é do tipo inválido. Corrija e informe um valor compatível com o tipo Long.", position = 15)
    private String detail;

//    @ApiModelProperty(example = "2024-10-20T14:30:34Z", position = 20)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime timestamp;

}
