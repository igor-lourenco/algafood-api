package com.algaworks.algafood.api.assemblers.DTOs;

import com.algaworks.algafood.api.DTOs.CidadeDTO;
import com.algaworks.algafood.api.DTOs.FormaPagamentoDTO;
import com.algaworks.algafood.domain.models.CidadeModel;
import com.algaworks.algafood.domain.models.FormaPagamentoModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormaPagamentoDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    /** Converte classe FormaPagamentoModel para classe FormaPagamentoDTO.FormaPagamentoDTOBuilder */
    public FormaPagamentoDTO.FormaPagamentoDTOBuilder convertToFormaPagamentoDTOBuilder(FormaPagamentoModel formaPagamentoModel) {

        FormaPagamentoDTO formaPagamentoDTO = modelMapper.map(formaPagamentoModel, FormaPagamentoDTO.class);
        return formaPagamentoDTO.toBuilder(); // retorna builder a partir de uma instância existente, para adicionar mais campos caso quem chama esse método tiver necessidade
    }
}
