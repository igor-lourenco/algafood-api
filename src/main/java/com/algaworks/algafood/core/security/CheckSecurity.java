package com.algaworks.algafood.core.security;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface CheckSecurity {


    @interface Cozinhas {

/**     Apenas quem tiver o scope 'SCOPE_READ'
        e também esteja autenticado vai ter autorização para acessar nesse método */
        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeConsultar { }


/**     Apenas quem tiver o scope 'SCOPE_WRITE'
        e também a permissão 'EDITAR_COZINHAS' vai ter autorização para acessar esse método */
        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_COZINHAS')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeEditar { }

    }


    @interface Restaurantes {

/**     Apenas quem tiver o scope 'SCOPE_READ'
        e também esteja autenticado vai ter autorização para acessar nesse método */
        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeConsultar { }


/**     Apenas quem tiver o scope 'SCOPE_WRITE'
        e também a permissão 'EDITAR_RESTAURANTES' vai ter autorização para acessar esse método */
        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_RESTAURANTES')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeGerenciarCadastro { }


/**     Apenas quem tiver o scope 'SCOPE_WRITE'
        e também a permissão 'EDITAR_RESTAURANTES'
        ou for o responsável por esse restaurante vai ter autorização para acessar esse método */
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

/**     Apenas quem tiver o scope 'SCOPE_READ'
        e também a permissão 'CONSULTAR_PEDIDOS' vai ter autorização para acessar esse método */
        @PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('CONSULTAR_PEDIDOS')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeConsultar{}


/**     Apenas quem tiver o scope 'SCOPE_READ'  e também a permissão 'CONSULTAR_PEDIDOS'
        ou se o usuário autenticado for igual ao cliente desse pedido
        ou se o usuário autenticado for o responsável do restaurante onde esse pedido foi feito
        vai ter autorização para acessar esse método */
        @PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('CONSULTAR_PEDIDOS') "
                    + "or (@algaSecurity.verificaSePedidoPertenceAoUsuario(#filtro.clienteId) "
                    + "    or @algaSecurity.gerenciaRestaurante(#filtro.restauranteId))")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodePesquisar{ }


/**     Apenas quem tiver o scope 'SCOPE_READ' e também esteja autenticado vai ter autorização para acessar nesse método.
        <br>
        Após a execução do método apenas quem tiver a permissão 'CONSULTAR_PEDIDOS'
        ou se o usuário autenticado for o cliente desse pedido
        ou se o usuário autenticado for o responsável do restaurante onde esse pedido foi feito
        terão autorização para receber o retorno desse método */
        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")

//      É usada para aplicar autorização após a execução de um método
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


/**     Apenas quem tiver o scope 'SCOPE_WRITE' e tiver a permissão 'GERENCIAR_PEDIDOS'
        ou se o usuário autenticado for o responsável do restaurante onde esse pedido foi feito
        terão autorização para receber o retorno desse método*/
        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('GERENCIAR_PEDIDOS') "
                    + " or @algaSecurity.gerenciaPedidosDoRestaurante(#codigoPedido)")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeAlterarStatus { }

/**     Apenas quem tiver o scope 'SCOPE_WRITE'
        e também esteja autenticado vai ter autorização para acessar nesse método */
        @PreAuthorize("hasAuthority('SCOPE_WRITE') and isAuthenticated()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeCriar { }


    }


    @interface FormasPagamento {

/**     Apenas quem tiver o scope 'SCOPE_READ'
        e também esteja autenticado vai ter autorização para acessar nesse método */
        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeConsultar{ }


/**     Apenas quem tiver o scope 'SCOPE_WRITE'
        e tiver a permissão 'EDITAR_FORMAS_PAGAMENTO' vai ter autorização para acessar nesse método */
        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_FORMAS_PAGAMENTO') ")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeEditar{ }

    }


    @interface Cidades {

/**     Apenas quem tiver o scope 'SCOPE_READ'
        e também esteja autenticado vai ter autorização para acessar nesse método */
        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeConsultar{ }


/**     Apenas quem tiver o scope 'SCOPE_WRITE'
        e tiver a permissão 'EDITAR_CIDADES' vai ter autorização para acessar nesse método */
        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_CIDADES') ")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeEditar{ }

    }


    @interface Estados {

/**     Apenas quem tiver o scope 'SCOPE_READ'
        e também esteja autenticado vai ter autorização para acessar nesse método */
        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeConsultar{ }


/**     Apenas quem tiver o scope 'SCOPE_WRITE'
        e tiver a permissão 'EDITAR_CIDADES' vai ter autorização para acessar nesse método */
        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_ESTADOS') ")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeEditar{ }

    }


    @interface Grupos{

/**     Apenas quem tiver o scope 'SCOPE_READ'
        e tiver a permissão 'CONSULTAR_USUARIOS_GRUPOS_PERMISSOES' vai ter autorização para acessar nesse método */
        @PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('CONSULTAR_USUARIOS_GRUPOS_PERMISSOES')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeConsultar{ }


/**     Apenas quem tiver o scope 'SCOPE_WRITE'
        e tiver a permissão 'EDITAR_USUARIOS_GRUPOS_PERMISSOES' vai ter autorização para acessar nesse método */
        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_USUARIOS_GRUPOS_PERMISSOES') ")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeEditar{ }


/**     Apenas quem tiver o scope 'SCOPE_WRITE'
        e tiver a permissão 'EDITAR_USUARIOS_GRUPOS_PERMISSOES' vai ter autorização para acessar nesse método */
        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_USUARIOS_GRUPOS_PERMISSOES') ")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeAssociarEDesassociarPermissao{ }

    }


    @interface Permissoes {

/**     Apenas quem tiver o scope 'SCOPE_READ'
        e tiver a permissão 'CONSULTAR_USUARIOS_GRUPOS_PERMISSOES' vai ter autorização para acessar nesse método */
        @PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('CONSULTAR_USUARIOS_GRUPOS_PERMISSOES')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeConsultar{ }

    }


    @interface Usuarios{

/**     Apenas quem tiver o scope 'SCOPE_READ'
        e tiver a permissão 'CONSULTAR_USUARIOS_GRUPOS_PERMISSOES' vai ter autorização para acessar nesse método */
        @PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('CONSULTAR_USUARIOS_GRUPOS_PERMISSOES') "
                    + "or @algaSecurity.getUsuarioId() == #usuarioId")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeConsultar{ }


/**     Apenas quem tiver o scope 'SCOPE_WRITE'
        e tiver a permissão 'EDITAR_USUARIOS_GRUPOS_PERMISSOES' vai ter autorização para acessar nesse método */
        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_USUARIOS_GRUPOS_PERMISSOES') ")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeEditar{ }


/**     Apenas quem tiver o scope 'SCOPE_WRITE'
        e também o usuário autenticado for o próprio usuarioId vai ter autorização para acessar nesse método */
        @PreAuthorize("hasAuthority('SCOPE_WRITE') and @algaSecurity.getUsuarioId() == #usuarioId")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeAlterarPropriaSenha{ }


/**     Apenas quem tiver o scope 'SCOPE_WRITE'
        e tiver a permissão 'EDITAR_USUARIOS_GRUPOS_PERMISSOES'
        ou o usuário autenticado for o próprio usuarioId vai ter autorização para acessar nesse método*/
        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_USUARIOS_GRUPOS_PERMISSOES') "
                    + " or @algaSecurity.getUsuarioId() == #usuarioId")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeAlterarUsuario{ }

    }


    @interface Estatisticas {

/**     Apenas quem tiver o scope 'SCOPE_READ'
        e também tiver a permissão 'GERAR_RELATORIOS' vai ter autorização para acessar nesse método */
        @PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('GERAR_RELATORIOS') ")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeConsultar { }
    }
}
