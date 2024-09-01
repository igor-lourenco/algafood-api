package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.DTOs.CozinhaDTO;
import com.algaworks.algafood.api.assemblers.DTOs.CozinhaDTOAssembler;
import com.algaworks.algafood.api.assemblers.CozinhaModelAssembler;
import com.algaworks.algafood.api.inputs.CozinhaInput;
import com.algaworks.algafood.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.models.CozinhaModel;
import com.algaworks.algafood.domain.repositories.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;
    @Autowired
    private CozinhaModelAssembler cozinhaModelAssembler;
    @Autowired
    private CozinhaDTOAssembler cozinhaDTOAssembler;


    public List<CozinhaDTO> listar(){
        List<CozinhaModel> listaCozinhas  = cozinhaRepository.findAll();
        List<CozinhaDTO> cozinhaDTOS = listaCozinhas.stream().map(cozinha ->
            cozinhaDTOAssembler.convertToCozinhaDTOBuilder(cozinha).build()).collect(Collectors.toList());

        return cozinhaDTOS;
    }

    public CozinhaDTO buscaPorId(Long id){
        CozinhaModel cozinhaModel = cozinhaRepository.findById(id).orElseThrow(() ->
            new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de Cozinha com id: %d", id)));

        CozinhaDTO cozinhaDTO = cozinhaDTOAssembler.convertToCozinhaDTOBuilder(cozinhaModel).build();
        return cozinhaDTO;
    }

    public List<CozinhaDTO> consultaPorNome(String nome) {
        List<CozinhaModel> listaConsultaPorNome = cozinhaRepository.consultaPorNome(nome);
        List<CozinhaDTO> cozinhaDTOS = listaConsultaPorNome.stream().map(cozinha ->
            new CozinhaDTO(cozinha.getId(), cozinha.getNome())).collect(Collectors.toList());

        return  cozinhaDTOS;
    }


    @Transactional // Se der tudo certo e não lançar nenhuma exception na transação, dá um commit no banco, senão dá rollback para manter a consistência no banco
    public CozinhaDTO salvar(CozinhaInput cozinhaInput){
        CozinhaModel cozinhaModel = new CozinhaModel();
        cozinhaModelAssembler.convertToCozinhaModel(cozinhaInput, cozinhaModel);

        cozinhaModel = cozinhaRepository.save(cozinhaModel);

        CozinhaDTO cozinhaDTO = cozinhaDTOAssembler.convertToCozinhaDTOBuilder(cozinhaModel).build();
        return cozinhaDTO;
    }


    @Transactional // Se der tudo certo e não lançar nenhuma exception na transação, dá um commit no banco, senão dá rollback para manter a consistência no banco
    public CozinhaDTO alterar(Long id, CozinhaInput cozinhaInput){
        CozinhaModel cozinhaModel = cozinhaRepository.findById(id).orElseThrow(() ->
            new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de Cozinha com id: %d", id)));

        cozinhaModelAssembler.convertToCozinhaModel(cozinhaInput, cozinhaModel);
        cozinhaModel = cozinhaRepository.save(cozinhaModel);

        CozinhaDTO cozinhaDTO = cozinhaDTOAssembler.convertToCozinhaDTOBuilder(cozinhaModel).build();
        return cozinhaDTO;
    }


    @Transactional // Se der tudo certo e não lançar nenhuma exception na transação, dá um commit no banco, senão dá rollback para manter a consistência no banco
    public void deletar(Long id) {
        try {
            cozinhaRepository.deleteById(id);
            cozinhaRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            System.out.println("ERROR: " + e.getMessage());
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de cozinha com código: %d", id));
        } catch (DataIntegrityViolationException e) {
            System.out.println("ERROR: " + e.getMessage());
            throw new EntidadeEmUsoException(String.format("Cozinha de código: %d não pode ser removida, pois está em uso.", id));
        }
    }

}
