package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.DTOs.GrupoDTO;
import com.algaworks.algafood.api.assemblers.DTOs.GrupoDTOAssembler;
import com.algaworks.algafood.api.assemblers.GrupoModelAssembler;
import com.algaworks.algafood.api.inputs.GrupoInput;
import com.algaworks.algafood.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.models.GrupoModel;
import com.algaworks.algafood.domain.repositories.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;
    @Autowired
    private GrupoDTOAssembler grupoDTOAssembler;
    @Autowired
    private GrupoModelAssembler grupoModelAssembler;

    @Transactional(readOnly = true)
    public List<GrupoDTO> lista(){
        List<GrupoModel> formaPagamentoModels = grupoRepository.findAll();

        List<GrupoDTO> formaPagamentoDTOS = formaPagamentoModels.stream()
            .map(grupoModel -> grupoDTOAssembler.convertToGrupoDTOBuilder(grupoModel).build())
            .collect(Collectors.toList());

        return  formaPagamentoDTOS;
    }


    public List<GrupoDTO> consultaPorNome(String nome) {
        List<GrupoModel> grupoModels = grupoRepository.consultaPorNome(nome);

        List<GrupoDTO> grupoDTOS = grupoModels.stream()
            .map(grupoModel -> grupoDTOAssembler.convertToGrupoDTOBuilder(grupoModel).build())
            .collect(Collectors.toList());

        return  grupoDTOS;
    }

    @Transactional(readOnly = true)
    public GrupoDTO findById(Long id) {
        GrupoModel grupoModel = findGrupoModelById(id);

        GrupoDTO grupoDTO = grupoDTOAssembler.convertToGrupoDTOBuilder(grupoModel).build();
        return grupoDTO;
    }


    @Transactional
    public GrupoDTO salva(GrupoInput grupoInput) {
        GrupoModel grupoModel = new GrupoModel();

        grupoModelAssembler.convertToGrupoModel(grupoInput, grupoModel);
        grupoRepository.save(grupoModel);

        GrupoDTO formaPagamentoDTO = grupoDTOAssembler.convertToGrupoDTOBuilder(grupoModel).build();
        return formaPagamentoDTO;
    }

    @Transactional
    public GrupoDTO altera(Long id, GrupoInput grupoInput) {
        GrupoModel grupoModel = findGrupoModelById(id);

        grupoModelAssembler.convertToGrupoModel(grupoInput, grupoModel);
        grupoRepository.save(grupoModel);

        GrupoDTO grupoDTO = grupoDTOAssembler.convertToGrupoDTOBuilder(grupoModel).build();
        return grupoDTO;
    }


    @Transactional
    public void deleta(Long id) {
        try {
            grupoRepository.deleteById(id);
            grupoRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            System.out.println("ERROR: " + e.getMessage());
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de grupo com código: %d", id));
        } catch (DataIntegrityViolationException e) {
            System.out.println("ERROR: " + e.getMessage());
            throw new EntidadeEmUsoException(String.format("Grupo do código: %d não pode ser removido, pois está em uso.", id));
        }
    }


    private GrupoModel findGrupoModelById(Long id) {
        return grupoRepository.findById(id).orElseThrow(() ->
            new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de grupo com código: %d", id)));
    }

}
