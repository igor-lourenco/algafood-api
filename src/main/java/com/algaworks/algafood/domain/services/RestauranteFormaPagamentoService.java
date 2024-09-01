package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.DTOs.FormaPagamentoDTO;
import com.algaworks.algafood.api.assemblers.FormaPagamentoDTOAssembler;
import com.algaworks.algafood.domain.models.FormaPagamentoModel;
import com.algaworks.algafood.domain.models.RestauranteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestauranteFormaPagamentoService {

    @Autowired
    private RestauranteService restauranteService;
    @Autowired
    private FormaPagamentoService formaPagamentoService;
    @Autowired
    private FormaPagamentoDTOAssembler formaPagamentoDTOAssembler;

    public List<FormaPagamentoDTO> findAllFormasPagamentos(Long restauranteId) {

        RestauranteModel restauranteModel = restauranteService.findRestauranteModel(restauranteId);
        List<FormaPagamentoModel> formaPagamentoModels = restauranteModel.getFormaPagamentos();

        List<FormaPagamentoDTO> formaPagamentoDTOS = formaPagamentoModels.stream().map(formaPagamentoModel ->
                formaPagamentoDTOAssembler.convertToFormaPagamentoDTOBuilder(formaPagamentoModel).build())
            .collect(Collectors.toList());

        return formaPagamentoDTOS;
    }

    @Transactional
    public void desassociaFormaPagamento(Long restauranteId, Long formaPagamentoId){
        RestauranteModel restauranteModel = restauranteService.findRestauranteModel(restauranteId);
        FormaPagamentoModel formaPagamentoModel = formaPagamentoService.findFormaPagamentoModelById(formaPagamentoId);

        restauranteModel.desassociaFormaPagamento(formaPagamentoModel);
    }
}
