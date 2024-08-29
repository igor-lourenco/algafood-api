package com.algaworks.algafood.api.assemblers;

import com.algaworks.algafood.api.inputs.CidadeInput;
import com.algaworks.algafood.api.inputs.FormaPagamentoInput;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.models.CidadeModel;
import com.algaworks.algafood.domain.models.EstadoModel;
import com.algaworks.algafood.domain.models.FormaPagamentoModel;
import com.algaworks.algafood.domain.repositories.EstadoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class FormaPagamentoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    /** Converte classe FormaPagamentoInput para classe FormaPagamentoModel */
    public void convertToFormaPagamentoModel(FormaPagamentoInput formaPagamentoInput, FormaPagamentoModel formaPagamentoModel){

        modelMapper.map(formaPagamentoInput, formaPagamentoModel);

    }
}
