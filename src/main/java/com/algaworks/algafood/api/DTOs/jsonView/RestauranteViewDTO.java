package com.algaworks.algafood.api.DTOs.jsonView;


import com.algaworks.algafood.api.DTOs.EnderecoDTO;
import com.algaworks.algafood.domain.models.views.RestauranteView;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.groups.Default;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
/** Essa classe foi criada de exemplo de como tem que ser implementado a annotaton @JsonView para projeção dos campos retornados na API */
@Schema(name = "Restaurante Resumido usando o @JsonView")// Usada no contexto do Swagger para descrever essa classe como modelo de dados que será utilizado na API
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true) // Permite que crie um builder a partir de uma instância existente, o que pode ser útil em casos onde você precisa modificar ou complementar um objeto sem criar um novo do zero.
public class RestauranteViewDTO {

    @Schema(example = "1")
    @JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
    private Long id;

    @Schema(example = "Thai Gourmet")
    @JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
    private String nome;

    @Schema(example = "0.00")
    @JsonView(RestauranteView.Resumo.class)
    private String taxaFrete;

    @JsonView(RestauranteView.Resumo.class)
    private CozinhaViewDTO cozinha;

    @JsonView(Default.class)
    @Schema(hidden = true) // oculta o campo na documentação
    private Boolean ativo;

    @JsonView(Default.class)
    @Schema(hidden = true)
    private Boolean aberto;

    @JsonView(Default.class)
    @Schema(hidden = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private OffsetDateTime dataCadastro; // OffsetDateTime por padrão já usa o ISO 8601 UTC

    @JsonView(Default.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'") // Padrão ISO 8601 UTC
    private LocalDateTime dataAtualizacao;

    @JsonView(Default.class)
    private EnderecoDTO endereco;
}
