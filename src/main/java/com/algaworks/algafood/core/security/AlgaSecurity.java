package com.algaworks.algafood.core.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class AlgaSecurity {

    public Long getUsuarioId() {
        Authentication authentication = getAuthentication();

//      Pega o JWT da autenticação para extrai informações dele.
        Jwt jwt = (Jwt) authentication.getPrincipal();

        Long usuarioId = jwt.getClaim("usuario_id");
        String nomeUsuario = jwt.getClaim("nome_completo");

        log.info("Usuário autenticado: {} - {}", usuarioId,nomeUsuario);
        return usuarioId;
    }

/** Obtém a autenticação atual do contexto de segurança, ou um token de solicitação de autenticação.*/
    public Authentication getAuthentication() {
        return
            SecurityContextHolder // Classe que mantém o contexto de segurança do aplicativo.
                .getContext() // obtém o contexto de segurança atual.
                .getAuthentication(); // obtém a autenticação atual do contexto de segurança.
    }


}
