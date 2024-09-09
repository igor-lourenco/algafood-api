package com.algaworks.algafood.api.assemblers.DTOs;

import com.algaworks.algafood.api.DTOs.CidadeDTO;
import com.algaworks.algafood.api.DTOs.PedidoDTO;
import com.algaworks.algafood.domain.models.CidadeModel;
import com.algaworks.algafood.domain.models.PedidoModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class PedidoDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    /** Converte classe PedidoModel para classe PedidoDTO.PedidoDTOBuilder */
    public PedidoDTO.PedidoDTOBuilder convertToPedidoDTOBuilder(PedidoModel pedidoModel) {

        PedidoDTO pedidoDTO = modelMapper.map(pedidoModel, PedidoDTO.class);
        return pedidoDTO.toBuilder(); // retorna builder a partir de uma instância existente, para adicionar mais campos caso quem chama esse método tiver necessidade
    }
}
