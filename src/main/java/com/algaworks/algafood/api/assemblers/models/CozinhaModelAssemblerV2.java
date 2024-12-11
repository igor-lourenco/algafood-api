package com.algaworks.algafood.api.assemblers.models;

import com.algaworks.algafood.api.inputs.CozinhaInputV2;
import com.algaworks.algafood.domain.models.CozinhaModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CozinhaModelAssemblerV2 {

    @Autowired
    private ModelMapper modelMapper;

    /** Converte classe CozinhaInputV2 para classe CozinhaModel */
    public void convertToCozinhaModel(CozinhaInputV2 cozinhaInputV2, CozinhaModel cozinhaModel){

        modelMapper.map(cozinhaInputV2, cozinhaModel);
    }
}
