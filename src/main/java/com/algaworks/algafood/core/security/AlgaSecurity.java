package com.algaworks.algafood.core.security;

import com.algaworks.algafood.domain.exceptions.UsuarioNaoAutenticadoException;
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

        if(null == authentication || !(authentication.getPrincipal() instanceof Jwt))
            throw new UsuarioNaoAutenticadoException("O usuário não foi autenticado.");

//      Pega o JWT da autenticação para extrai informações dele.
        Jwt jwt = (Jwt) authentication.getPrincipal();

        Long usuarioId = jwt.getClaim("usuario_id");
        String nomeUsuario = jwt.getClaim("nome_completo");

        log.info("Usuário autenticado: {} - {}", usuarioId,nomeUsuario);
        return usuarioId;
    }

/** Obtém a autenticação atual do contexto de segurança, ou um token de solicitação de autenticação.*/
    private Authentication getAuthentication() {
        Authentication authentication = SecurityContextHolder // Classe que mantém o contexto de segurança do aplicativo.
                                        .getContext() // obtém o contexto de segurança atual.
                                        .getAuthentication(); // obtém a autenticação atual do contexto de segurança.

        return authentication;
    }


/** Esse método verifica se o usuário autenticado na requisição atual é o responsável por esse restaurante.
 *  Obs: Por exemplo, esse método é chamado na anotação @CheckSecurity.Restaurantes.PodeGerenciarFuncionamento usando o SpEL (Spring Expression Language)*/
    public boolean gerenciaRestaurante(Long restauranteId){

        if(null == restauranteId){
            log.warn("O parâmetro 'restauranteId' é null");
            return false;
        }

        Long usuarioId = getUsuarioId();
        boolean responsavelForThisRestaurante = restauranteRepository.isResponsavelForThisRestaurante(usuarioId, restauranteId);

        if(responsavelForThisRestaurante)
            log.info("O usuário: {} é responsável pelo restaurante: {}", usuarioId, restauranteId);
        else{
            log.warn("O usuário: {} não é responsável pelo restaurante: {}", usuarioId, restauranteId);
        }

        return responsavelForThisRestaurante;
    }

/**  Esse método verifica se o pedido pertence ao usuário autenticado na requisição atual.
 *   Obs: Por exemplo, esse método é chamado na anotação @CheckSecurity.Pedidos.PodePesquisar usando o SpEL (Spring Expression Language)*/
    public boolean verificaSePedidoPertenceAoUsuario(Long clienteId) {
        Long usuarioId = getUsuarioId();

        if (null == clienteId){
            log.warn("O parâmetro 'clienteId' é null");
            return false;
        } else if (clienteId.equals(usuarioId)) {
            log.info("O parâmetro 'clienteId': {} é igual ao usuarioId: {}", clienteId, usuarioId);
            return true;
        } else {
            log.warn("O parâmetro 'clienteId': {} não igual ao usuarioId: {}", clienteId, usuarioId);
            return false;
        }
    }
}
