package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.DTOs.PermissaoDTO;
import com.algaworks.algafood.api.assemblers.DTOs.PermissaoDTOAssembler;
import com.algaworks.algafood.domain.models.PermissaoModel;
import com.algaworks.algafood.domain.repositories.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;
    @Autowired
    private PermissaoDTOAssembler permissaoDTOAssembler;


    @Transactional(readOnly = true)
    public CollectionModel<PermissaoDTO> findAllPermissoes(){
        List<PermissaoModel> permissaoModels = permissaoRepository.findAll();
        return  permissaoDTOAssembler.toCollectionModel(permissaoModels);
    }

}
