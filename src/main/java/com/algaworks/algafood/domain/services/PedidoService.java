package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.DTOs.CidadeDTO;
import com.algaworks.algafood.api.DTOs.PedidoDTO;
import com.algaworks.algafood.api.assemblers.DTOs.PedidoDTOAssembler;
import com.algaworks.algafood.domain.models.CidadeModel;
import com.algaworks.algafood.domain.models.PedidoModel;
import com.algaworks.algafood.domain.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PedidoDTOAssembler pedidoDTOAssembler;

    public List<PedidoDTO> listar(){

        List<PedidoModel> pedidoModels  = pedidoRepository.findAll();

        List<PedidoDTO> cidadeDTOs = pedidoModels.stream().map(pedidoModel ->
                pedidoDTOAssembler.convertToPedidoDTOBuilder(pedidoModel).build())
            .collect(Collectors.toList());

        return cidadeDTOs;
    }
}
