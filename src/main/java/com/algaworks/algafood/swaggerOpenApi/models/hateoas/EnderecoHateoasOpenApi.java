package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "Endereco Output")
@Data
public class EnderecoHateoasOpenApi {

    @Schema(example = "38400-000")
    private String cep;

    @Schema(example = "Rua Floriano Peixoto")
    private String logradouro;

    @Schema(example = "500")
    private String numero;

    @Schema(example = "Apto 801")
    private String complemento;

    @Schema(example = "Cazeca")
    private String bairro;

    @Schema(name = "cidade")
    private CidadeResumoHateoasOpenApi cidade;
}