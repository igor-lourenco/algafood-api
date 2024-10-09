package com.algaworks.algafood.requestClients;

import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
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

//          >>>>>>>>>>>>> GET <<<<<<<<<<<<<<<<<<<<<

            var response = requestClient.lista();

            System.out.println(">>>>>>>>>>>>> GET <<<<<<<<<<<<<<<<<<<<<");
            response.forEach(System.out::println);
            System.out.println(">>>>>>>>>>>>>>>>>>>>\n");

//          >>>>>>>>>>>>> POST <<<<<<<<<<<<<<<<<<<<<

            var restaurante = getRestauranteSaveRequestClientInput();

            var restauranteModel = requestClient.adiciona(restaurante);

            System.out.println(">>>>>>>>>>>>> POST <<<<<<<<<<<<<<<<<<<<<");
            System.out.println(restauranteModel);
            System.out.println(">>>>>>>>>>>>>>>>>>>>\n");

        } catch (RestauranteRequestClientException e) {

            if (e.getStandardError() != null) {
                System.out.println("\nDetail :: " + e.getStandardError().getDetail());

                e.getStandardError().getObjects()
                    .forEach(p -> System.out.println("MessageError :: " + p.getUserMessage()));


            } else {
                System.out.println("Erro desconhecido: " + e.getMessage());
            }
        }

    }


    public List<RestauranteResumoRequestClientDTO> lista() {

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


    public RestauranteSaveRequestClientDTO adiciona(RestauranteSaveRequestClientInput restaurante) {
        var resourceUri = URI.create(url + RESOURCE_PATH);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<RestauranteSaveRequestClientInput> entity = new HttpEntity<>(restaurante, headers);

        try {
            return restTemplate.postForObject(resourceUri, entity, RestauranteSaveRequestClientDTO.class);

        } catch (HttpClientErrorException e) {
            throw new RestauranteRequestClientException(e.getMessage(), e);
        }
    }


    private static RestauranteSaveRequestClientInput getRestauranteSaveRequestClientInput() {
        var endereco = new EnderecoSaveRequestClientInput();
        endereco.setCep("38500-111");
        endereco.setLogradouro("Rua Xyz");
        endereco.setNumero("300");
        endereco.setBairro("Centro");
        endereco.setCidadeId(2L);

        var restaurante = new RestauranteSaveRequestClientInput();
        restaurante.setNome("Comida Mineira");
        restaurante.setPrecoFreteModelMapper(new BigDecimal("9.5"));
        restaurante.setCozinhaId(2L);
        restaurante.setEndereco(endereco);
        return restaurante;
    }

}
