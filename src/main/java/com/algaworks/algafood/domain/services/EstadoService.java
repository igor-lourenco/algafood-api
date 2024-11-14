package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.DTOs.EstadoDTO;
import com.algaworks.algafood.api.assemblers.DTOs.EstadoDTOAssembler;
import com.algaworks.algafood.api.assemblers.EstadoModelAssembler;
import com.algaworks.algafood.api.inputs.EstadoInput;
import com.algaworks.algafood.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.models.EstadoModel;
import com.algaworks.algafood.domain.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private EstadoDTOAssembler estadoDTOAssembler;
    @Autowired
    private EstadoModelAssembler estadoModelAssembler;


    @Transactional(readOnly = true)
    public CollectionModel<EstadoDTO> listar(){
        List<EstadoModel> listaEstados  = estadoRepository.findAll();
        return estadoDTOAssembler.toCollectionModel(listaEstados);
    }


    @Transactional(readOnly = true)
    public EstadoDTO buscaPorId(Long id){
        EstadoModel estadoModel = findEstadoModelById(id);
        return estadoDTOAssembler.convertToEstadoDTOBuilder(estadoModel);
    }


    @Transactional(readOnly = true)
    public CollectionModel<EstadoDTO> consultaPorNome(String nome) {
        List<EstadoModel> listaConsultaPorNome = estadoRepository.consultaPorNome(nome);
        return estadoDTOAssembler.toCollectionModel(listaConsultaPorNome);
    }


    @Transactional // Se der tudo certo e não lançar nenhuma exception na transação, dá um commit no banco, senão dá rollback para manter a consistência no banco
    public EstadoDTO salvar(EstadoInput estadoInput) {
        EstadoModel estadoModel = new EstadoModel();
        estadoModelAssembler.convertToEstadoModel(estadoInput, estadoModel);

        estadoModel = estadoRepository.save(estadoModel);
        return estadoDTOAssembler.convertToEstadoDTOBuilder(estadoModel);
    }


    @Transactional // Se der tudo certo e não lançar nenhuma exception na transação, dá um commit no banco, senão dá rollback para manter a consistência no banco
    public EstadoDTO alterar(Long id, EstadoInput estadoInput){
        EstadoModel estadoModel = findEstadoModelById(id);

        estadoModelAssembler.convertToEstadoModel(estadoInput, estadoModel);
        estadoModel = estadoRepository.save(estadoModel);

        return estadoDTOAssembler.convertToEstadoDTOBuilder(estadoModel);
    }


    @Transactional // Se usar com o metódos delete ou save deve capturar a exception no ControllerHandler porque só lança exceptions quando a commita a transação
    public void deletar(Long id) {
        try {
            estadoRepository.deleteById(id);
            estadoRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            System.out.println("ERROR: " + e.getMessage());
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de etado com código: %d", id));
        } catch (DataIntegrityViolationException e) {
            System.out.println("ERROR: " + e.getMessage());
            throw new EntidadeEmUsoException(String.format("Estado de código: %d não pode ser removida, pois está em uso.", id));
        }
    }


    private EstadoModel findEstadoModelById(Long id) {
        return estadoRepository.findById(id)
            .orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de Estado com id: %d", id)));
    }
}
