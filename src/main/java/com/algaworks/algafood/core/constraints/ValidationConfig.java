package com.algaworks.algafood.core.constraints;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/** O foco dessa classe de configuração é definir como as mensagens de erro de validação serão resolvidas,
 * permitindo a utilização de um arquivo específico para essas mensagens, como o arquivo messages.properties que foi implementado nesse projeto,
 * em vez do arquivo padrão ValidationMessages.properties do Bean Validation.*/
@Configuration
public class ValidationConfig {

    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean(MessageSource messageSource){

        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();

        // Especifica que é para usar como padrão o arquivo messages.properties para customizar os erros de validação em vez do ValidationMessages.properties do bean Validation
        bean.setValidationMessageSource(messageSource);

        return bean;
    }
}
