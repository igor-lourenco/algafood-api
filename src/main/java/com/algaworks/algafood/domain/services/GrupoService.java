package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.DTOs.GrupoDTO;
import com.algaworks.algafood.api.assemblers.DTOs.GrupoDTOAssembler;
import com.algaworks.algafood.api.assemblers.models.GrupoModelAssembler;
import com.algaworks.algafood.api.inputs.GrupoInput;
import com.algaworks.algafood.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.models.GrupoModel;
import com.algaworks.algafood.domain.repositories.GrupoRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
public class GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;
    @Autowired
    private GrupoDTOAssembler grupoDTOAssembler;
    @Autowired
    private GrupoModelAssembler grupoModelAssembler;

    @Transactional(readOnly = true)
    public CollectionModel<GrupoDTO> lista(){
        List<GrupoModel> grupoModels = grupoRepository.findAll();
        return  grupoDTOAssembler.toCollectionModel(grupoModels);
    }


    @Transactional(readOnly = true)
    public CollectionModel<GrupoDTO> consultaPorNome(String nome) {
        List<GrupoModel> grupoModels = grupoRepository.consultaPorNome(nome);
        return  grupoDTOAssembler.toCollectionModel(grupoModels);
    }

    @Transactional(readOnly = true)
    public GrupoDTO findById(Long id) {
        GrupoModel grupoModel = findGrupoModelById(id);
        return grupoDTOAssembler.convertToGrupoDTO(grupoModel);
    }


    @Transactional
    public GrupoDTO salva(GrupoInput grupoInput) {
        GrupoModel grupoModel = new GrupoModel();

        grupoModelAssembler.convertToGrupoModel(grupoInput, grupoModel);
        grupoRepository.save(grupoModel);

        return grupoDTOAssembler.convertToGrupoDTO(grupoModel);
    }

    @Transactional
    public GrupoDTO altera(Long id, GrupoInput grupoInput) {
        GrupoModel grupoModel = findGrupoModelById(id);

        grupoModelAssembler.convertToGrupoModel(grupoInput, grupoModel);
        grupoRepository.save(grupoModel);

        return grupoDTOAssembler.convertToGrupoDTO(grupoModel);
    }


    @Transactional
    public void deleta(Long id) {
        try {
            grupoRepository.deleteById(id);
            grupoRepository.flush();  // Libera todas as alterações pendentes no banco de dados e sincroniza as alterações com o banco de dados

        } catch (EmptyResultDataAccessException e) {
            log.error("ERROR :: {}", e.getMessage());
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de grupo com código: %d", id));
        } catch (DataIntegrityViolationException e) {
            log.error("ERROR :: {}", e.getMessage());
            throw new EntidadeEmUsoException(String.format("Grupo do código: %d não pode ser removido, pois está em uso.", id));
        }
    }


    protected GrupoModel findGrupoModelById(Long id) {
        return grupoRepository.findById(id).orElseThrow(() ->
            new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de grupo com código: %d", id)));
    }

}
