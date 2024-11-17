package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.DTOs.UsuarioDTO;
import com.algaworks.algafood.api.assemblers.DTOs.UsuarioDTOAssembler;
import com.algaworks.algafood.api.assemblers.models.UsuarioModelAssembler;
import com.algaworks.algafood.api.inputs.UsuarioComSenhaInput;
import com.algaworks.algafood.api.inputs.UsuarioInput;
import com.algaworks.algafood.api.inputs.UsuarioNovaSenhaInput;
import com.algaworks.algafood.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exceptions.SenhaInvalidaException;
import com.algaworks.algafood.domain.exceptions.UsuarioExistenteException;
import com.algaworks.algafood.domain.models.UsuarioModel;
import com.algaworks.algafood.domain.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioDTOAssembler usuarioDTOAssembler;
    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @Transactional(readOnly = true)
    public CollectionModel<UsuarioDTO> lista(){
        List<UsuarioModel> usuarioModels = usuarioRepository.findAll();
        return usuarioDTOAssembler.toCollectionModel(usuarioModels);
    }


    @Transactional(readOnly = true)
    public UsuarioDTO findById(Long id) {
        UsuarioModel usuarioModel = findUsuarioModelById(id);
        return usuarioDTOAssembler.convertToUsuarioDTO(usuarioModel);
    }


    @Transactional(readOnly = true)
    public CollectionModel<UsuarioDTO> consultaPorNome(String nome) {
        List<UsuarioModel> usuarioModels = usuarioRepository.consultaPorNome(nome);
        return usuarioDTOAssembler.toCollectionModel(usuarioModels);
    }


    @Transactional
    public UsuarioDTO salva(UsuarioComSenhaInput usuarioInput) {
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModelAssembler.convertToUsuarioModel(usuarioInput, usuarioModel);

        Optional<UsuarioModel> usuarioExistente = usuarioRepository.findByEmail(usuarioInput.getEmail());
        verificaEmailExistente(usuarioExistente, usuarioModel);

        usuarioRepository.save(usuarioModel);

        return usuarioDTOAssembler.convertToUsuarioDTO(usuarioModel);
    }


    @Transactional
    public UsuarioDTO altera(Long id, UsuarioInput usuarioInput) {
        UsuarioModel usuarioModel = findUsuarioModelById(id);
        usuarioRepository.detach(usuarioModel); // remove a entidade do contexto gerenciado pelo JPA

        usuarioModelAssembler.convertToUsuarioModel(usuarioInput, usuarioModel);

        Optional<UsuarioModel> usuarioExistente = usuarioRepository.findByEmail(usuarioInput.getEmail());
        verificaEmailExistente(usuarioExistente, usuarioModel);

        usuarioRepository.save(usuarioModel);

        return usuarioDTOAssembler.convertToUsuarioDTO(usuarioModel);
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


    protected UsuarioModel findUsuarioModelById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() ->
            new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de usuário com código: %d", id)));
    }


    private static void verificaEmailExistente(Optional<UsuarioModel> usuarioExistente, UsuarioModel usuarioModel) {
        if (usuarioExistente.isPresent() && !usuarioExistente.get().getId().equals(usuarioModel.getId())) {
            throw new UsuarioExistenteException("Já existe um usuário cadastrado com o e-mail: " + usuarioExistente.get().getEmail());
        }
    }

}
