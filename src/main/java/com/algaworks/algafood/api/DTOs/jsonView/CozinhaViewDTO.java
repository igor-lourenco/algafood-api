package com.algaworks.algafood.api.DTOs.jsonView;

import com.algaworks.algafood.domain.models.views.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Essa classe foi criada de exemplo de como tem que ser implementado a annotaton @JsonView para projeção dos campos retornados na API */
@Schema(name = "Cozinha Resumida usando o @JsonView")// Usada no contexto do Swagger para descrever essa classe como modelo de dados que será utilizado na API
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true) // Permite que crie um builder a partir de uma instância existente, o que pode ser útil em casos onde você precisa modificar ou complementar um objeto sem criar um novo do zero.
public class CozinhaViewDTO {

    @Schema(example = "1")
    @JsonView(RestauranteView.Resumo.class)
    private Long id;

    @Schema(example = "Tailandesa")
    @JsonView(RestauranteView.Resumo.class)
    private String nome;
}
