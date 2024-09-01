package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.DTOs.CidadeDTO;
import com.algaworks.algafood.api.assemblers.DTOs.CidadeDTOAssembler;
import com.algaworks.algafood.api.assemblers.CidadeModelAssembler;
import com.algaworks.algafood.api.inputs.CidadeInput;
import com.algaworks.algafood.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.models.CidadeModel;
import com.algaworks.algafood.domain.repositories.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private CidadeDTOAssembler cidadeDTOAssembler;
    @Autowired
    private CidadeModelAssembler cidadeModelAssembler;

    public List<CidadeDTO> listar(){
        List<CidadeModel> listaCidades  = cidadeRepository.findAll();
        List<CidadeDTO> cidadeDTOs = listaCidades.stream().map(cidadeModel -> cidadeDTOAssembler.convertToCidadeDTOBuilder(cidadeModel).build())
            .collect(Collectors.toList());

        return cidadeDTOs;
    }

    public CidadeDTO buscaPorId(Long id){
        CidadeModel cidadeModel = cidadeRepository.findById(id).orElseThrow(() ->
            new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de Cidade com id: %d", id)));

        CidadeDTO cidadeDTO = cidadeDTOAssembler.convertToCidadeDTOBuilder(cidadeModel).build();
        return cidadeDTO;
    }

    public List<CidadeDTO> consultaPorNome(String nome) {
        List<CidadeModel> listaConsultaPorNome = cidadeRepository.consultaPorNome(nome);
        List<CidadeDTO> cidadeDTOs = listaConsultaPorNome.stream().map(cidadeModel -> cidadeDTOAssembler.convertToCidadeDTOBuilder(cidadeModel).build())
            .collect(Collectors.toList());

        return cidadeDTOs;
    }


    @Transactional // Se der tudo certo e não lançar nenhuma exception na transação, dá um commit no banco, senão dá rollback para manter a consistência no banco
    public CidadeDTO salvar(CidadeInput cidadeInput){
        CidadeModel cidadeModel = new CidadeModel();
        cidadeModelAssembler.convertToCidadeModel(cidadeInput, cidadeModel);

        cidadeModel = cidadeRepository.save(cidadeModel);

        CidadeDTO cidadeDTO = cidadeDTOAssembler.convertToCidadeDTOBuilder(cidadeModel).build();
        return cidadeDTO;
    }


    @Transactional // Se der tudo certo e não lançar nenhuma exception na transação, dá um commit no banco, senão dá rollback para manter a consistência no banco
    public CidadeDTO alterar(Long id, CidadeInput cidadeInput){
        CidadeModel cidadeModel = cidadeRepository.findById(id).orElseThrow(() ->
            new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de Cidade com id: %d", id)));

        cidadeModelAssembler.convertToCidadeModel(cidadeInput, cidadeModel);
        cidadeModel = cidadeRepository.save(cidadeModel);

        CidadeDTO cidadeDTO = cidadeDTOAssembler.convertToCidadeDTOBuilder(cidadeModel).build();
        return cidadeDTO;
    }


//    @Transactional // Se der tudo certo e não lançar nenhuma exception na transação, dá um commit no banco, senão dá rollback para manter a consistência no banco
    public void deletar(Long id) {
        try {
            cidadeRepository.deleteById(id);
            cidadeRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            System.out.println("ERROR: " + e.getMessage());
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de cidade com código: %d", id));
        } catch (DataIntegrityViolationException e) {
            System.out.println("ERROR: " + e.getMessage());
            throw new EntidadeEmUsoException(String.format("Cidade de código: %d não pode ser removida, pois está em uso.", id));
        }
    }


}
