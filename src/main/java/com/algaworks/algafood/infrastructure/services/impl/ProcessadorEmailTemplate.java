package com.algaworks.algafood.infrastructure.services.impl;

import com.algaworks.algafood.domain.exceptions.EmailException;
import com.algaworks.algafood.infrastructure.services.EnvioEmailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

@Component
public class ProcessadorEmailTemplate {

    @Autowired
    private Configuration freemarkerConfig;

    public String processaTemplate(EnvioEmailService.Mensagem mensagem) {

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
