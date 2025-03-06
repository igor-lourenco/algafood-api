package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

//@ApiModel("Restaurante Output")
@Data
public class RestauranteHateoasOpenApi {

//    @ApiModelProperty(example = "1", position = 0)
    private Long id;
//    @ApiModelProperty(example = "Thai Gourmet", position = 5)
    private String nome;
//    @ApiModelProperty(example = "0.00", position = 10)
    private String taxaFrete;
//    @ApiModelProperty(position = 15)
    private CozinhaHateoasOpenApi cozinha;
//    @ApiModelProperty(example = "true", position = 20)
    private Boolean ativo;
//    @ApiModelProperty(example = "true", position = 25)
    private Boolean aberto;

//    @ApiModelProperty(example = "2024-09-05T14:04:11Z", position = 30)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private OffsetDateTime dataCadastro; // OffsetDateTime por padrão já usa o ISO 8601 UTC

//    @ApiModelProperty(example = "2024-09-05T14:04:11Z", position = 35)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'") // Padrão ISO 8601 UTC
    private LocalDateTime dataAtualizacao;

//    @ApiModelProperty(position = 40)
    private EnderecoHateoasOpenApi endereco;

    private Links _links;
}