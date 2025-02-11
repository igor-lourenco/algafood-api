package com.algaworks.algafood.core.io;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ProtocolResolver;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.util.Base64;

/** Essa classe decodifica os recursos codificados em base64 e se registra automaticamente ao contexto da aplicação Spring durante a inicialização. */
@Log4j2
public class Base64ProtocolResolver implements ProtocolResolver,
    ApplicationListener<ApplicationContextInitializedEvent> // Dessa forma essa classe se registra como um listener para eventos de inicialização do contexto da aplicação.
                                                            {


    @Override
    public Resource resolve(String location, ResourceLoader resourceLoader) {

        if(location.startsWith("base64:")){
            log.info("Decodificando o location: {}", location);
            log.info("resourceLoader: {}", resourceLoader.getResource("classpath:application.properties"));

            byte[] decodeResource = Base64.getDecoder().decode(location.substring(7));
            return new ByteArrayResource(decodeResource);
        }

        return null;
    }



/** Quando o evento ApplicationContextInitializedEvent ocorre, esse método é chamado e adiciona esta
 *  instância de Base64ProtocolResolver como um ProtocolResolver ao contexto da aplicação. */
    @Override
    public void onApplicationEvent(ApplicationContextInitializedEvent event) {

        log.info("Adicionando classe Base64ProtocolResolver no contexto da aplicação");
        event.getApplicationContext().addProtocolResolver(this);
    }
}
