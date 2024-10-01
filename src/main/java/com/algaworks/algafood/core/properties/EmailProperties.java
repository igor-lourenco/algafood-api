package com.algaworks.algafood.core.properties;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.digester.annotations.rules.SetTop;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Component
@ConfigurationProperties(prefix = "algafood.email")
@Getter
@Setter
@Validated // para validar os campos com o Bean Validation
public class EmailProperties {

    @NotNull // Se o campo for nulo e não estiver preenchido no application.properties dá exception e não sobe a aplicação
    private String remetente;

}
