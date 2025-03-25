package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Schema(name ="Restaurante Output")
@Data
public class RestauranteHateoasOpenApi {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Thai Gourmet")
    private String nome;

    @Schema(example = "0.00")
    private String taxaFrete;

    private CozinhaHateoasOpenApi cozinha;

    @Schema(example = "true")
    private Boolean ativo;

    @Schema(example = "true")
    private Boolean aberto;

    @Schema(example = "2024-09-05T14:04:11Z") // Configurado no ModelMapperConfig para ignorar esse campo no mapeamento de RestauranteModel para RestauranteDTO
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private OffsetDateTime dataCadastro; // OffsetDateTime por padrão já usa o ISO 8601 UTC

    @Schema(example = "2024-09-05T14:04:11Z") // Configurado no ModelMapperConfig para ignorar esse campo no mapeamento de RestauranteModel para RestauranteDTO
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'") // Padrão ISO 8601 UTC
    private LocalDateTime dataAtualizacao;

    private EnderecoHateoasOpenApi endereco;

    @Schema(name = "_links")
    private LinksModelOpenApi _links;
}