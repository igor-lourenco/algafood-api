package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.DTOs.CidadeDTO;
import com.algaworks.algafood.api.DTOs.PedidoDTO;
import com.algaworks.algafood.api.DTOs.PedidoResumoDTO;
import com.algaworks.algafood.api.assemblers.DTOs.PedidoDTOAssembler;
import com.algaworks.algafood.api.assemblers.PedidoModelAssembler;
import com.algaworks.algafood.api.inputs.PedidoInput;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.models.CidadeModel;
import com.algaworks.algafood.domain.models.PedidoModel;
import com.algaworks.algafood.domain.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PedidoDTOAssembler pedidoDTOAssembler;
    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;

    @Transactional(readOnly = true)
    public List<PedidoResumoDTO> listar(){
        List<PedidoModel> pedidoModels  = pedidoRepository.findAll();

        List<PedidoResumoDTO> pedidoDTOS = pedidoModels.stream().map(pedidoModel ->
                pedidoDTOAssembler.convertToPedidoResumoDTOBuilder(pedidoModel).build())
            .collect(Collectors.toList());

        return pedidoDTOS;
    }

    @Transactional(readOnly = true)
    public PedidoDTO findById(Long pedidoId){
        PedidoModel pedidoModel  = findPedidoModelById(pedidoId);

        PedidoDTO pedidoDTO = pedidoDTOAssembler.convertToPedidoDTOBuilder(pedidoModel).build();

        return pedidoDTO;
    }

    public PedidoDTO savePedido(PedidoInput pedidoInput) {
        PedidoModel pedidoModel = new PedidoModel();
        pedidoModelAssembler.convertToPedidoModel(pedidoInput, pedidoModel);

        PedidoDTO pedidoDTO = pedidoDTOAssembler.convertToPedidoDTOBuilder(pedidoModel).build();

        return pedidoDTO;

    }


    protected PedidoModel findPedidoModelById(Long pedidoId) {
        return pedidoRepository.findById(pedidoId).orElseThrow(() ->
            new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de pedido com id: %d", pedidoId)));
    }


}
