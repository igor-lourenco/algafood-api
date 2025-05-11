package com.algaworks.algafood.api.assemblers.DTOs;

import com.algaworks.algafood.api.DTOs.FormaPagamentoDTO;
import com.algaworks.algafood.api.assemblers.links.FormaPagamentoLinks;
import com.algaworks.algafood.api.controllers.FormaPagamentoController;
import com.algaworks.algafood.domain.models.FormaPagamentoModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class FormaPagamentoDTOAssembler extends RepresentationModelAssemblerSupport<FormaPagamentoModel, FormaPagamentoDTO> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private FormaPagamentoLinks formaPagamentoLinks;


    //  Construtor obrigatório para criar um novo RepresentationModelAssemblerSupport usando a classe de controlador e o tipo de recurso fornecidos como base.
    public FormaPagamentoDTOAssembler() {
        super(FormaPagamentoController.class, FormaPagamentoDTO.class);
    }


    /** Converte classe FormaPagamentoModel para classe FormaPagamentoDTO*/
    public FormaPagamentoDTO convertToFormaPagamentoDTO(FormaPagamentoModel formaPagamentoModel) {
        return toModel(formaPagamentoModel);
    }


    @Override
    public FormaPagamentoDTO toModel(FormaPagamentoModel formaPagamentoModel) {
        FormaPagamentoDTO formaPagamentoDTO = modelMapper.map(formaPagamentoModel, FormaPagamentoDTO.class);

        // Representa o URI indicando que este link aponta para a própria forma de pagamento
        formaPagamentoDTO.add(formaPagamentoLinks.addSelfLink(formaPagamentoDTO, getServletWebRequest()));


        // Representa o URI para a coleção de recursos do mesmo tipo do recurso atual da forma de pagamento
        formaPagamentoDTO.add(formaPagamentoLinks.addCollectionLink("collection", getServletWebRequest()));


        return formaPagamentoDTO;
    }


    @Override
    public CollectionModel<FormaPagamentoDTO> toCollectionModel(Iterable<? extends FormaPagamentoModel> entities) {

//      Dessa forma adiciona a própria URI mapeada na classe FormaPagamentoController para essa coleção
        return super.toCollectionModel(entities)
            .add(formaPagamentoLinks.addSelfCollectionLink());
    }


    public CollectionModel<FormaPagamentoDTO> toCollectionModelRestauranteFormaPagamento(Iterable<? extends FormaPagamentoModel> entities, Long restauranteId) {

//      Dessa forma adiciona a própria URI mapeada na classe RestauranteFormaPagamentoController para essa coleção
        CollectionModel<FormaPagamentoDTO> formaPagamentoDTOS = super.toCollectionModel(entities)
            .add(formaPagamentoLinks.addSelRestauranteFormaPagamentofCollectionLink(restauranteId));

        // Representa o URI para dessassociar o restaurante da forma de pagamento
        formaPagamentoDTOS.forEach(dto ->
            dto.add(formaPagamentoLinks.addDesassociaRestauranteDaFormaPagamentoLink(restauranteId, dto.getId())));


        // Representa o URI para associar o restaurante com a forma de pagamento
        formaPagamentoDTOS.add(formaPagamentoLinks.addAssociaRestauranteDaFormaPagamentoLink(restauranteId));

        return formaPagamentoDTOS;
    }


    /** Recupera o contexto da requisição HTTP atual, encapsulando-o em um objeto ServletWebRequest, útil para trabalhar com informações da requisição */
    private static ServletWebRequest getServletWebRequest() {
        HttpServletRequest request = ((ServletRequestAttributes)
            RequestContextHolder.getRequestAttributes()). // Recupera os atributos da requisição atual (no Spring, isso usa o contexto da requisição atual).
            getRequest();                              // Obtém o objeto HttpServletRequest da requisição atual.

        return new ServletWebRequest(request);
    }

}
