package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.models.CozinhaModel;
import com.algaworks.algafood.domain.models.RestauranteModel;
import com.algaworks.algafood.infrastructure.repositories.CozinhaRepository;
import com.algaworks.algafood.infrastructure.repositories.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    CozinhaRepository cozinhaRepository;


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
        Optional<CozinhaModel> cozinhaOptional = cozinhaRepository.findById(restauranteModel.getCozinha().getId());

        if(cozinhaOptional.isEmpty()){
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de cozinha com código: %d", restauranteModel.getCozinha().getId()));

        }

        restauranteModel.setCozinha(cozinhaOptional.get());
        restauranteModel = restauranteRepository.save(restauranteModel);

        return restauranteModel;
    }

    public RestauranteModel alterar(Long id, RestauranteModel restauranteModel){
        Optional<RestauranteModel> restauranteOptional = restauranteRepository.findById(id);
        Optional<CozinhaModel> cozinhaOptional = cozinhaRepository.findById(restauranteModel.getCozinha().getId());


        if(restauranteOptional.isEmpty()){
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de restaurante com código: %d", id));
        }
        if(cozinhaOptional.isEmpty()){
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de cozinha com código: %d", restauranteModel.getCozinha().getId()));

        }

        RestauranteModel obj = restauranteOptional.get();
        obj.setNome(restauranteModel.getNome());
        obj = restauranteRepository.save(obj);

        return obj;
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
}
