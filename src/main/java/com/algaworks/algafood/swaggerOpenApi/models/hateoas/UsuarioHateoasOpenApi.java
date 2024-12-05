package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.time.LocalDateTime;

@ApiModel("Usuário Output")
@Data
public class UsuarioHateoasOpenApi {

    @ApiModelProperty(example = "1", position = 0)
    private Long id;
    @ApiModelProperty(example = "Diana R", position = 5)
    private String nome;
    @ApiModelProperty(example = "diana@gmail.com", position = 10)
    private String email;

    @ApiModelProperty(example = "2024-09-05T14:04:11Z", position = 15)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'") // Padrão ISO 8601 UTC
    private LocalDateTime dataCadastro;

    private Links _links;
}