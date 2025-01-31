package com.algaworks.algafood.core.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface CheckSecurity {


    @interface Cozinhas {

        /** Apenas quem tiver o scope 'SCOPE_READ'
         *  e também esteja autenticado vai ter autorização para acessar nesse método */
        @Retention(RetentionPolicy.RUNTIME)
        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @Target(ElementType.METHOD)
        @interface PodeConsultar { }


        /** Apenas quem tiver o scope 'SCOPE_WRITE'
         *  e também a permissão 'EDITAR_COZINHAS' vai ter autorização para acessar esse método */
        @Retention(RetentionPolicy.RUNTIME)
        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_COZINHAS')")
        @Target(ElementType.METHOD)
        @interface PodeEditar { }

    }


    @interface Restaurantes {

        /** Apenas quem tiver o scope 'SCOPE_READ'
         *  e também esteja autenticado vai ter autorização para acessar nesse método */
        @Retention(RetentionPolicy.RUNTIME)
        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @Target(ElementType.METHOD)
        @interface PodeConsultar { }


        /** Apenas quem tiver o scope 'SCOPE_WRITE'
         *  e também a permissão 'EDITAR_RESTAURANTES' vai ter autorização para acessar esse método */
        @Retention(RetentionPolicy.RUNTIME)
        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_RESTAURANTES')")
        @Target(ElementType.METHOD)
        @interface PodeGerenciarCadastro { }


        /** Apenas quem tiver o scope 'SCOPE_WRITE'
         *  e também a permissão 'EDITAR_RESTAURANTES'
         *  ou for o responsável por esse restaurante vai ter autorização para acessar esse método */
        @Retention(RetentionPolicy.RUNTIME)
        @PreAuthorize("hasAuthority('SCOPE_WRITE') "
                    +   "and (hasAuthority('EDITAR_RESTAURANTES') " +
                        "or @algaSecurity.gerenciaRestaurante(#restauranteId))")
        @Target(ElementType.METHOD)
        @interface PodeGerenciarFuncionamento {
            /*
               O '@' no SpEL(Spring Expression Language) é usado para acessar beans Spring ou variáveis de ambiente.
               Nesse caso essa anotação acessa o bean AlgaSecurity usando o @algaSecurity e dentro desse bean tem o
             método gerenciaRestaurante(Long restauranteId)


               O '#' é usado para acessar variáveis ou métodos definidos no contexto da expressão.
               Nesse caso está acessando a variável 'restauranteId' onde essa anotação está sendo chamada,
               por exemplo essa anotação está sendo chamada no RestauranteController
               no path PUT /{restauranteId}/ativa e no path DELETE /{restauranteId}/inativa

            */
        }

    }

}
