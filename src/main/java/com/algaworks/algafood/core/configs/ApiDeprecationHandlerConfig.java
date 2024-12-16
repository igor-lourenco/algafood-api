package com.algaworks.algafood.core.configs;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Essa classe extende HandlerInterceptorAdapter para usar interceptores que permite manipular requisições antes de chegarem ao controlador. */
@Component
public class ApiDeprecationHandlerConfig extends HandlerInterceptorAdapter {

/*  Esse método é executado antes do processamento da requisição. E foi implementado a lógica para verificar se a URI da
    requisição começa com "/v1/" se sim, adiciona o cabeçalho de depreciação na resposta. */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(request.getRequestURI().startsWith("/v1/")){
            response.addHeader("X-Algafood-Deprecated", "Essa versão da API está depreciada e deixará de existir a partir de 01/01/2027. "
                + "Use a versão mais atual da API");
        }

        return true;
    }
}
