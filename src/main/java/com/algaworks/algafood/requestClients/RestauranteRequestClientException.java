package com.algaworks.algafood.requestClients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClientResponseException;

import java.util.ArrayList;
import java.util.List;

@Getter
@Slf4j
public class RestauranteRequestClientException extends RuntimeException {

    private StandardError standardError;

    public RestauranteRequestClientException(String message, RestClientResponseException cause) {
        super(message, cause);

        deserializeStandardError(cause);
    }


    public void deserializeStandardError(RestClientResponseException e) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());

        objectMapper.findAndRegisterModules();

        try {
            this.standardError = objectMapper.readValue(e.getResponseBodyAsString(), StandardError.class);
        } catch (JsonProcessingException ex) {
            log.warn("Não foi possível desserializar a resposta em uma StandardError.class :: " + ex.getMessage());
        }
    }
}


@Data
class StandardError {
    private Integer status;
    private String timestamp;
    private String detail;

    private List<Object> objects = new ArrayList<>();

    @Data
    public static class Object {

        private String name;
        private String userMessage;

    }
}