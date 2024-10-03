package com.algaworks.algafood.infrastructure.services.impl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FakeEnvioEmailServiceImpl extends SmtpEnvioEmailServiceImpl {

    @Override
    public void enviar(Mensagem mensagem) {
        // Foi necessário alterar o modificador de acesso do método processarTemplate da classe pai para "protected", para poder chamar aqui
        String corpo = processaTemplate(mensagem);

        log.info("[FAKE E-MAIL] Para: {}\n{}", mensagem.getDestinatarios(), corpo);
    }
}
