package com.algaworks.algafood.api.assemblers;

import com.algaworks.algafood.api.DTOs.FormaPagamentoDTO;
import com.algaworks.algafood.api.DTOs.GrupoDTO;
import com.algaworks.algafood.domain.models.FormaPagamentoModel;
import com.algaworks.algafood.domain.models.GrupoModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GrupoDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    /** Converte classe GrupoModel para classe GrupoDTO.GrupoDTOBuilder */
    public GrupoDTO.GrupoDTOBuilder convertToGrupoDTOBuilder(GrupoModel grupoModel) {

        GrupoDTO grupoDTO = modelMapper.map(grupoModel, GrupoDTO.class);
        return grupoDTO.toBuilder(); // retorna builder a partir de uma instância existente, para adicionar mais campos caso quem chama esse método tiver necessidade
    }
}
