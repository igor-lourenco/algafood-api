package com.algaworks.algafood.api.assemblers.DTOs;

import com.algaworks.algafood.api.DTOs.PedidoDTO;
import com.algaworks.algafood.api.DTOs.PedidoResumoDTO;
import com.algaworks.algafood.api.DTOs.jsonFilter.PedidoResumoFilterDTO;
import com.algaworks.algafood.api.assemblers.links.PedidoLinks;
import com.algaworks.algafood.api.controllers.PedidoController;
import com.algaworks.algafood.domain.models.PedidoModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoDTOAssembler extends RepresentationModelAssemblerSupport<PedidoModel, PedidoDTO> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PagedResourcesAssembler<PedidoModel> pagedAssembler;
    @Autowired
    private PedidoLinks pedidoLinks;

    /* Construtor obrigatório para criar um novo RepresentationModelAssemblerSupport usando a classe de controlador e o tipo de recurso fornecidos como base. */
    public PedidoDTOAssembler() {
        super(PedidoController.class, PedidoDTO.class);
    }


    /** Converte classe PedidoModel para classe PedidoDTO */
    public PedidoDTO convertToPedidoDTO(PedidoModel pedidoModel) {
        return toModel(pedidoModel);
    }


    /**  Converte PedidoModel para PedidoResumoDTO e já adiciona link próprio para cada ID fornecido do objeto pedido e da cidade. */
    private PedidoResumoDTO convertToPedidoResumoDTO(PedidoModel pedidoModel){
        PedidoResumoDTO pedidoResumoDTO = modelMapper.map(pedidoModel, PedidoResumoDTO.class);

        // Representa o URI para a coleção de recursos do mesmo tipo do recurso atual do pedido
        pedidoResumoDTO.add(pedidoLinks.addSelfLink(pedidoResumoDTO.getCodigo()));

        // Representa o URI para o recurso cidade desse pedido
        pedidoResumoDTO.getEnderecoEntrega().getCidade().add(pedidoLinks.addSelfCidadeLink(pedidoResumoDTO.getEnderecoEntrega().getCidade().getId()));

        return pedidoResumoDTO;
    }


    /** Converte classe PedidoModel para classe PedidoResumoFilterDTO.PedidoResumoFilterDTOBuilder */
    public PedidoResumoFilterDTO.PedidoResumoFilterDTOBuilder convertToPedidoResumoFilterDTOBuilder(PedidoModel pedidoModel) {

        PedidoResumoFilterDTO pedidoDTO = modelMapper.map(pedidoModel, PedidoResumoFilterDTO.class);
        return pedidoDTO.toBuilder(); // retorna builder a partir de uma instância existente, para adicionar mais campos caso quem chama esse método tiver necessidade
    }


    @Override
    public PedidoDTO toModel(PedidoModel pedidoModel) {
        // Cria um novo recurso já com um link próprio(self) para o ID fornecido.
        PedidoDTO pedidoDTO = createModelWithId(pedidoModel.getCodigo(), pedidoModel);
        modelMapper.map(pedidoModel, pedidoDTO);


        // Representa o URI para a coleção desse pedido
        pedidoDTO.add(pedidoLinks.addCollectionLink());

        // Representa o URI para o recurso de forma de pagamento desse pedido
        pedidoDTO.getFormaPagamento().add(pedidoLinks.addSelfFormaPagamentoLink(pedidoDTO.getFormaPagamento().getId(), getServletWebRequest()));

        // Representa o URI para o recurso  restaurante desse pedido
        pedidoDTO.getRestaurante().add(pedidoLinks.addSelfRestauranteLink(pedidoDTO.getRestaurante().getId()));

        // Representa o URI para o recurso cliente desse pedido
        pedidoDTO.getCliente().add(pedidoLinks.addSelfClienteLink(pedidoDTO.getCliente().getId()));

        // Representa o URI para o recurso cidade desse pedido
        pedidoDTO.getEnderecoEntrega().getCidade().add(pedidoLinks.addSelfCidadeLink(pedidoDTO.getEnderecoEntrega().getCidade().getId()));

        // Representa o URI para o recurso dos produtos dos itens de pedido desse pedido
        pedidoDTO.getItens().forEach(itemPedidoDTO -> {
            itemPedidoDTO.add(pedidoLinks.addSelfProdutoDoItemPedidoLink(pedidoDTO.getRestaurante().getId(), itemPedidoDTO.getProdutoId()));
        });

        // Representa o URI para o recurso de alteração do status desse pedido para 'CONFIRMADO'
        pedidoDTO.add(pedidoLinks.addSelfConfirmaPedidoLink(pedidoDTO.getCodigo()));

        // Representa o URI para o recurso de alteração do status desse pedido para 'ENTREGUE'
        pedidoDTO.add(pedidoLinks.addSelfEntregaPedidoLink(pedidoDTO.getCodigo()));

        // Representa o URI para o recurso de alteração do status desse pedido para 'CANCELADO'
        pedidoDTO.add(pedidoLinks.addSelfCancelaPedidoLink(pedidoDTO.getCodigo()));

        return pedidoDTO;
    }


    /** Converte Collection<PedidoModel> para CollectionModel<PedidoResumoDTO> da biblioteca do hateoas */
    public CollectionModel<PedidoResumoDTO> convertToCollectionPedidoResumoDTO(Collection<? extends PedidoModel> pedidoModels) {

        List<PedidoResumoDTO> pedidoResumoDTOs = pedidoModels.stream()
            .map(this::convertToPedidoResumoDTO) // converte PedidoModel para PedidoResumoDTO
            .map(dto -> dto.add(pedidoLinks.addCollectionLink())) // adiciona a URI para a coleção desse pedido
            .collect(Collectors.toList());

         return CollectionModel.of(pedidoResumoDTOs);
    }

    /** Converte Page<PedidoModel> do Spring Data para o PagedModel<PedidoResumoDTO> do hateoas é já adiciona link próprio para cada ID fornecido do objeto PedidoResumoDTO */
    public PagedModel<PedidoResumoDTO> convertToPedidoResumoDTOPage(Page<PedidoModel> pedidoModelPage) {

//      Converte Page<?> do Spring Data para o PagedModel<?> do hateoas é já adiciona link próprio para cada ID fornecido do objeto pedido e também os links da paginação.
        PagedModel<PedidoDTO> cozinhaDTOPagedModel = pagedAssembler.toModel(pedidoModelPage, this);

//      Converte a List<PedidoModel> para CollectionModel<PedidoResumoDTO> e já adiciona link próprio para cada ID fornecido do objeto PedidoResumoDTO e da cidade.
        CollectionModel<PedidoResumoDTO> collectionModel = convertToCollectionPedidoResumoDTO(pedidoModelPage.getContent());

        return PagedModel.of(collectionModel.getContent(), cozinhaDTOPagedModel.getMetadata(), cozinhaDTOPagedModel.getLinks());
    }


    /** Recupera o contexto da requisição HTTP atual, encapsulando-o em um objeto ServletWebRequest, útil para trabalhar com informações da requisição */
    private static ServletWebRequest getServletWebRequest() {
        HttpServletRequest request = ((ServletRequestAttributes)
            RequestContextHolder.getRequestAttributes()). // Recupera os atributos da requisição atual (no Spring, isso usa o contexto da requisição atual).
            getRequest();                              // Obtém o objeto HttpServletRequest da requisição atual.

        return new ServletWebRequest(request);
    }

}
