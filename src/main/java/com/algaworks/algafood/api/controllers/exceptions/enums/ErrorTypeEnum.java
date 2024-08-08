package com.algaworks.algafood.api.controllers.exceptions.enums;

import lombok.Getter;

@Getter
public enum ErrorTypeEnum {

    ENTIDADE_NAO_ENCONTRADA("Entidade não encontrada", "/entidade-nao-encontrada"),
    ENTIDADE_EM_USO("Entidade em uso", "/entidade-em-uso");

    private String title;
    private String uri;

    ErrorTypeEnum(String title, String path){
        this.title = title;
        this.uri = "http://localhost:8080" + path;
    }
}
