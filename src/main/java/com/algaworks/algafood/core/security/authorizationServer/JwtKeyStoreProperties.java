package com.algaworks.algafood.core.security.authorizationServer;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Validated
@Component
@ConfigurationProperties("algafood.jwt.keystore")
@Getter
@Setter
public class JwtKeyStoreProperties {

    @NotNull
    private Resource path;

    @NotBlank
    private String password;

    @NotBlank
    private String keypairAlias;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKeypairAlias() {
        return keypairAlias;
    }

    public void setKeypairAlias(String keypairAlias) {
        this.keypairAlias = keypairAlias;
    }

}