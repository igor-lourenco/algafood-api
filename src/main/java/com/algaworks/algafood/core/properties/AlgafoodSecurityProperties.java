package com.algaworks.algafood.core.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;

@Component
@Getter
@Setter
@Validated
@ConfigurationProperties("algafood.auth")
public class AlgafoodSecurityProperties {

    @NotBlank
    private String providerUrl;
}