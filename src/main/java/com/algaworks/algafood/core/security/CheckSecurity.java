package com.algaworks.algafood.core.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface CheckSecurity {

    @interface Cozinhas {

        /** Apenas quem tiver o scope 'SCOPE_READ' e também esteja autenticado vai ter autorização para acessar nesse método */
        @Retention(RetentionPolicy.RUNTIME)
        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @Target(ElementType.METHOD)
        @interface PodeConsultar {
        }

        /** Apenas quem tiver o scope 'SCOPE_WRITE' e também a permissão 'EDITAR_COZINHAS' vai ter autorização para acessar esse método */
        @Retention(RetentionPolicy.RUNTIME)
        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_COZINHAS')")
        @Target(ElementType.METHOD)
        @interface PodeEditar {
        }

    }

    @interface Restaurantes {

        /** Apenas quem tiver o scope 'SCOPE_READ' e também esteja autenticado vai ter autorização para acessar nesse método */
        @Retention(RetentionPolicy.RUNTIME)
        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @Target(ElementType.METHOD)
        @interface PodeConsultar {
        }

        /** Apenas quem tiver o scope 'SCOPE_WRITE' e também a permissão 'EDITAR_RESTAURANTES' vai ter autorização para acessar esse método */
        @Retention(RetentionPolicy.RUNTIME)
        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_RESTAURANTES')")
        @Target(ElementType.METHOD)
        @interface PodeEditar {
        }

    }

}
