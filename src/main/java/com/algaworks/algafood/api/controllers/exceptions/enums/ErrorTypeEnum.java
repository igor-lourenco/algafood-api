package com.algaworks.algafood.api.controllers.exceptions.enums;

import lombok.Getter;

@Getter
public enum ErrorTypeEnum {

    PARAMETER_INVALID("Parametro invalido", "/parametro-invalido"),
    ENTITY_NOT_FOUND("Entidade nao encontrada", "/entidade-nao-encontrada"),
    ENTITY_IN_USE("Entidade em uso", "/entidade-em-uso"),
    JSON_INVALID("JSON incompreensivel", "/json-incompreensivel"),
    INTERNAL_ERROR("Erro interno", "/erro-interno");

    private String title;
    private String uri;

    ErrorTypeEnum(String title, String path){
        this.title = title;
        this.uri = "http://localhost:8080" + path;
    }
}
