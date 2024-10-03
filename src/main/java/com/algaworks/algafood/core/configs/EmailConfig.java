package com.algaworks.algafood.core.configs;

import com.algaworks.algafood.core.properties.EmailProperties;
import com.algaworks.algafood.infrastructure.services.EnvioEmailService;
import com.algaworks.algafood.infrastructure.services.impl.FakeEnvioEmailServiceImpl;
import com.algaworks.algafood.infrastructure.services.impl.SandboxEnvioEmailServiceImpl;
import com.algaworks.algafood.infrastructure.services.impl.SmtpEnvioEmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {

    @Autowired
    private EmailProperties emailProperties;

    @Bean
    public EnvioEmailService envioEmailService() {

        // Verifica o valor que está no application.properties para instanciar o service no contexto do Spring para disponibilizar a injeção nas partes da aplicação
        switch (emailProperties.getImpl()) {
            case FAKE:
                return new FakeEnvioEmailServiceImpl();
            case SMTP:
                return new SmtpEnvioEmailServiceImpl();
            case SANDBOX:
                return new SandboxEnvioEmailServiceImpl();
            default:
                return null;
        }
    }
}