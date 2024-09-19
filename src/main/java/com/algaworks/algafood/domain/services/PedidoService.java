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
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
    public List<PedidoResumoDTO> listar(){
        List<PedidoModel> pedidoModels  = pedidoRepository.findAll();

        List<PedidoResumoDTO> pedidoDTOS = pedidoModels.stream().map(pedidoModel ->
                pedidoDTOAssembler.convertToPedidoResumoDTOBuilder(pedidoModel).build())
            .collect(Collectors.toList());

        return pedidoDTOS;
    }

    @Transactional(readOnly = true)
    public List<PedidoResumoDTO> listar(Specification<PedidoModel> pedidoModelSpecification) {
        List<PedidoModel> pedidoModels  = pedidoRepository.findAll(pedidoModelSpecification);

        List<PedidoResumoDTO> pedidoDTOS = pedidoModels.stream().map(pedidoModel ->
                pedidoDTOAssembler.convertToPedidoResumoDTOBuilder(pedidoModel).build())
            .collect(Collectors.toList());

        return pedidoDTOS;
    }


    @Transactional(readOnly = true)
    public Page<PedidoResumoDTO> listar(Specification<PedidoModel> pedidoModelSpecification, Pageable pageable) {

        // Converte os campos de ordenação(parâmetro sort) que está vindo da API do tipo 'PedidoResumoDTO' para 'PedidoModel' para evitar PropertyReferenceException na paginação
        pageable = traduzirPageable(pageable);

        Page<PedidoModel> pedidoModelPage  = pedidoRepository.findAll(pedidoModelSpecification, pageable);

        List<PedidoResumoDTO> pedidoDTOS = pedidoModelPage.getContent().stream().map(pedidoModel ->
                pedidoDTOAssembler.convertToPedidoResumoDTOBuilder(pedidoModel).build())
            .collect(Collectors.toList());

        Page<PedidoResumoDTO> pedidoResumoDTOPage = new PageImpl<>(pedidoDTOS, pageable, pedidoModelPage.getTotalPages());

        return pedidoResumoDTOPage;
    }

    private Pageable traduzirPageable(Pageable pageable) {

        ImmutableMap<String, String> mapeamento = ImmutableMap.of( // também pode ser usado o Map.of do java.util

            // valor que está vindo da API | valor convertido para
            "codigo",                    "codigo",
            "restaurante.nome",     "restaurante.nome",
            "nomeCliente",          "cliente.nome",
            "valorTotal",           "valorTotal"
        );

        return PageableTranslator.translate(pageable, mapeamento);
    }


    @Transactional(readOnly = true)
    public List<PedidoResumoFilterDTO> listaPedidoComJsonFilter(){
        List<PedidoModel> pedidoModels  = pedidoRepository.findAll();

        List<PedidoResumoFilterDTO> pedidoDTOS = pedidoModels.stream().map(pedidoModel ->
                pedidoDTOAssembler.convertToPedidoResumoFilterDTOBuilder(pedidoModel).build())
            .collect(Collectors.toList());

        return pedidoDTOS;
    }


    @Transactional(readOnly = true)
    public PedidoDTO findById(Long pedidoId){
        PedidoModel pedidoModel  = findPedidoModelById(pedidoId);

        PedidoDTO pedidoDTO = pedidoDTOAssembler.convertToPedidoDTOBuilder(pedidoModel).build();
        return pedidoDTO;
    }


    @Transactional(readOnly = true)
    public PedidoDTO findByCodigo(String codigoPedido){
        PedidoModel pedidoModel  = findPedidoModelByCodigo(codigoPedido);

        PedidoDTO pedidoDTO = pedidoDTOAssembler.convertToPedidoDTOBuilder(pedidoModel).build();
        return pedidoDTO;
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

        PedidoDTO pedidoDTO = pedidoDTOAssembler.convertToPedidoDTOBuilder(pedidoModel).build();
        return pedidoDTO;
    }


    /** Esse método retorna um objeto MappingJacksonValue com o filtro aplicado que foi especificado com
      annotation @JsonFilter na classe annotation PedidoResumoFilterDTO. O objeto MappingJacksonValue será serializado em
      JSON, contendo apenas os campos que o cliente especificou (caso tenham sido fornecidos) ou todos os
      campos, caso o cliente não tenha solicitado nenhum filtro.*/
    public MappingJacksonValue listaFiltradaComSimpleFilterProvider(List<PedidoResumoFilterDTO> pedidoDTOS, String campos) {

        Set<String> camposDaClasse = getCamposClasse(PedidoResumoFilterDTO.class); // pega os campos que existe na classe
        MappingJacksonValue pedidosWrapper = new MappingJacksonValue(pedidoDTOS); // encapsula a lista pedidoDTOS

        // SimpleFilterProvider é utilizado para associar um filtro específico a um nome de filtro, neste caso,
        // o filtro "pedidoFilter" que foi especificado dentro da classe PedidoResumoFilterDTO
        SimpleFilterProvider filterProvider = new SimpleFilterProvider();

        filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.serializeAll()); // Serializa todos os campos por padrão

        if (StringUtils.isNotBlank(campos)) { // Se o 'campo' não estiver nulo e nem vazio

            String[] camposArray = Arrays.stream(campos.split(",")) // Pega os campos separando pela ','
                .map( String::trim)     // remove os espacos em branco
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

     /** Pega os campos da classe  */
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
    private static void validaOsCampoExistenteNaClasse(Set<String> camposInexistentes, String[] camposArray) {

        camposInexistentes.forEach(x -> System.out.println("Campo: " + x));

        // Se 'camposInexistentes' não estiver vazio e 'camposInexistentes' for do mesmo tamanho que 'camposArray' então
        // significa que todos os campos que estão vindo do @RequestParam não existem na classe
        if (!camposInexistentes.isEmpty() && camposInexistentes.size() == camposArray.length) {
            throw new FiltroException("Nenhum campo válido foi fornecido. Campos inválidos: " + camposInexistentes);
        }
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
