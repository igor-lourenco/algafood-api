package com.algaworks.algafood.api.assemblers.DTOs;

import com.algaworks.algafood.api.DTOs.RootEntryPointDTO;
import com.algaworks.algafood.api.assemblers.links.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RootEntryPointDTOAssemblerV2 {

    @Autowired
    private CozinhaLinksV2 cozinhaLinksV2;
    @Autowired
    private CidadeLinksV2 cidadeLinksV2;

    public RootEntryPointDTO toModel(){
        RootEntryPointDTO rootEntryPointDTO = new RootEntryPointDTO();

        rootEntryPointDTO.add(cozinhaLinksV2.addCollectionLink("cozinhas"));
        rootEntryPointDTO.add(cidadeLinksV2.addCollectionLink("cidades"));

        return rootEntryPointDTO;
    }

}
