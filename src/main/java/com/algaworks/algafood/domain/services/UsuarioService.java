package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.DTOs.GrupoDTO;
import com.algaworks.algafood.api.DTOs.UsuarioDTO;
import com.algaworks.algafood.api.assemblers.UsuarioDTOAssembler;
import com.algaworks.algafood.api.assemblers.UsuarioModelAssembler;
import com.algaworks.algafood.api.inputs.GrupoInput;
import com.algaworks.algafood.api.inputs.UsuarioComSenhaInput;
import com.algaworks.algafood.api.inputs.UsuarioInput;
import com.algaworks.algafood.api.inputs.UsuarioNovaSenhaInput;
import com.algaworks.algafood.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exceptions.SenhaInvalidaException;
import com.algaworks.algafood.domain.models.GrupoModel;
import com.algaworks.algafood.domain.models.UsuarioModel;
import com.algaworks.algafood.domain.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioDTOAssembler usuarioDTOAssembler;
    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @Transactional(readOnly = true)
    public List<UsuarioDTO> lista(){
        List<UsuarioModel> usuarioModels = usuarioRepository.findAll();

        List<UsuarioDTO> usuarioDTOS = usuarioModels.stream()
            .map(usuarioModel -> usuarioDTOAssembler.convertToUsuarioDTOBuilder(usuarioModel).build())
            .collect(Collectors.toList());

        return  usuarioDTOS;
    }


    @Transactional(readOnly = true)
    public UsuarioDTO findById(Long id) {
        UsuarioModel usuarioModel = findUsuarioModelById(id);

        UsuarioDTO usuarioDTO = usuarioDTOAssembler.convertToUsuarioDTOBuilder(usuarioModel)
            .dataCadastro(usuarioModel.getDataCadastro())
            .build();

        return usuarioDTO;
    }


    @Transactional(readOnly = true)
    public List<UsuarioDTO> consultaPorNome(String nome) {
        List<UsuarioModel> usuarioModels = usuarioRepository.consultaPorNome(nome);

        List<UsuarioDTO> usuarioDTOS = usuarioModels.stream()
            .map(grupoModel -> usuarioDTOAssembler.convertToUsuarioDTOBuilder(grupoModel).build())
            .collect(Collectors.toList());

        return usuarioDTOS;
    }


    @Transactional
    public UsuarioDTO salva(UsuarioComSenhaInput usuarioInput) {
        UsuarioModel usuarioModel = new UsuarioModel();

        usuarioModelAssembler.convertToUsuarioModel(usuarioInput, usuarioModel);
        usuarioRepository.save(usuarioModel);

        UsuarioDTO usuarioDTO = usuarioDTOAssembler.convertToUsuarioDTOBuilder(usuarioModel)
            .dataCadastro(usuarioModel.getDataCadastro())
            .build();

        return usuarioDTO;
    }


    @Transactional
    public UsuarioDTO altera(Long id, UsuarioInput usuarioInput) {
        UsuarioModel usuarioModel = findUsuarioModelById(id);

        usuarioModelAssembler.convertToUsuarioModel(usuarioInput, usuarioModel);
        usuarioRepository.save(usuarioModel);

        UsuarioDTO usuarioDTO = usuarioDTOAssembler.convertToUsuarioDTOBuilder(usuarioModel)
            .dataCadastro(usuarioModel.getDataCadastro())
            .build();

        return usuarioDTO;
    }


    @Transactional
    public void alteraSenha(Long id, UsuarioNovaSenhaInput usuarioInput) {
        UsuarioModel usuarioModel = findUsuarioModelById(id);

        if(!usuarioInput.getSenhaAtual().equals(usuarioModel.getSenha()))
            throw new SenhaInvalidaException("Senha atual não coincide com a senha do usuário.");

        usuarioModel.setSenha(usuarioInput.getNovaSenha());
        usuarioRepository.save(usuarioModel);
    }


    @Transactional
    public void deleta(Long id) {
        try {
            usuarioRepository.deleteById(id);
            usuarioRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            System.out.println("ERROR: " + e.getMessage());
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de usuario com código: %d", id));
        } catch (DataIntegrityViolationException e) {
            System.out.println("ERROR: " + e.getMessage());
            throw new EntidadeEmUsoException(String.format("Usuario do código: %d não pode ser removido, pois está em uso.", id));
        }
    }


    private UsuarioModel findUsuarioModelById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() ->
            new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de usuário com código: %d", id)));
    }

}
