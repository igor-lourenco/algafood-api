package com.algaworks.algafood.utils;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@UtilityClass
public class ControllerUtils {

    public static void adicionaUriNoHeaderDaResposta(Object id) {

        URI uri = ServletUriComponentsBuilder // UriComponentsBuilder com métodos de fábrica estáticos adicionais para criar links com base no HttpServletRequest atual.
            .fromCurrentRequest()             //  Cria um construtor de URI baseado na URI da requisição HTTP atual.
            .path("/{id}")                    //  Adiciona /{id} à URI atual para indicar o ID do novo recurso
            .buildAndExpand(id)               // Substitui {id} pelo valor do parâmetro id fornecido, criando a URI completa para o novo recurso.
            .toUri();                         // Converte o resultado em um objeto URI

        HttpServletResponse response = ((ServletRequestAttributes)
            RequestContextHolder.getRequestAttributes()) // Recupera os atributos da requisição atual (no Spring, isso usa o contexto da requisição atual).
            .getResponse();                              // Obtém o objeto HttpServletResponse da requisição atual.

        response.setHeader(HttpHeaders.LOCATION, uri.toString());
    }
}
