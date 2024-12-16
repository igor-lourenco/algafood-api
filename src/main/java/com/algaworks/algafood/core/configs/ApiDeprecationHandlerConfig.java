package com.algaworks.algafood.core.configs;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Essa classe extende HandlerInterceptorAdapter para usar interceptores que permite manipular requisições antes de chegarem ao controlador. */
@Component
public class ApiDeprecationHandlerConfig extends HandlerInterceptorAdapter {

/*  Esse método é executado antes do processamento da requisição. */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

/*      Lógica para verificar se a URI da requisição começa com "/v1/", se sim, adiciona o cabeçalho de depreciação na resposta. */
        if(request.getRequestURI().startsWith("/v1/")){
            response.addHeader("X-Algafood-Deprecated", "Essa versão da API está depreciada e deixará de existir a partir de 01/01/2027. "
                + "Use a versão mais atual da API");


        }


/*      Exemplo alternativo de como desligar uma versão da API da aplicação. */
/*      Essa lógica verifica se a URI da requisição termina com "/v1/cidades" e for do verbo "GET", se sim, retorna status code 410 */
        if (request.getRequestURI().endsWith("/v1/cidades") && request.getMethod().equals("GET")) {

            response.setStatus(HttpStatus.GONE.value()); // 410 -> Recurso solicitado foi removido permanentemente do servidor e não está mais disponível.
            return false;
        }

        return true;
    }
}
