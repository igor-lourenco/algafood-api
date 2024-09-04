package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.DTOs.GrupoPermissaoDTO;
import com.algaworks.algafood.api.assemblers.DTOs.GrupoPermissaoDTOAssembler;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.models.GrupoModel;
import com.algaworks.algafood.domain.models.PermissaoModel;
import com.algaworks.algafood.domain.repositories.GrupoRepository;
import com.algaworks.algafood.domain.repositories.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GrupoPermissaoService {

    @Autowired
    private GrupoRepository grupoRepository;
    @Autowired
    private PermissaoRepository permissaoRepository;
    @Autowired
    private GrupoService grupoService;
    @Autowired
    private GrupoPermissaoDTOAssembler grupoPermissaoDTOAssembler;


    @Transactional(readOnly = true)
    public List<GrupoPermissaoDTO> findAllPermissoes(Long id){
        GrupoModel grupoModel = grupoService.findGrupoModelById(id);

        Set<PermissaoModel> permissaoModels = grupoModel.getPermissoes();

        List<GrupoPermissaoDTO> grupoPermissaoDTOS = permissaoModels.stream()
            .map(permissaoModel -> grupoPermissaoDTOAssembler.convertToGrupoPermissaoDTOBuilder(permissaoModel).build())
            .collect(Collectors.toList());

        return  grupoPermissaoDTOS;
    }


    @Transactional(readOnly = true)
    public GrupoPermissaoDTO findPermissaoByPermissaoId(Long grupoId, Long permissaoId) {
        GrupoModel grupoModel = grupoService.findGrupoModelById(grupoId);

        Set<PermissaoModel> permissaoModels = grupoModel.getPermissoes();

        PermissaoModel permissaoModel = findPermissaoModelByPermissaoId(permissaoModels, permissaoId)
            .orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format("Não existe uma Permissao com código: %d", permissaoId)));

        GrupoPermissaoDTO grupoPermissaoDTO = grupoPermissaoDTOAssembler.convertToGrupoPermissaoDTOBuilder(permissaoModel).build();

        return grupoPermissaoDTO;
    }


    @Transactional
    public void associaPermissao(Long grupoId, Long permissaoId) {
        GrupoModel grupoModel = grupoService.findGrupoModelById(grupoId);

        PermissaoModel permissaoModel = permissaoRepository.findById(permissaoId).orElseThrow(() ->
            new EntidadeNaoEncontradaException(String.format("Não existe uma Permissao com código: %d", permissaoId)));;

        grupoModel.associaPermissao(permissaoModel);

        /* Obs: Como o restauranteModel está em estado gerenciado pelo EntityManager, significa que qualquer alteração feita
            na entidade será automaticamente sincronizada com o banco de dados ao final da transação,
            sem a necessidade de chamar explicitamente um método save() para adicionar formaPagamentoModel em restauranteModel.
        */
    }


    @Transactional
    public void desassociaPermissao(Long grupoId, Long permissaoId) {
        GrupoModel grupoModel = grupoService.findGrupoModelById(grupoId);
        Set<PermissaoModel> permissaoModels = grupoModel.getPermissoes();

        PermissaoModel permissaoModel = findPermissaoModelByPermissaoId(permissaoModels, permissaoId)
            .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Não existe uma Permissao com código: %d associado com o Grupo com código: %d",
                permissaoId, grupoId)));

        grupoModel.desassociaPermissao(permissaoModel);

        /* Obs: Como o restauranteModel está em estado gerenciado pelo EntityManager, significa que qualquer alteração feita
            na entidade será automaticamente sincronizada com o banco de dados ao final da transação,
            sem a necessidade de chamar explicitamente um método save() para adicionar formaPagamentoModel em restauranteModel.
        */
    }

    private static Optional<PermissaoModel> findPermissaoModelByPermissaoId (Collection<PermissaoModel> permissaoModels, Long permissaoId) {
        return permissaoModels.stream()
            .filter(permissaoModel -> permissaoModel.getId().equals(permissaoId))
            .findFirst();
    }
}
