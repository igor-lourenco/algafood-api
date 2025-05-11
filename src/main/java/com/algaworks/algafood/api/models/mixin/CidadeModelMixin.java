package com.algaworks.algafood.api.models.mixin;

import com.algaworks.algafood.domain.models.EstadoModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/** Classe utilizada para usar as anotações da biblioteca Jackson e separar regras de Domínio(Banco de dados e mapeamento dos campos) de API(Representação dos recursos JSON no Controller) */
public abstract class CidadeModelMixin {

    @JsonIgnoreProperties(value = {"nome"}, allowGetters = true) // Retorna erro se o campo 'nome' de estado e apenas permite se for para leitura. Obs: também tem que configurar no application.properties
    private EstadoModel estado;
}
