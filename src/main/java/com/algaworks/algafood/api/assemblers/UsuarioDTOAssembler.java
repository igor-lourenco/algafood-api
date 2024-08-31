package com.algaworks.algafood.api.assemblers;

import com.algaworks.algafood.api.DTOs.UsuarioDTO;
import com.algaworks.algafood.domain.models.UsuarioModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    /** Converte classe UsuarioModel para classe UsuarioDTO.UsuarioDTOBuilder */
    public UsuarioDTO.UsuarioDTOBuilder convertToUsuarioDTOBuilder(UsuarioModel usuarioModel) {

        UsuarioDTO usuarioDTO = modelMapper.map(usuarioModel, UsuarioDTO.class);
        return usuarioDTO.toBuilder(); // retorna builder a partir de uma instância existente, para adicionar mais campos caso quem chama esse método tiver necessidade
    }
}
