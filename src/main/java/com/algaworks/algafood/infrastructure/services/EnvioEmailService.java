package com.algaworks.algafood.infrastructure.services;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

public interface EnvioEmailService {

    void enviar(Mensagem mensagem);

    @Getter
    @Builder
    class Mensagem{

        private Set<String> destinatarios;
        private String assunto;
        private String corpo;
    }
}
