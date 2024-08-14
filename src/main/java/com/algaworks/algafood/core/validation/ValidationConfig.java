package com.algaworks.algafood.core.validation;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

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
