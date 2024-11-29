package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.DTOs.UsuarioGrupoDTO;
import com.algaworks.algafood.api.assemblers.DTOs.UsuarioGrupoDTOAssembler;
import com.algaworks.algafood.domain.models.GrupoModel;
import com.algaworks.algafood.domain.models.UsuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class UsuarioGrupoService {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioGrupoDTOAssembler usuarioGrupoDTOAssembler;
    @Autowired GrupoService grupoService;

    @Transactional(readOnly = true)
    public CollectionModel<UsuarioGrupoDTO> findGruposByUsuarioId(Long usuarioId){
        UsuarioModel usuarioModel = usuarioService.findUsuarioModelById(usuarioId);
        Set<GrupoModel> grupoModels = usuarioModel.getGrupos();

        return  usuarioGrupoDTOAssembler.toCollectionUsuarioGrupoModel(usuarioId,grupoModels);
    }

    @Transactional
    public void associaUsuarioWithGrupo(Long usuarioId, Long grupoId) {
        UsuarioModel usuarioModel = usuarioService.findUsuarioModelById(usuarioId);
        GrupoModel grupoModel = grupoService.findGrupoModelById(grupoId);

        usuarioModel.associaGrupo(grupoModel);

        /* Obs: Como o usuarioModel está em estado gerenciado pelo EntityManager, significa que qualquer alteração feita
            na entidade será automaticamente sincronizada com o banco de dados ao final da transação,
            sem a necessidade de chamar explicitamente um método save() para adicionar grupoModel em usuarioModel.
        */

    }

    @Transactional
    public void desassociaUsuarioWithGrupo(Long usuarioId, Long grupoId) {
        UsuarioModel usuarioModel = usuarioService.findUsuarioModelById(usuarioId);
        GrupoModel grupoModel = grupoService.findGrupoModelById(grupoId);

        usuarioModel.desassociaGrupo(grupoModel);

        /* Obs: Como o usuarioModel está em estado gerenciado pelo EntityManager, significa que qualquer alteração feita
            na entidade será automaticamente sincronizada com o banco de dados ao final da transação,
            sem a necessidade de chamar explicitamente um método save() para adicionar grupoModel em usuarioModel.
        */

    }

}
