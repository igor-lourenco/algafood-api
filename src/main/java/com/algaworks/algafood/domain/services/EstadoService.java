package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.DTOs.EstadoDTO;
import com.algaworks.algafood.api.assemblers.EstadoDTOAssembler;
import com.algaworks.algafood.api.assemblers.EstadoModelAssembler;
import com.algaworks.algafood.api.inputs.EstadoInput;
import com.algaworks.algafood.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.models.EstadoModel;
import com.algaworks.algafood.domain.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private EstadoDTOAssembler estadoDTOAssembler;
    @Autowired
    private EstadoModelAssembler estadoModelAssembler;


    public List<EstadoDTO> listar(){
        List<EstadoModel> listaEstados  = estadoRepository.findAll();
        List<EstadoDTO> estadoDTOS = listaEstados.stream().map(estadoModel ->
           estadoDTOAssembler.convertToEstadoDTOBuilder(estadoModel).build()).collect(Collectors.toList());

        return estadoDTOS;
    }

    public EstadoDTO buscaPorId(Long id){
        EstadoModel estadoModel = estadoRepository.findById(id).orElseThrow(() ->
            new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de Estado com id: %d", id)));

        EstadoDTO estadoDTO = estadoDTOAssembler.convertToEstadoDTOBuilder(estadoModel).build();

        return estadoDTO;
    }

    public List<EstadoDTO> consultaPorNome(String nome) {
        List<EstadoModel> listaConsultaPorNome = estadoRepository.consultaPorNome(nome);
        List<EstadoDTO> estadoDTOS = listaConsultaPorNome.stream().map(estadoModel ->
            estadoDTOAssembler.convertToEstadoDTOBuilder(estadoModel).build()).collect(Collectors.toList());

        return  estadoDTOS;
    }


    @Transactional // Se der tudo certo e não lançar nenhuma exception na transação, dá um commit no banco, senão dá rollback para manter a consistência no banco
    public EstadoDTO salvar(EstadoInput estadoInput) {
        EstadoModel estadoModel = new EstadoModel();
        estadoModelAssembler.convertToEstadoModel(estadoInput, estadoModel);

        estadoModel = estadoRepository.save(estadoModel);

        EstadoDTO estadoDTO = estadoDTOAssembler.convertToEstadoDTOBuilder(estadoModel).build();
        return estadoDTO;

    }


    @Transactional // Se der tudo certo e não lançar nenhuma exception na transação, dá um commit no banco, senão dá rollback para manter a consistência no banco
    public EstadoDTO alterar(Long id, EstadoInput estadoInput){
        EstadoModel estadoModel = estadoRepository.findById(id).orElseThrow(() ->
            new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de Estado com id: %d", id)));

        estadoModelAssembler.convertToEstadoModel(estadoInput, estadoModel);
        estadoModel = estadoRepository.save(estadoModel);

        EstadoDTO estadoDTO = estadoDTOAssembler.convertToEstadoDTOBuilder(estadoModel).build();
        return estadoDTO;
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


}
