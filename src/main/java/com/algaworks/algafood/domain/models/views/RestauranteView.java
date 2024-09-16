package com.algaworks.algafood.domain.models.views;

/** Essa interface foi criada para ser usada com a annotation @JsonView da biblioteca Jackson, tem o objetivo de definir
  diferentes "visões"(views) sobre a serialização de objetos JSON. Isso permite controlar quais campos serão incluídos
  no JSON retornado em diferentes contextos, como exemplo anotar o campo da classe com @JsonView(RestauranteView.Resumo.class).
  Obs: A API também tem que estar anotado com @JsonView(RestauranteView.Resumo.class) usando de base exemplo anterior ou
 pode usar a classe MappingJacksonValue da biblioteca Jackson para implementar na API a view dinamicamente. */
public interface RestauranteView {

    public interface Resumo{}
    public interface ApenasNome{}
}
