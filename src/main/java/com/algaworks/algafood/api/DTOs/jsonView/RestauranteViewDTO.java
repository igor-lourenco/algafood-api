package com.algaworks.algafood.api.DTOs.jsonView;


import com.algaworks.algafood.api.DTOs.EnderecoDTO;
import com.algaworks.algafood.domain.models.views.RestauranteView;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
/** Essa classe foi criada de exemplo de como tem que ser implementado a annotaton @JsonView para projeção dos campos retornados na API */

@ApiModel(value = "Restaurante Resumido usando o @JsonView")// Usada no contexto do Swagger para descrever essa classe como modelo de dados que será utilizado na API
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true) // Permite que crie um builder a partir de uma instância existente, o que pode ser útil em casos onde você precisa modificar ou complementar um objeto sem criar um novo do zero.
public class RestauranteViewDTO {

    @ApiModelProperty(example = "1", position = 0)
    @JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
    private Long id;

    @ApiModelProperty(example = "Thai Gourmet", position = 5)
    @JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
    private String nome;

    @ApiModelProperty(example = "0.00", position = 10)
    @JsonView(RestauranteView.Resumo.class)
    private String taxaFrete;

    @ApiModelProperty(position = 15)
    @JsonView(RestauranteView.Resumo.class)
    private CozinhaViewDTO cozinha;

    @ApiModelProperty(hidden = true) // oculta o campo na documentação
    private Boolean ativo;
    @ApiModelProperty(hidden = true)
    private Boolean aberto;

    @ApiModelProperty(hidden = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private OffsetDateTime dataCadastro; // OffsetDateTime por padrão já usa o ISO 8601 UTC

    @ApiModelProperty(hidden = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'") // Padrão ISO 8601 UTC
    private LocalDateTime dataAtualizacao;

    @ApiModelProperty(hidden = true)
    private EnderecoDTO endereco;
}
