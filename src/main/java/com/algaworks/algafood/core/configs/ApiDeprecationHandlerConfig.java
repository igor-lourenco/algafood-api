package com.algaworks.algafood.core.configs;

import com.algaworks.algafood.api.controllers.exceptions.StandardError;
import com.algaworks.algafood.api.controllers.exceptions.enums.ErrorTypeEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;

/** Essa classe extende HandlerInterceptorAdapter para usar interceptores que permite manipular requisições antes de chegarem ao controlador. */
@Component
@Log4j2
public class ApiDeprecationHandlerConfig implements HandlerInterceptor {

    @Autowired
    private ObjectMapper objectMapper;

/*  Esse método é executado antes do processamento da requisição. */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.warn("Verificando se a URI da requisição começa com '/v1/', se sim, adiciona o cabeçalho de depreciação na resposta.");

/*      Lógica para verificar se a URI da requisição começa com "/v1/", se sim, adiciona o cabeçalho de depreciação na resposta. */
        if(request.getRequestURI().startsWith("/v1/")){
        log.warn("Adicionando no cabeçalho de depreciação na resposta.");
            response.addHeader("X-Algafood-Deprecated", "Essa versão da API está depreciada e deixará de existir a partir de 01/01/2027. "
                + "Use a versão mais atual da API");
        }


/*      Exemplo alternativo de como desligar uma versão da API da aplicação. */
/*      Essa lógica verifica se a URI da requisição termina com "/v1/cidades" e for do verbo "GET", se sim, retorna status code 410 */
        if (request.getRequestURI().endsWith("/v1/cidades") && request.getMethod().equals("GET")) {
            log.warn("A API '/v1/cidades' é um exemplo de como depreciar uma API, é exemplo alternativo de como desligar uma versão da API da aplicação.");

            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(HttpStatus.GONE.value()); // 410 -> Recurso solicitado foi removido permanentemente do servidor e não está mais disponível.

            var error = StandardError.builder()
                .status(HttpStatus.GONE.value())
                .type(ErrorTypeEnum.RESOURCE_DEPRECATED.getUri())
                .title(ErrorTypeEnum.RESOURCE_DEPRECATED.getTitle())
                .detail("Recurso solicitado foi removido permanentemente do servidor e não está mais disponível")
                .timestamp(LocalDateTime.now())
                .build();

            String json = convertObjectToJson(error);

            response.getWriter().write(json);
            return false;
        }

        return true;
    }

    private String convertObjectToJson(Object obj) {
        try {
            objectMapper.registerModule(new JavaTimeModule());    // Adiciona suporte para serialização e desserialização de tipos de data e hora do Java 8 (por exemplo, LocalDate, LocalDateTime).
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // Força a não escrever datas como timestamps (Long), mas sim como String ("2024-07-12T16:29:23").
            String json = objectMapper.writeValueAsString(obj);
            return json;                    // Converte o objeto para JSON
        } catch (JsonProcessingException e) {
            log.error("ERROR  ao converter Object para JSON", e.getMessage());
            return obj.toString();
        }
    }
}
