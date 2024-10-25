package com.algaworks.algafood.api.DTOs;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@ApiModel(value = "Restaurante")// Usada no contexto do Swagger para descrever essa classe como modelo de dados que será utilizado na API
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true) // Permite que crie um builder a partir de uma instância existente, o que pode ser útil em casos onde você precisa modificar ou complementar um objeto sem criar um novo do zero.
@JsonInclude(JsonInclude.Include.NON_NULL) // Ignora campos com valores nulos durante a serialização para JSON
public class RestauranteDTO {

    @ApiModelProperty(example = "1", position = 0)
    private Long id;
    @ApiModelProperty(example = "Thai Gourmet", position = 5)
    private String nome;
    @ApiModelProperty(example = "10.00", position = 10)
    private String taxaFrete;
    @ApiModelProperty(position = 15)
    private CozinhaDTO cozinha;
    @ApiModelProperty(example = "true", position = 20)
    private Boolean ativo;
    @ApiModelProperty(example = "true", position = 25)
    private Boolean aberto;

    @ApiModelProperty(example = "2024-09-05T14:04:11Z", position = 30)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private OffsetDateTime dataCadastro; // OffsetDateTime por padrão já usa o ISO 8601 UTC

    @ApiModelProperty(example = "2024-09-05T14:04:11Z", position = 35)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'") // Padrão ISO 8601 UTC
    private LocalDateTime dataAtualizacao;

    @ApiModelProperty(position = 40)
    private EnderecoDTO endereco;
}
