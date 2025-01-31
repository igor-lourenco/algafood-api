package com.algaworks.algafood.core.security;

import com.algaworks.algafood.domain.repositories.RestauranteRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class AlgaSecurity {

    @Autowired
    private RestauranteRepository restauranteRepository;


/** Esse método retorna o id do usuário autenticado na requisição atual*/
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
    private Authentication getAuthentication() {
        return
            SecurityContextHolder // Classe que mantém o contexto de segurança do aplicativo.
                .getContext() // obtém o contexto de segurança atual.
                .getAuthentication(); // obtém a autenticação atual do contexto de segurança.
    }


/** Esse método verifica se o usuário autenticado na requisição atual é o responsável por esse restaurante
 *  Obs: Por exemplo, esse método é chamado na anotação @CheckSecurity.Restaurantes.PodeGerenciarFuncionamento usando o SpEL (Spring Expression Language)*/
    public boolean gerenciaRestaurante(Long restauranteId){

        Long usuarioId = getUsuarioId();
        boolean responsavelForThisRestaurante = restauranteRepository.isResponsavelForThisRestaurante(usuarioId, restauranteId);

        if(responsavelForThisRestaurante)
            log.info("O usuário: {} é responsável pelo restaurante: {}", usuarioId, restauranteId);
        else{
            log.error("O usuário: {} não é responsável pelo restaurante: {}", usuarioId, restauranteId);
        }

        return responsavelForThisRestaurante;
    }

}
