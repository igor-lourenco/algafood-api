package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.DTOs.RootEntryPointDTO;
import com.algaworks.algafood.api.assemblers.DTOs.RootEntryPointDTOAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RootEntryPointService {

    @Autowired
    private RootEntryPointDTOAssembler rootEntryPointDTOAssembler;

    public RootEntryPointDTO rootEntryPoint() {
        return rootEntryPointDTOAssembler.toModel();
    }
}
