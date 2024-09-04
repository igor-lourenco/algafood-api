package com.algaworks.algafood.api.assemblers.DTOs;

import com.algaworks.algafood.api.DTOs.GrupoPermissaoDTO;
import com.algaworks.algafood.domain.models.PermissaoModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GrupoPermissaoDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    /** Converte classe GrupoModel para classe GrupoDTO.GrupoDTOBuilder */
    public GrupoPermissaoDTO.GrupoPermissaoDTOBuilder convertToGrupoPermissaoDTOBuilder(PermissaoModel permissaoModel) {

        GrupoPermissaoDTO grupoDTO = modelMapper.map(permissaoModel, GrupoPermissaoDTO.class);
        return grupoDTO.toBuilder(); // retorna builder a partir de uma instância existente, para adicionar mais campos caso quem chama esse método tiver necessidade
    }
}
