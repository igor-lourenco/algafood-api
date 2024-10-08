package com.algaworks.algafood.requestClients;

import lombok.AllArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class RestauranteRequestClient {
    private static final String RESOURCE_PATH = "/restaurantes";

    private String url;
    private RestTemplate restTemplate;

    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();
        String baseUrl = "http://localhost:8080";

        RestauranteRequestClient requestClient = new RestauranteRequestClient(baseUrl, restTemplate);

        var response = requestClient.listar();
        response.forEach(System.out::println);


    }


    public List<RestauranteResumoRequestClientDTO> listar(){
        URI resourceUri = URI.create(url + RESOURCE_PATH);

        RestauranteResumoRequestClientDTO[] restaurantes = restTemplate
            .getForObject(resourceUri, RestauranteResumoRequestClientDTO[].class);

        return Arrays.asList(restaurantes);
    }
}
