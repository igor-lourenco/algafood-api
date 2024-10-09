package com.algaworks.algafood.requestClients;

import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class RestauranteRequestClient {
    private static final String RESOURCE_PATH = "/restaurantes";

    private String url;
    private RestTemplate restTemplate;

    public static void main(String[] args) {

        try {
            RestTemplate restTemplate = new RestTemplate();
            String baseUrl = "http://localhost:8080/";

            RestauranteRequestClient requestClient = new RestauranteRequestClient(baseUrl, restTemplate);

            var response = requestClient.listar();
            response.forEach(System.out::println);

        } catch (RestauranteRequestClientException e) {

            if (e.getStandardError() != null) {
                System.out.println(e.getStandardError().getDetail());
            } else {
                System.out.println("Erro desconhecido: " + e.getMessage());
            }
        }

    }


    public List<RestauranteResumoRequestClientDTO> listar() {

        try {
            URI resourceUri = URI.create(url + RESOURCE_PATH);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<RestauranteResumoRequestClientDTO[]> restaurantes = restTemplate.exchange(
                resourceUri, HttpMethod.GET, entity, RestauranteResumoRequestClientDTO[].class);

//          Se o 'restaurantes.getBody()' for null retorna a exception NullPointerException com a mensagem personalizada 'A resposta da requisição não pode ser null'
            return Arrays.asList(Objects.requireNonNull(restaurantes.getBody(), "A resposta da requisição não pode ser null"));
        } catch (RestClientResponseException e) {
            throw new RestauranteRequestClientException(e.getMessage(), e);
        }
    }
}
