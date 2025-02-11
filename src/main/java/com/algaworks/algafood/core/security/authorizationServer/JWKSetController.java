package com.algaworks.algafood.core.security.authorizationServer;

import com.nimbusds.jose.jwk.JWKSet;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/** Esse controlador expõe um endpoint que serve o conjunto de chaves JSON Web Key (JWK) em formato JSON.
 * Isso é útil para clientes que precisam obter a chave pública para validar tokens JWT assinados pelo servidor. */
@RestController
@Log4j2
public class JwtSetController {

    @Autowired
    private JWKSet jwkSet;


    @GetMapping(path = "/.well-know/jwks.json")
    public Map<String, Object> keys(){

        log.info("JWKS Endpoint");

        return this.jwkSet.toJSONObject();
    }
}
