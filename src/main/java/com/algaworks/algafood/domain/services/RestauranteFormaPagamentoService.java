package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.DTOs.FormaPagamentoDTO;
import com.algaworks.algafood.api.assemblers.DTOs.FormaPagamentoDTOAssembler;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.models.FormaPagamentoModel;
import com.algaworks.algafood.domain.models.RestauranteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class RestauranteFormaPagamentoService {

    @Autowired
    private RestauranteService restauranteService;
    @Autowired
    private FormaPagamentoService formaPagamentoService;
    @Autowired
    private FormaPagamentoDTOAssembler formaPagamentoDTOAssembler;

    public CollectionModel<FormaPagamentoDTO> findAllFormasPagamentos(Long restauranteId) {
        RestauranteModel restauranteModel = restauranteService.findRestauranteModel(restauranteId);
        Set<FormaPagamentoModel> formaPagamentoModels = restauranteModel.getFormaPagamentos();

        return formaPagamentoDTOAssembler.toCollectionModelRestauranteFormaPagamento(formaPagamentoModels, restauranteId);
    }


    public FormaPagamentoDTO findFormaPagamentoByRestauranteId(Long restauranteId, Long formaPagamentoId) {
        RestauranteModel restauranteModel = restauranteService.findRestauranteModel(restauranteId);

        Set<FormaPagamentoModel> formaPagamentoModels = restauranteModel.getFormaPagamentos();
        FormaPagamentoModel formaPagamentoModel = findFormaPagamentoModelByFormaPagamentoId(restauranteId, formaPagamentoId, formaPagamentoModels);

        FormaPagamentoDTO formaPagamentoDTO = formaPagamentoDTOAssembler.convertToFormaPagamentoDTO(formaPagamentoModel);
        return formaPagamentoDTO;
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


    private static FormaPagamentoModel findFormaPagamentoModelByFormaPagamentoId(Long restauranteId, Long formaPagamentoId, Set<FormaPagamentoModel> formaPagamentoModels) {
        return formaPagamentoModels.stream().filter(f -> f.getId().equals(formaPagamentoId))
            .findFirst().orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de forma de pagamento com código: %d para o restaurante de código: %d", formaPagamentoId, restauranteId)));
    }
}
