package com.algaworks.algafood.api.controllers.exceptions.enums;

import lombok.Getter;

@Getter
public enum ErrorTypeEnum {
    PARAMETER_NULL("Parametro vazio", "/parametro-vazio"),
    PARAMETER_INVALID("Parametro invalido", "/parametro-invalido"),
    RESOURCE_NOT_FOUND("Recurso nao encontrada", "/recurso-nao-encontrada"),
    ENTITY_IN_USE("Entidade em uso", "/entidade-em-uso"),
    JSON_INVALID("JSON incompreensivel", "/json-incompreensivel"),
    FILTER_INVALID("Filtro inválido", "/filtro-invalido"),
    DATAS_INVALID("Dados invalidos", "/dados-invalidos"),
    INTERNAL_ERROR("Erro interno", "/erro-interno");

    private String title;
    private String uri;

    ErrorTypeEnum(String title, String path){
        this.title = title;
        this.uri = "http://localhost:8080" + path;
    }
}
