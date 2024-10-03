package com.algaworks.algafood.infrastructure.services.impl;

import com.algaworks.algafood.core.properties.EmailProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Slf4j
public class SandboxEnvioEmailServiceImpl extends SmtpEnvioEmailServiceImpl {

    @Autowired
    private EmailProperties emailProperties;

    // Separei a criação de MimeMessage em um método na classe pai (criaMimeMessage) para possibilitar a sobrescrita desse método aqui
    @Override
    protected MimeMessage criaMimeMessage(Mensagem mensagem) throws MessagingException {
        MimeMessage mimeMessage = super.criaMimeMessage(mensagem);

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        helper.setTo(emailProperties.getSandbox().getDestinatario());

        return mimeMessage;
    }
}
