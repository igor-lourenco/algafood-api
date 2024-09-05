package com.algaworks.algafood.api.assemblers.DTOs;

import com.algaworks.algafood.api.DTOs.UsuarioDTO;
import com.algaworks.algafood.api.DTOs.UsuarioGrupoDTO;
import com.algaworks.algafood.domain.models.GrupoModel;
import com.algaworks.algafood.domain.models.UsuarioModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class UsuarioGrupoDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    /** Converte classe GrupoModel para classe UsuarioGrupoDTO.UsuarioGrupoDTOBuilder */
    public UsuarioGrupoDTO.UsuarioGrupoDTOBuilder convertToUsuarioDTOBuilder(GrupoModel grupoModel) {

//        UsuarioGrupoDTO usuarioDTO = modelMapper.map(grupoModel, UsuarioGrupoDTO.class);

        UsuarioGrupoDTO usuarioDTO = new UsuarioGrupoDTO();
        usuarioDTO.setId(grupoModel.getId());
        usuarioDTO.setNome((grupoModel.getNome()));

        return usuarioDTO.toBuilder(); // retorna builder a partir de uma instância existente, para adicionar mais campos caso quem chama esse método tiver necessidade
    }
}
