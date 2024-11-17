package com.algaworks.algafood.api.assemblers.models;

import com.algaworks.algafood.api.inputs.GrupoInput;
import com.algaworks.algafood.api.inputs.UsuarioComSenhaInput;
import com.algaworks.algafood.api.inputs.UsuarioInput;
import com.algaworks.algafood.domain.models.GrupoModel;
import com.algaworks.algafood.domain.models.UsuarioModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class UsuarioModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    /** Converte classe UsuarioComSenhaInput para classe UsuarioModel */
    public void convertToUsuarioModel(UsuarioComSenhaInput usuarioInput, UsuarioModel usuarioModel){

        modelMapper.map(usuarioInput, usuarioModel);

    }

    /** Converte classe UsuarioInput para classe UsuarioModel */
    public void convertToUsuarioModel(UsuarioInput usuarioInput, UsuarioModel usuarioModel){

        modelMapper.map(usuarioInput, usuarioModel);

    }
}
