package com.algaworks.algafood.api.assemblers.DTOs;

import com.algaworks.algafood.api.DTOs.RestauranteDTO;
import com.algaworks.algafood.api.DTOs.jsonView.RestauranteViewDTO;
import com.algaworks.algafood.api.assemblers.links.RestauranteLinks;
import com.algaworks.algafood.api.controllers.RestauranteController;
import com.algaworks.algafood.domain.models.RestauranteModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RestauranteDTOAssembler extends RepresentationModelAssemblerSupport<RestauranteModel, RestauranteDTO> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RestauranteLinks restauranteLinks;


    /* Construtor obrigatório para criar um novo RepresentationModelAssemblerSupport usando a classe de controlador e o tipo de recurso fornecidos como base. */
    public RestauranteDTOAssembler() {
        super(RestauranteController.class, RestauranteDTO.class);
    }


    /** Converte classe RestauranteModel para classe  RestauranteDTO */
    public RestauranteDTO convertToRestauranteDTO(RestauranteModel restauranteModel) {
        RestauranteDTO restauranteDTO  = toModel(restauranteModel);

//        TODO: fazer a logica para verificar se existe forma de pagamento nesse restaurante,senão tiver, não é para mostrar a API de forma-pagamento
        // Representa o URI para o recurso de forma de pagamento desse restaurante
        restauranteDTO.add(restauranteLinks.addSelfFormasDePagamentoLink(restauranteDTO));

//        TODO: fazer a logica para verificar se existe usuario responsavel para esse restaurante,senão tiver, não é para mostrar a API de usuario responsavel
        // Representa o URI para o recurso de usuarios responsaveis de restaurante
        restauranteDTO.add(restauranteLinks.addSelfUsuariosResponsaveisLink(restauranteDTO));

        // Representa o URI para o recurso de produtos desse restaurante
        restauranteDTO.add(restauranteLinks.addSelfRestauranteProdutoLink(restauranteDTO));


        if(restauranteModel.permiteAtivacao()){
        // Representa o URI para o recurso de alteração do campo: ativo = true
         restauranteDTO.add(restauranteLinks.addSelfAtivaRestauranteLink(restauranteDTO));

        }

        if(restauranteModel.permiteInativacao()) {
        // Representa o URI para o recurso de alteração do campo: ativo = false
            restauranteDTO.add(restauranteLinks.addSelfInativaRestauranteLink(restauranteDTO));
        }

        if(restauranteModel.aberturaPermitida()) {
        // Representa o URI para o recurso de alteração do campo: aberto = true
            restauranteDTO.add(restauranteLinks.addSelfAbreRestauranteLink(restauranteDTO));
        }

        if(restauranteModel.fechamentoPermitido()) {
        // Representa o URI para o recurso de alteração do campo: aberto = false
            restauranteDTO.add(restauranteLinks.addSelfFechaRestauranteLink(restauranteDTO));
        }

        return restauranteDTO;
    }


    /** Converte classe RestauranteModel para classe  RestauranteViewDTO.RestauranteViewDTOBuilder */
    public RestauranteViewDTO.RestauranteViewDTOBuilder convertToRestauranteViewDTOBuilder(RestauranteModel restauranteModel) {

        RestauranteViewDTO restauranteDTO = modelMapper.map(restauranteModel, RestauranteViewDTO.class);
        return restauranteDTO.toBuilder(); // retorna builder a partir de uma instância existente, para adicionar mais campos caso quem chama esse método tiver necessidade
    }


    @Override
    public RestauranteDTO toModel(RestauranteModel restauranteModel) {
        RestauranteDTO restauranteDTO = modelMapper.map(restauranteModel, RestauranteDTO.class);

        // Representa o URI para a coleção de recursos do mesmo tipo do recurso atual do pedido
        restauranteDTO.add(restauranteLinks.addSelfLink(restauranteDTO));

        // Representa o URI para a coleção desse pedido
        restauranteDTO.add(restauranteLinks.addCollectionLink());

        // Representa o URI para o recurso cozinha desse restaurante
        restauranteDTO.getCozinha().add(restauranteLinks.addSelfCozinhaLink(restauranteDTO));

        // Representa o URI para o recurso cidade desse pedido
        if(null != restauranteDTO.getEndereco() && null != restauranteDTO.getEndereco().getCidade()) {
            restauranteDTO.getEndereco().getCidade().add(restauranteLinks.addSelfCidadeLink(restauranteDTO));
        }

        return restauranteDTO;
    }

    @Override
    public CollectionModel<RestauranteDTO> toCollectionModel(Iterable<? extends RestauranteModel> entities) {

//      Dessa forma adiciona a própria URI mapeada na classe RestauranteController para essa coleção
        return super.toCollectionModel(entities)
            .add(restauranteLinks.addSelfCollectionLink())
            .add(restauranteLinks.addLimitaOsCamposRestauranteLink())
            .add(restauranteLinks.addProjecaoRestauranteLink());
    }
}
