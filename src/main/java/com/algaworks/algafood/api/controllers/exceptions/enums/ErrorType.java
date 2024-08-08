package com.algaworks.algafood.api.controllers.exceptions.enums;

import lombok.Getter;
import org.apache.tomcat.jni.Error;

@Getter
public enum ErrorType {

    ENTIDADE_NAO_ENCONTRADA("Entidade não encontrada", "/entidade-nao-encontrada");

    private String title;
    private String uri;

    ErrorType(String title, String path){
        this.title = title;
        this.uri = "http://localhost:8080" + path;
    }
}
