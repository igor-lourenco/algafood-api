package com.algaworks.algafood.api.assemblers;

import com.algaworks.algafood.api.inputs.FormaPagamentoInput;
import com.algaworks.algafood.api.inputs.GrupoInput;
import com.algaworks.algafood.domain.models.FormaPagamentoModel;
import com.algaworks.algafood.domain.models.GrupoModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GrupoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    /** Converte classe FormaPagamentoInput para classe FormaPagamentoModel */
    public void convertToGrupoModel(GrupoInput grupoInput, GrupoModel grupoModel){

        modelMapper.map(grupoInput, grupoModel);

    }
}
