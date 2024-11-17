package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.DTOs.CidadeDTO;
import com.algaworks.algafood.api.assemblers.models.CidadeModelAssembler;
import com.algaworks.algafood.api.assemblers.DTOs.CidadeDTOAssembler;
import com.algaworks.algafood.api.inputs.CidadeInput;
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
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private CidadeDTOAssembler cidadeDTOAssembler;
    @Autowired
    private CidadeModelAssembler cidadeModelAssembler;

    public CollectionModel<CidadeDTO> listar(){
        List<CidadeModel> listaCidades  = cidadeRepository.findAll();
        return  cidadeDTOAssembler.toCollectionModel(listaCidades);
    }


    public CidadeDTO buscaPorId(Long id){
        CidadeModel cidadeModel = findCidadeModelByCidadeId(id);
        return cidadeDTOAssembler.convertToCidadeDTO(cidadeModel);
    }


    public CollectionModel<CidadeDTO> consultaPorNome(String nome) {
        List<CidadeModel> listaConsultaPorNome = cidadeRepository.consultaPorNome(nome);
        return  cidadeDTOAssembler.toCollectionModel(listaConsultaPorNome);
    }


    @Transactional // Se der tudo certo e não lançar nenhuma exception na transação, dá um commit no banco, senão dá rollback para manter a consistência no banco
    public CidadeDTO salvar(CidadeInput cidadeInput){
        CidadeModel cidadeModel = new CidadeModel();
        cidadeModelAssembler.convertToCidadeModel(cidadeInput, cidadeModel);

        cidadeModel = cidadeRepository.save(cidadeModel);

        return cidadeDTOAssembler.convertToCidadeDTO(cidadeModel);
    }


    @Transactional // Se der tudo certo e não lançar nenhuma exception na transação, dá um commit no banco, senão dá rollback para manter a consistência no banco
    public CidadeDTO alterar(Long id, CidadeInput cidadeInput){
        CidadeModel cidadeModel = findCidadeModelByCidadeId(id);

        cidadeModelAssembler.convertToCidadeModel(cidadeInput, cidadeModel);
        cidadeModel = cidadeRepository.save(cidadeModel);

        return cidadeDTOAssembler.convertToCidadeDTO(cidadeModel);
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
