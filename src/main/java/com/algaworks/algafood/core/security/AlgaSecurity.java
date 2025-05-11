package com.algaworks.algafood.core.security;

import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exceptions.UsuarioNaoAutenticadoException;
import com.algaworks.algafood.domain.repositories.PedidoRepository;
import com.algaworks.algafood.domain.repositories.RestauranteRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

/** Esse componente Spring é utilizado para gerenciar a segurança da aplicação. Contém métodos para verificar se um
 * usuário tem permissão para acessar certos recursos, verificando suas permissões e definindo regras de acesso */
@Log4j2
@Component
public class AlgaSecurity {

    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private PedidoRepository pedidoRepository;


/** Esse método retorna o id do usuário autenticado na requisição atual*/
    public Long getUsuarioId() {
        Authentication authentication = getAuthentication();

        if(null == authentication || !(authentication.getPrincipal() instanceof Jwt)) {
            log.error("Não foi encontrado autenticação: {}", authentication.getPrincipal());
            throw new UsuarioNaoAutenticadoException("O usuário não foi autenticado.");
        }

//      Pega o JWT da autenticação para extrai informações dele.
        Jwt jwt = (Jwt) authentication.getPrincipal();

        Object usuarioId = jwt.getClaim("usuario_id");
        Object nomeUsuario = jwt.getClaim("nome_completo");

        log.info("Usuário autenticado: {} - {}", usuarioId,nomeUsuario);

        if(null == usuarioId) return null;

        return Long.valueOf(usuarioId.toString());
    }

/** Obtém a autenticação atual do contexto de segurança, ou um token de solicitação de autenticação.*/
    private Authentication getAuthentication() {
        return SecurityContextHolder // Classe que mantém o contexto de segurança do aplicativo.
            .getContext() // obtém o contexto de segurança atual.
            .getAuthentication(); // obtém a autenticação atual do contexto de segurança.

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


/** Esse método verifica se o usuário autenticado na requisição atual é o responsável do restaurante desse pedido.
 *   Obs: Por exemplo, esse método é chamado na anotação @CheckSecurity.Pedidos.PodeGerenciarPedido usando o SpEL (Spring Expression Language)*/
    public boolean gerenciaPedidosDoRestaurante(String codigoPedido) {
        Long usuarioId = getUsuarioId();

        if (!pedidoRepository.existsByCodigo(codigoPedido))
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de pedido com codigo: %s", codigoPedido));

        boolean isResponsavel = pedidoRepository.isResponsavelDoRestauranteDessePedido(usuarioId, codigoPedido);

        if (isResponsavel) {
            log.info("O usuário: {} é responsável pelo pedido: {}", usuarioId, codigoPedido);
            return true;
        }else{
            log.warn("O usuário: {} não é responsável pelo pedido: {}", usuarioId, codigoPedido);
            return false;
        }
    }

/** Esse método verifica se o usuário autenticado na requisição atual tem o scope 'SCOPE_WRITE' e a permissão 'GERENCIAR_PEDIDOS'
    ou se o usuário autenticado for o responsável do restaurante desse pedido para ter permissção de gerenciar esse pedido*/
    public boolean podeGerenciarPedidos(String codigoPedido){

        if(hasAuthority("SCOPE_WRITE") && hasAuthority("GERENCIAR_PEDIDOS")){
            log.info("O usuário autenticado tem permissão para gereciar esse pedido: {}", codigoPedido);
            return true;
        }

        else if(gerenciaPedidosDoRestaurante(codigoPedido)){
            log.info("O usuário autenticado é um dos responsáveis do restaurante desse pedido: {}", codigoPedido);
            return true;
        }

        log.warn("O usuário não tem nenhuma permissão para gerenciar e nem é responsável pelo pedido: {}", codigoPedido);
        return false;
    }


    public boolean podeGerenciarFuncionamentoDesseRestaurante(Long restauranteId){
        if(hasAuthority("SCOPE_WRITE") && hasAuthority("EDITAR_RESTAURANTES")){
            log.info("O usuário autenticado tem permissão para editar esse restaurante: {}", restauranteId);
            return true;
        }

        else if(gerenciaRestaurante(restauranteId)){
            log.info("O usuário autenticado é um dos responsáveis do restaurante: {}", restauranteId);
            return true;
        }

        log.warn("O usuário não tem nenhuma permissão para gerenciar e nem é responsável pelo restaurante: {}", restauranteId);
        return false;

    }

    private boolean hasAuthority(String authorityname){
        return
            getAuthentication().getAuthorities()
                .stream()
                .anyMatch(grantedAuthority ->
                    grantedAuthority.getAuthority().equals(authorityname));

    }
}
