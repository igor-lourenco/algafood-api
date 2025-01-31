package com.algaworks.algafood.core.security;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface CheckSecurity {


    @interface Cozinhas {

        /** Apenas quem tiver o scope 'SCOPE_READ'
         *  e também esteja autenticado vai ter autorização para acessar nesse método */
        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeConsultar { }


        /** Apenas quem tiver o scope 'SCOPE_WRITE'
         *  e também a permissão 'EDITAR_COZINHAS' vai ter autorização para acessar esse método */
        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_COZINHAS')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeEditar { }

    }


    @interface Restaurantes {

        /** Apenas quem tiver o scope 'SCOPE_READ'
         *  e também esteja autenticado vai ter autorização para acessar nesse método */
        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeConsultar { }


        /** Apenas quem tiver o scope 'SCOPE_WRITE'
         *  e também a permissão 'EDITAR_RESTAURANTES' vai ter autorização para acessar esse método */
        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_RESTAURANTES')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeGerenciarCadastro { }


        /** Apenas quem tiver o scope 'SCOPE_WRITE'
         *  e também a permissão 'EDITAR_RESTAURANTES'
         *  ou for o responsável por esse restaurante vai ter autorização para acessar esse método */
        @PreAuthorize("hasAuthority('SCOPE_WRITE') "
                    +   "and (hasAuthority('EDITAR_RESTAURANTES') " +
                        "or @algaSecurity.gerenciaRestaurante(#restauranteId))")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeGerenciarFuncionamento {
            /*
               O '@' no SpEL(Spring Expression Language) é usado para acessar beans Spring ou variáveis de ambiente.
               Nesse caso essa anotação acessa o bean AlgaSecurity usando o @algaSecurity e dentro desse bean tem o
             método gerenciaRestaurante(Long restauranteId)


               O '#' no SpEL(Spring Expression Language) é usado para acessar variáveis ou métodos definidos no contexto da expressão.
               Nesse caso está acessando a variável 'restauranteId' onde essa anotação está sendo chamada,
               por exemplo essa anotação está sendo chamada no RestauranteController
               no path PUT /{restauranteId}/ativa e no path DELETE /{restauranteId}/inativa

            */
        }

    }


    @interface Pedidos {
        /** Apenas quem tiver o scope 'SCOPE_READ' e também esteja autenticado vai ter autorização para acessar nesse método.
         * <br>
         *  Após a execução do método apenas quem tiver a permissão 'CONSULTAR_PEDIDOS'
         *  ou se o usuário autenticado for o cliente desse pedido
         *  ou se o usuário autenticado for o responsável do restaurante onde esse pedido foi feito
         *  terão autorização para receber o retorno desse método */
        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")

        // É usada para aplicar autorização após a execução de um método
        @PostAuthorize("hasAuthority('CONSULTAR_PEDIDOS') " // apenas quem tiver a permissão 'CONSULTAR_PEDIDOS'
                    +  "or @algaSecurity.getUsuarioId() == returnObject.body.cliente.id " // ou se o usuário autenticado for igual ao cliente desse pedido
                    +  "or @algaSecurity.gerenciaRestaurante(returnObject.body.restaurante.id)") //ou se o usuário autenticado for o responsável do restaurante onde esse pedido foi feito
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeBuscar {
            /*
               O '@' no SpEL(Spring Expression Language) é usado para acessar beans Spring ou variáveis de ambiente.
             Nesse caso essa anotação acessa o bean AlgaSecurity usando o @algaSecurity e dentro desse bean tem o
             método gerenciaRestaurante(Long restauranteId)

               O 'returnObject' no SpEL(Spring Expression Language) refere-se ao objeto retornado pelo método.
             Por exemplo nesse caso está sendo o ResponseEntity<PedidoDTO> do PedidoController no path GET /{codigoPedido}, onde essa
             anotação está sendo usada

            */
        }

    }

}
