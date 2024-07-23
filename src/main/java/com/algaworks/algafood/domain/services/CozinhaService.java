package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.models.CozinhaModel;
import com.algaworks.algafood.infrastructure.repositories.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;


    public List<CozinhaModel> listar(){
        List<CozinhaModel> listaCozinhas  = cozinhaRepository.findAll();
        return listaCozinhas;
    }

    public CozinhaModel buscaPorId(Long id){
        Optional<CozinhaModel> cozinhaOptional = cozinhaRepository.findById(id);

        if(cozinhaOptional.isEmpty()){
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de cozinha com código: %d", id));
        }

        return cozinhaOptional.get();
    }

    public CozinhaModel salvar(CozinhaModel cozinha){
        cozinha = cozinhaRepository.save(cozinha);
        return cozinha;
    }

    public CozinhaModel alterar(Long id, CozinhaModel cozinha){
        CozinhaModel cozinhaModel = buscaPorId(id);

        cozinhaModel.setNome(cozinha.getNome());
        cozinhaModel = cozinhaRepository.save(cozinhaModel);

        return cozinhaModel;
    }

    public void deletar(Long id) {
        try {
            cozinhaRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            System.out.println("ERROR: " + e.getMessage());
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de cozinha com código: %d", id));
        } catch (DataIntegrityViolationException e) {
            System.out.println("ERROR: " + e.getMessage());
            throw new EntidadeEmUsoException(String.format("Cozinha de código: %d não pode ser removida, pois está em uso.", id));
        }
    }
}
