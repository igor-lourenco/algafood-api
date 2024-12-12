package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.DTOs.RootEntryPointDTO;
import com.algaworks.algafood.api.assemblers.DTOs.RootEntryPointDTOAssemblerV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RootEntryPointServiceV2 {

    @Autowired
    private RootEntryPointDTOAssemblerV2 rootEntryPointDTOAssemblerV2;

    public RootEntryPointDTO rootEntryPointV2() {
        return rootEntryPointDTOAssemblerV2.toModel();
    }
}
