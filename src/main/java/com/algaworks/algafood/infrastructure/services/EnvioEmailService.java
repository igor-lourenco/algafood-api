package com.algaworks.algafood.infrastructure.services;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;

import java.util.Map;
import java.util.Set;

public interface EnvioEmailService {

    void enviar(Mensagem mensagem);

    @Getter
    @Builder
    class Mensagem{

        @Singular // Usada com @Builder para criar métodos 'add' de elemento único no construtor para coleções.
        private Set<String> destinatarios;
        @NonNull //  Lombok insere verificação nula no início do corpo do método/construtor, lançando uma NullPointerException com o nome do parâmetro como mensagem.
        private String assunto;
        @NonNull
        private String corpo;

        @Singular("variavel")
        private Map<String, Object> variaveis;
    }
}
