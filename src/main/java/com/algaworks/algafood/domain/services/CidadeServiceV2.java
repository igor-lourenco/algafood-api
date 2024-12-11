package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.DTOs.CidadeDTOV2;
import com.algaworks.algafood.api.assemblers.DTOs.CidadeDTOAssemblerV2;
import com.algaworks.algafood.api.assemblers.models.CidadeModelAssemblerV2;
import com.algaworks.algafood.api.inputs.CidadeInputV2;
import com.algaworks.algafood.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.models.CidadeModel;
import com.algaworks.algafood.domain.repositories.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CidadeServiceV2 {

    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private CidadeDTOAssemblerV2 cidadeDTOAssemblerV2;
    @Autowired
    private CidadeModelAssemblerV2 cidadeModelAssemblerV2;


    @Transactional(readOnly = true)
    public CollectionModel<CidadeDTOV2> listar(){
        List<CidadeModel> listaCidades  = cidadeRepository.findAll();
        return  cidadeDTOAssemblerV2.toCollectionModel(listaCidades);
    }


    @Transactional(readOnly = true)
    public CidadeDTOV2 buscaPorId(Long id){
        CidadeModel cidadeModel = findCidadeModelByCidadeId(id);
        return cidadeDTOAssemblerV2.convertToCidadeDTOV2(cidadeModel);
    }


    @Transactional(readOnly = true)
    public CollectionModel<CidadeDTOV2> consultaPorNome(String nome) {
        List<CidadeModel> listaConsultaPorNome = cidadeRepository.consultaPorNome(nome);
        return  cidadeDTOAssemblerV2.toCollectionModel(listaConsultaPorNome);
    }


    @Transactional // Se der tudo certo e não lançar nenhuma exception na transação, dá um commit no banco, senão dá rollback para manter a consistência no banco
    public CidadeDTOV2 salvar(CidadeInputV2 cidadeInput){
        CidadeModel cidadeModel = new CidadeModel();
        cidadeModelAssemblerV2.convertToCidadeModel(cidadeInput, cidadeModel);

        cidadeModel = cidadeRepository.save(cidadeModel);
        return cidadeDTOAssemblerV2.convertToCidadeDTOV2(cidadeModel);
    }


    @Transactional // Se der tudo certo e não lançar nenhuma exception na transação, dá um commit no banco, senão dá rollback para manter a consistência no banco
    public CidadeDTOV2 alterar(Long id, CidadeInputV2 cidadeInput){
        CidadeModel cidadeModel = findCidadeModelByCidadeId(id);

        cidadeModelAssemblerV2.convertToCidadeModel(cidadeInput, cidadeModel);
        cidadeModel = cidadeRepository.save(cidadeModel);

        return cidadeDTOAssemblerV2.convertToCidadeDTOV2(cidadeModel);
    }


//    @Transactional // Se der tudo certo e não lançar nenhuma exception na transação, dá um commit no banco, senão dá rollback para manter a consistência no banco
    public void deletar(Long id) {
        try {
            cidadeRepository.deleteById(id);
            cidadeRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            System.out.println("ERROR: " + e.getMessage());
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de cidade com código: %d", id));
        } catch (DataIntegrityViolationException e) {
            System.out.println("ERROR: " + e.getMessage());
            throw new EntidadeEmUsoException(String.format("Cidade de código: %d não pode ser removida, pois está em uso.", id));
        }
    }


    public CidadeModel findCidadeModelByCidadeId(Long id) {
        return cidadeRepository.findById(id).orElseThrow(() ->
            new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de Cidade com id: %d", id)));
    }

}
