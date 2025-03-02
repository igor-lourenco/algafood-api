package com.algaworks.algafood.infrastructure.services.impl;

import com.algaworks.algafood.infrastructure.services.EnvioEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class FakeEnvioEmailServiceImpl implements EnvioEmailService {

    @Autowired
    private ProcessadorEmailTemplate processadorEmailTemplate;

    @Override
    public void enviar(Mensagem mensagem) {
        // Foi necessário alterar o modificador de acesso do método processarTemplate da classe pai para "protected", para poder chamar aqui
        String corpo = processadorEmailTemplate.processaTemplate(mensagem);

        log.info("[FAKE E-MAIL] Para: {}\n{}", mensagem.getDestinatarios(), corpo);
    }
}
