package com.algaworks.algafood.infrastructure.services.impl;

import com.algaworks.algafood.core.properties.EmailProperties;
import com.algaworks.algafood.domain.exceptions.EmailException;
import com.algaworks.algafood.infrastructure.services.EnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Arrays;

@Service
public class SmtpEnvioEmailServiceImpl implements EnvioEmailService {

    @Autowired
    private JavaMailSender mailSender; // Fornecido pelo Spring para facilitar o envio de e-mails com suporte a SMTP.
    @Autowired
    private EmailProperties emailProperties;

    @Override
    public void enviar(Mensagem mensagem) {

        try {
//          Cria uma instância de MimeMessage através do JavaMailSender, que é a representação do e-mail a ser enviado.
            MimeMessage mimeMessage = mailSender.createMimeMessage();

//          Cria um novo MimeMessageHelper para o MimeMessage fornecido, assumindo uma mensagem de texto simples (sem conteúdo multipartes, ou seja, sem textos alternativos e sem elementos embutidos ou anexos).
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");

            helper.setFrom(emailProperties.getRemetente());     // O Remetente (FROM)
            helper.setTo(mensagem.getDestinatarios().toArray(String[]::new));  // Os destinatários
            helper.setSubject(mensagem.getAssunto());    // O Assunto
            helper.setText(mensagem.getCorpo(), true); // O corpo do e-mail com o html: true

            System.out.println("Enviando email: " + Arrays.toString(mimeMessage.getFrom()));
            mailSender.send(mimeMessage); // Envia o e-mail

        } catch (Exception e) {
            throw new EmailException("Não foi possível enviar e-mail", e);
        }
    }
}
