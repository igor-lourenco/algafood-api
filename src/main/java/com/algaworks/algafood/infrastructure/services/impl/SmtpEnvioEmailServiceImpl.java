package com.algaworks.algafood.infrastructure.services.impl;

import com.algaworks.algafood.core.properties.EmailProperties;
import com.algaworks.algafood.domain.exceptions.EmailException;
import com.algaworks.algafood.infrastructure.services.EnvioEmailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;

//@Service
@Log4j2
public class SmtpEnvioEmailServiceImpl implements EnvioEmailService {

    @Autowired
    private JavaMailSender mailSender; // Fornecido pelo Spring para facilitar o envio de e-mails com suporte a SMTP.
    @Autowired
    private EmailProperties emailProperties;
    @Autowired
    private Configuration freemarkerConfig;

    @Override
    public void enviar(Mensagem mensagem) {

        try {
            MimeMessage mimeMessage = criaMimeMessage(mensagem);

            mailSender.send(mimeMessage); // Envia o e-mail

        } catch (Exception e) {
            throw new EmailException("Não foi possível enviar e-mail", e);
        }
    }

    protected MimeMessage criaMimeMessage(Mensagem mensagem) throws MessagingException {
        String corpo = processaTemplate(mensagem);

//      Cria uma instância de MimeMessage através do JavaMailSender, que é a representação do e-mail a ser enviado.
        MimeMessage mimeMessage = mailSender.createMimeMessage();

//      Cria um novo MimeMessageHelper para o MimeMessage fornecido, assumindo uma mensagem de texto simples (sem conteúdo multipartes, ou seja, sem textos alternativos e sem elementos embutidos ou anexos).
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");


        helper.setFrom(emailProperties.getRemetente());     // O Remetente (FROM)
        helper.setTo(mensagem.getDestinatarios().toArray(String[]::new));  // Os destinatários
        helper.setSubject(mensagem.getAssunto());    // O Assunto
        helper.setText(corpo, true); // O corpo do e-mail com o html: true

        log.info("Enviando email: {}", Arrays.toString(mimeMessage.getFrom()));

        return mimeMessage;
    }

    protected String processaTemplate(Mensagem mensagem){

        try {
//          Carrega o template de e-mail com o nome fornecido pelo campo
            Template template = freemarkerConfig.getTemplate(mensagem.getCorpo());

//          Processa o template carregado, substituindo as variáveis presentes no template pelos valores fornecidos no mapa de variáveis mensagem.getVariaveis().
            return FreeMarkerTemplateUtils
                .processTemplateIntoString(template, mensagem.getVariaveis());

        } catch (Exception e) {
            throw new EmailException("Não foi possível montar template do e-mail", e);
        }
    }
}
