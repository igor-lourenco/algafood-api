package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.DTOs.FormaPagamentoDTO;
import com.algaworks.algafood.api.assemblers.FormaPagamentoDTOAssembler;
import com.algaworks.algafood.domain.models.FormaPagamentoModel;
import com.algaworks.algafood.domain.models.RestauranteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
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
        Set<FormaPagamentoModel> formaPagamentoModels = restauranteModel.getFormaPagamentos();

        List<FormaPagamentoDTO> formaPagamentoDTOS = formaPagamentoModels.stream().map(formaPagamentoModel ->
                formaPagamentoDTOAssembler.convertToFormaPagamentoDTOBuilder(formaPagamentoModel).build())
            .collect(Collectors.toList());

        return formaPagamentoDTOS;
    }


    @Transactional
    public void associaFormaPagamento(Long restauranteId, Long formaPagamentoId){
        RestauranteModel restauranteModel = restauranteService.findRestauranteModel(restauranteId);
        FormaPagamentoModel formaPagamentoModel = formaPagamentoService.findFormaPagamentoModelById(formaPagamentoId);

        restauranteModel.associaFormaPagamento(formaPagamentoModel);

        /* Obs: Como o restauranteModel está em estado gerenciado pelo EntityManager, significa que qualquer alteração feita
            na entidade será automaticamente sincronizada com o banco de dados ao final da transação,
            sem a necessidade de chamar explicitamente um método save() para adicionar formaPagamentoModel em restauranteModel.
        */
    }


    @Transactional
    public void desassociaFormaPagamento(Long restauranteId, Long formaPagamentoId){
        RestauranteModel restauranteModel = restauranteService.findRestauranteModel(restauranteId);
        FormaPagamentoModel formaPagamentoModel = formaPagamentoService.findFormaPagamentoModelById(formaPagamentoId);

        restauranteModel.desassociaFormaPagamento(formaPagamentoModel);

        /* Obs: Como o restauranteModel está em estado gerenciado pelo EntityManager, significa que qualquer alteração feita
            na entidade será automaticamente sincronizada com o banco de dados ao final da transação,
            sem a necessidade de chamar explicitamente um método save() para remover formaPagamentoModel de restauranteModel.
        */
    }

}
