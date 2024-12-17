package com.algaworks.algafood.requestClients;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Log4j2
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

            log.info(">>>>>>>>>>>>> GET <<<<<<<<<<<<<<<<<<<<<");
            response.forEach(log::info);
            log.info(">>>>>>>>>>>>>>>>>>>>");

//          >>>>>>>>>>>>> POST <<<<<<<<<<<<<<<<<<<<<

            var restaurante = getRestauranteSaveRequestClientInput();

            var restauranteModel = requestClient.adiciona(restaurante);

            log.info(">>>>>>>>>>>>> POST <<<<<<<<<<<<<<<<<<<<<");
            log.info(restauranteModel);
            log.info(">>>>>>>>>>>>>>>>>>>>");

        } catch (RestauranteRequestClientException e) {

            if (e.getStandardError() != null) {
                log.error("Detail :: {}", e.getStandardError().getDetail());

                e.getStandardError().getObjects()
                    .forEach(p -> log.error("MessageError :: {}", p.getUserMessage()));


            } else {
                log.error("Erro desconhecido: {}", e.getMessage());
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
            log.error("ERROR :: {}", e.getMessage());
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
            log.error("ERROR :: {}", e.getMessage());
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
