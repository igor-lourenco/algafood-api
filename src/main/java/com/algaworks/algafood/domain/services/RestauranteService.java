package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.models.CozinhaModel;
import com.algaworks.algafood.domain.models.RestauranteModel;
import com.algaworks.algafood.domain.repositories.CozinhaRepository;
import com.algaworks.algafood.domain.repositories.RestauranteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    CozinhaService cozinhaService;


    public List<RestauranteModel> listar(){
        List<RestauranteModel> listaRestaurantes  = restauranteRepository.findAll();

        return listaRestaurantes;
    }

    public RestauranteModel buscaPorId(Long id){
        Optional<RestauranteModel> restauranteOptional = restauranteRepository.findById(id);

        if(restauranteOptional.isEmpty()){
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de restaurante com código: %d", id));
        }

        return restauranteOptional.get();
    }

    public RestauranteModel salvar(RestauranteModel restauranteModel){
        CozinhaModel cozinhaModel = cozinhaService.buscaPorId(restauranteModel.getCozinha().getId());

        restauranteModel.setCozinha(cozinhaModel);
        restauranteModel = restauranteRepository.save(restauranteModel);

        return restauranteModel;
    }

    public RestauranteModel alterar(Long id, RestauranteModel restauranteModel){
        RestauranteModel restaurante = buscaPorId(id);
        CozinhaModel cozinhaModel = cozinhaService.buscaPorId(restauranteModel.getCozinha().getId());

        restaurante.setNome(restauranteModel.getNome());
        restaurante.setCozinha(cozinhaModel);
        restaurante = restauranteRepository.save(restaurante);

        return restaurante;
    }

    public RestauranteModel alterarParcial(Long id,  Map<String, Object> campos){
        RestauranteModel restaurante = buscaPorId(id);

        merge(campos, restaurante);

        restaurante = restauranteRepository.save(restaurante);

        return restaurante;
    }

    public void deletar(Long id) {
        try {
            restauranteRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            System.out.println("ERROR: " + e.getMessage());
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de restaurante com código: %d", id));
        } catch (DataIntegrityViolationException e) {
            System.out.println("ERROR: " + e.getMessage());
            throw new EntidadeEmUsoException(String.format("Restaurante de código: %d não pode ser removida, pois está em uso.", id));
        }
    }


    private void merge(Map<String, Object> objOrigem, RestauranteModel restauranteDestino){
        ObjectMapper objectMapper = new ObjectMapper();
        RestauranteModel restauranteOrigem = objectMapper.convertValue(objOrigem, RestauranteModel.class); // converte o objOrigem para uma instância de RestauranteModel

        objOrigem.forEach((campo, valor) -> {

            Field field = ReflectionUtils.findField(RestauranteModel.class, campo); // busca o campo preenchido correspondente no RestauranteModel
            field.setAccessible(true); // deixa o atributo do objeto privado acessível

            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem); // Obtém o valor do campo do objeto 'restauranteOrigem'.

            ReflectionUtils.setField(field, restauranteDestino, novoValor);  // atribui o valor do campo preenchido ao campo do objeto destino com o 'novoValor' convertido

        });
    }

    public List<RestauranteModel> findAll(Specification<RestauranteModel> and) {

        return restauranteRepository.findAll(and);
    }
}
