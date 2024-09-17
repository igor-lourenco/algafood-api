package com.algaworks.algafood.core.configs;

// Referências:
// - https://stackoverflow.com/a/53613678
// - https://tomcat.apache.org/tomcat-8.5-doc/config/http.html
// - https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto-configure-webserver
// - https://gist.github.com/thiagofa/ce48c08e4caae34c5dca0a7a5c252666

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

/** Essa classe está modificando o comportamento padrão do Tomcat para aceitar colchetes ([]) em parâmetros de
  consulta (query parameters) das URLs, que facilita o uso de APIs ou sistemas que exigem esse tipo de formato em suas URLs.*/
@Component
public class TomcatCustomizer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        factory.addConnectorCustomizers(connector -> connector.setAttribute("relaxedQueryChars", "[]"));
    }

}