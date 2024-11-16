package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.DTOs.PedidoDTO;
import com.algaworks.algafood.api.DTOs.PedidoResumoDTO;
import com.algaworks.algafood.api.DTOs.jsonFilter.PedidoResumoFilterDTO;
import com.algaworks.algafood.api.assemblers.DTOs.PedidoDTOAssembler;
import com.algaworks.algafood.api.assemblers.PedidoModelAssembler;
import com.algaworks.algafood.api.inputs.PedidoInput;
import com.algaworks.algafood.core.data.PageableTranslator;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exceptions.FiltroException;
import com.algaworks.algafood.domain.models.PedidoModel;
import com.algaworks.algafood.domain.repositories.PedidoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private RestauranteFormaPagamentoService restauranteFormaPagamentoService;
    @Autowired
    private PedidoDTOAssembler pedidoDTOAssembler;
    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;


    @Transactional(readOnly = true)
    public CollectionModel<PedidoResumoDTO> listaPaginadaComCamposDeFiltragem() {
        List<PedidoModel> pedidoModels = pedidoRepository.findAll();
        return pedidoDTOAssembler.convertToCollectionPedidoResumoDTO(pedidoModels);

    }


    @Transactional(readOnly = true)
    public CollectionModel<PedidoResumoDTO> listarComSpecification(Specification<PedidoModel> pedidoModelSpecification) {
        List<PedidoModel> pedidoModels = pedidoRepository.findAll(pedidoModelSpecification);
        return pedidoDTOAssembler.convertToCollectionPedidoResumoDTO(pedidoModels);
    }


    @Transactional(readOnly = true)
    public PagedModel<PedidoResumoDTO> listaPaginadaComCamposDeFiltragem(Specification<PedidoModel> pedidoModelSpecification, Pageable pageable) {

        Pageable pageableTraduzido = traduzirPageable(pageable);

        Page<PedidoModel> pedidoModelPage = pedidoRepository.findAll(pedidoModelSpecification, pageableTraduzido);

//      Cria um novo Page<PedidoModel> mas com os params da paginação e ordenação dos campos da classe 'PedidoResumoDTO',
//      senão vai retornar a ordenação com os campo da classe PedidoModel em vez dos campos da classe 'PedidoResumoDTO'
        Page<PedidoModel> pedidoModelPageComCamposVindoDaRequest =
            new PageImpl<>(pedidoModelPage.getContent(), pageable, pedidoModelPage.getTotalElements());

        return pedidoDTOAssembler.convertToPedidoResumoDTOPage(pedidoModelPageComCamposVindoDaRequest);
    }


    @Transactional(readOnly = true)
    public List<PedidoResumoFilterDTO> listaPedidoComJsonFilter() {
        List<PedidoModel> pedidoModels = pedidoRepository.findAll();

        List<PedidoResumoFilterDTO> pedidoDTOS = pedidoModels.stream().map(pedidoModel ->
                pedidoDTOAssembler.convertToPedidoResumoFilterDTOBuilder(pedidoModel).build())
            .collect(Collectors.toList());

        return pedidoDTOS;
    }


    @Transactional(readOnly = true) // não está sendo usado
    public PedidoDTO findById(Long pedidoId) {
        PedidoModel pedidoModel = findPedidoModelById(pedidoId);
        return pedidoDTOAssembler.convertToPedidoDTO(pedidoModel);
    }


    @Transactional(readOnly = true)
    public PedidoDTO findByCodigo(String codigoPedido) {
        PedidoModel pedidoModel = findPedidoModelByCodigo(codigoPedido);
        return pedidoDTOAssembler.convertToPedidoDTO(pedidoModel);
    }


    @Transactional
    public PedidoDTO savePedido(PedidoInput pedidoInput) {
        PedidoModel pedidoModel = new PedidoModel();

        restauranteFormaPagamentoService.findFormaPagamentoByRestauranteId(pedidoInput.getRestauranteId(), pedidoInput.getFormaPagamentoId());
        pedidoModelAssembler.convertToPedidoModel(pedidoInput, pedidoModel);

        pedidoModel.defineFrete();
        pedidoModel.calculaValorTotal();

        pedidoModel.setDataCriacao(LocalDateTime.now());
        pedidoModel.setCliente(usuarioService.findUsuarioModelById(1L)); // TODO: Depois pegar o usuário pela autenticação

        pedidoModel = pedidoRepository.save(pedidoModel);

        return pedidoDTOAssembler.convertToPedidoDTO(pedidoModel);
    }


/** Esse método retorna um objeto MappingJacksonValue com o filtro aplicado que foi especificado com
    annotation @JsonFilter na classe annotation PedidoResumoFilterDTO. O objeto MappingJacksonValue será serializado em
    JSON, contendo apenas os campos que o cliente especificou (caso tenham sido fornecidos) ou todos os
    campos, caso o cliente não tenha solicitado nenhum filtro.  */
    public static MappingJacksonValue listaFiltradaComSimpleFilterProvider(List<PedidoResumoFilterDTO> pedidoDTOS, String campos) {

        Set<String> camposDaClasse = getCamposClasse(PedidoResumoFilterDTO.class); // pega os campos que existe na classe
        MappingJacksonValue pedidosWrapper = new MappingJacksonValue(pedidoDTOS); // encapsula a lista pedidoDTOS

        // SimpleFilterProvider é utilizado para associar um filtro específico a um nome de filtro, neste caso,
        // o filtro "pedidoFilter" que foi especificado dentro da classe PedidoResumoFilterDTO
        SimpleFilterProvider filterProvider = new SimpleFilterProvider();

        filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.serializeAll()); // Serializa todos os campos por padrão

        if (StringUtils.isNotBlank(campos)) { // Se o 'campo' não estiver nulo e nem vazio

            String[] camposArray = Arrays.stream(campos.split(",")) // Pega os campos separando pela ','
                .map(String::trim)     // remove os espacos em branco
                .filter(StringUtils::isNotBlank) // remove os campos vazios
                .toArray(String[]::new); // // Coleta o resultado em um array de String

            Set<String> camposInexistentes = Arrays.stream(camposArray)
                .map(campo -> campo.trim())     // remove os espacos em branco
                .filter(campo -> !camposDaClasse.contains(campo)) // filtra pelos campos que não existem na classe
                .collect(Collectors.toSet());

            validaOsCampoExistenteNaClasse(camposInexistentes, camposArray);

            // Serializa apenas os campos especificados na API
            filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.filterOutAllExcept(camposArray));
        }

        pedidosWrapper.setFilters(filterProvider); // Seta o filtro configurado no objeto
        return pedidosWrapper;
    }


    /** Pega os campos da classe */
    private static Set<String> getCamposClasse(Class<?> classe) {

        ObjectMapper objectMapper = new ObjectMapper();
        SerializationConfig config = objectMapper.getSerializationConfig();

        // Obtém as definições de propriedades da classe
        List<BeanPropertyDefinition> propriedades = objectMapper.getSerializationConfig()
            .introspect(objectMapper.constructType(classe))
            .findProperties();

        // Extrai o nome das propriedades (campos) e retorna como um Set de Strings
        return propriedades.stream()
            .map(BeanPropertyDefinition::getName)
            .collect(Collectors.toSet());
    }


    /** Se todos os campos fornecidos forem inválidos, lança uma exceção ou retorna um erro */
    private static void validaOsCampoExistenteNaClasse(Collection<String> camposInexistentes, String[] camposArray) {

        camposInexistentes.forEach(x -> System.out.println("Campo: " + x));

        // Se 'camposInexistentes' não estiver vazio e 'camposInexistentes' for do mesmo tamanho que 'camposArray' então
        // significa que todos os campos que estão vindo do @RequestParam não existem na classe
        if (!camposInexistentes.isEmpty() && camposInexistentes.size() == camposArray.length) {
            throw new FiltroException("Nenhum campo válido foi fornecido. Campos inválidos: " + camposInexistentes);
        }
    }


    /** Converte os campos que vem na ordenação(parâmetro sort) que está vindo da API do que são da classe 'PedidoResumoDTO'
    para os campos da classe 'PedidoModel', isso evita PropertyReferenceException na paginação */
    private static Pageable traduzirPageable(Pageable pageable) {

        Map<String, String> mapeamento = Map.of( // também pode ser usado o Map.of do java.util

            // valor que está vindo da API | valor convertido para
            "codigo", "codigo",
            "restaurante", "restaurante.nome",
            "nomeCliente", "cliente.nome",
            "valorTotal", "valorTotal",
            "nome", "cliente.nome"
        );

        return PageableTranslator.translate(pageable, mapeamento);
    }


    protected PedidoModel findPedidoModelById(Long pedidoId) {
        return pedidoRepository.findById(pedidoId).orElseThrow(() ->
            new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de pedido com id: %d", pedidoId)));
    }


    protected PedidoModel findPedidoModelByCodigo(String codigoPedido) {
        return pedidoRepository.findByCodigo(codigoPedido).orElseThrow(() ->
            new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de pedido com codigo: %s", codigoPedido)));
    }

}
