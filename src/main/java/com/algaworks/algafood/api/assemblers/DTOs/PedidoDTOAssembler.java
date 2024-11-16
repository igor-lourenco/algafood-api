package com.algaworks.algafood.api.assemblers.DTOs;

import com.algaworks.algafood.api.DTOs.PedidoDTO;
import com.algaworks.algafood.api.DTOs.PedidoResumoDTO;
import com.algaworks.algafood.api.DTOs.jsonFilter.PedidoResumoFilterDTO;
import com.algaworks.algafood.api.controllers.*;
import com.algaworks.algafood.domain.models.PedidoModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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


//  Construtor obrigatório para criar um novo RepresentationModelAssemblerSupport usando a classe de controlador e o tipo de recurso fornecidos como base.
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

        pedidoResumoDTO.add(WebMvcLinkBuilder      //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder.     // cria uma base para o link HATEOAS, apontando para o controlador PedidoController
                methodOn(PedidoController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .BuscaPeloCodigo(pedidoResumoDTO.getCodigo()))   //  método do PedidoController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.SELF)); // Representa o URI para a coleção de recursos do mesmo tipo do recurso atual do pedido

        pedidoResumoDTO.getEnderecoEntrega().getCidade().add(addLinkCidade(pedidoResumoDTO.getEnderecoEntrega().getCidade().getId()));

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

        pedidoDTO.add(addCollection());

        pedidoDTO.getFormaPagamento().add(addLinkFormaPagamento(pedidoDTO.getFormaPagamento().getId()));

        pedidoDTO.getRestaurante().add(addLinkRestaurante(pedidoDTO.getRestaurante().getId()));

        pedidoDTO.getCliente().add(addLinkCliente(pedidoDTO.getCliente().getId()));

        pedidoDTO.getEnderecoEntrega().getCidade().add(addLinkCidade(pedidoDTO.getEnderecoEntrega().getCidade().getId()));

        pedidoDTO.getItens().forEach(itemPedidoDTO -> {
            itemPedidoDTO.add(addLinkProdutoDoItemPedido(pedidoDTO.getRestaurante().getId(), itemPedidoDTO.getProdutoId()));
        });

        return pedidoDTO;
    }


    /** Recupera o contexto da requisição HTTP atual, encapsulando-o em um objeto ServletWebRequest, útil para trabalhar com informações da requisição */
    private static ServletWebRequest getServletWebRequest() {
        HttpServletRequest request = ((ServletRequestAttributes)
            RequestContextHolder.getRequestAttributes()). // Recupera os atributos da requisição atual (no Spring, isso usa o contexto da requisição atual).
            getRequest();                              // Obtém o objeto HttpServletRequest da requisição atual.

        return new ServletWebRequest(request);
    }


    /** Converte Collection<PedidoModel> para CollectionModel<PedidoResumoDTO> da biblioteca do hateoas */
    public CollectionModel<PedidoResumoDTO> convertToCollectionPedidoResumoDTO(Collection<? extends PedidoModel> pedidoModels) {

        List<PedidoResumoDTO> pedidoResumoDTOs = pedidoModels.stream()
            .map(this::convertToPedidoResumoDTO)
            .map(dto -> dto.add(addCollection()))
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


    /** Adiciona link para a coleção desse próprio objeto pedido */
    private static Link addCollection() {
/*      Dessa forma usa methodOn() para referenciar diretamente os métodos com a URI mapeada da classe PedidoController com o ID
        especificado. Ajuda a evitar problemas caso a URL do método mude futuramente */

        return WebMvcLinkBuilder            //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder.              // cria uma base para o link HATEOAS, apontando para o controlador PedidoController
                methodOn(PedidoController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .lista())                           //  método do PedidoController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.COLLECTION); // Representa o URI para a coleção de recursos do mesmo tipo do recurso atual do pedido
    }


    /** Adiciona link da forma de pagamento no objeto 'formaPagamento' do pedido */
    private static Link addLinkFormaPagamento(Long formaPagamentoId) {
        ServletWebRequest servletWebRequest = getServletWebRequest();

/*      Dessa forma usa methodOn() para referenciar diretamente os métodos com a URI mapeada da classe FormaPagamentoController com o ID
        especificado. Ajuda a evitar problemas caso a URL do método mude futuramente */

        return WebMvcLinkBuilder            //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder.              // cria uma base para o link HATEOAS, apontando para o controlador FormaPagamentoController
                methodOn(FormaPagamentoController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaPorId(formaPagamentoId, servletWebRequest))  //  método do FormaPagamentoController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.SELF);  // Representa a URI para essa mesma forma de pagamento
    }


    /** Adiciona link do restaurante no objeto 'restaurante' do pedido */
    private static Link addLinkRestaurante(Long restauranteId) {
/*      Dessa forma usa methodOn() para referenciar diretamente os métodos com a URI mapeada da classe RestauranteController com o ID
        especificado. Ajuda a evitar problemas caso a URL do método mude futuramente */

        return WebMvcLinkBuilder            //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder.  // cria uma base para o link HATEOAS, apontando para o controlador RestauranteController
                methodOn(RestauranteController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaPorId(restauranteId))  //  método do RestauranteController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.SELF); // Representa o URI para esse mesmo restaurante
    }


    /** Adiciona link do usuário no objeto 'cliente' do pedido */
    private static Link addLinkCliente(Long clienteId) {
/*      Dessa forma usa methodOn() para referenciar diretamente os métodos com a URI mapeada da classe UsuarioController com o ID
        especificado. Ajuda a evitar problemas caso a URL do método mude futuramente */

        return WebMvcLinkBuilder            //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder.              // cria uma base para o link HATEOAS, apontando para o controlador UsuarioController
                methodOn(UsuarioController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaPorId(clienteId))     //  método do UsuarioController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.SELF); // Representa o URI para esse mesmo usuário
    }


    /** Adiciona link da cidade no objeto 'enderecoEntrega' do pedido */
    private static Link addLinkCidade(Long cidadeId) {
/*      Dessa forma usa methodOn() para referenciar diretamente os métodos com a URI mapeada da classe CidadeController com o ID
        especificado. Ajuda a evitar problemas caso a URL do método mude futuramente */

        return WebMvcLinkBuilder            //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder.  // cria uma base para o link HATEOAS, apontando para o controlador CidadeController
                methodOn(CidadeController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaPorId(cidadeId))             //  método do CidadeController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.SELF); // Representa a URI para essa mesma cidade
    }


    /** Adiciona link do produto do restaurante no objeto 'itens' de itemPedidoDTO do pedido */
    private static Link addLinkProdutoDoItemPedido(Long restauranteId, Long produtoId) {
/*      Dessa forma usa methodOn() para referenciar diretamente os métodos com a URI mapeada da classe RestauranteProdutoController com o ID
        especificado. Ajuda a evitar problemas caso a URL do método mude futuramente */

        return WebMvcLinkBuilder            //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder.   // cria uma base para o link HATEOAS, apontando para o controlador RestauranteProdutoController
                methodOn(RestauranteProdutoController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaProdutoPeloId(restauranteId, produtoId))  //  método do RestauranteProdutoController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel("produto"); // Representa o URI para esse mesmo produto
    }
}
