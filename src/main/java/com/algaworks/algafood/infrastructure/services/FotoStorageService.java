package com.algaworks.algafood.infrastructure.services;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;
import java.util.UUID;

public interface FotoStorageService {

    void armazenar(NovaFoto novaFoto);

    @Builder
    @Getter
    class NovaFoto{
        private String nomeArquivo;
        private InputStream  inputStream;
    }

    default String gerarNomeArquivo(String nomeOriginal){
        return UUID.randomUUID().toString() + "_" + nomeOriginal;
    }

}
