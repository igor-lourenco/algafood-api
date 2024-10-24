package com.algaworks.algafood.api.controllers;


import com.algaworks.algafood.api.DTOs.PedidoDTO;
import com.algaworks.algafood.api.DTOs.PedidoResumoDTO;
import com.algaworks.algafood.api.DTOs.jsonFilter.PedidoResumoFilterDTO;
import com.algaworks.algafood.api.inputs.PedidoInput;
import com.algaworks.algafood.domain.filters.PedidoFilter;
import com.algaworks.algafood.domain.services.PedidoService;
import com.algaworks.algafood.infrastructure.repositories.specs.PedidoSpecs;
import com.algaworks.algafood.swaggerOpenApi.controllers.PedidoControllerOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/pedidos", produces = {MediaType.APPLICATION_JSON_VALUE})
public class PedidoController implements PedidoControllerOpenApi {

    @Autowired
    private PedidoService pedidoService;


    @GetMapping
    public ResponseEntity<List<PedidoResumoDTO>> lista() {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.listar());

    }


    @GetMapping(value = "/{codigoPedido}")
    public ResponseEntity<PedidoDTO> BuscaPeloCodigo(@PathVariable String codigoPedido) {
        PedidoDTO pedidoDTO = pedidoService.findByCodigo(codigoPedido);
        return ResponseEntity.status(HttpStatus.OK).body(pedidoDTO);

    }


//  TODO: Depois pegar o usuário pela autenticação, por enquanto usar o cliente fixo
    @PostMapping
    public ResponseEntity<PedidoDTO> salva(@Valid @RequestBody PedidoInput pedidoInput){
        PedidoDTO pedidoDTO = pedidoService.savePedido(pedidoInput);

        return ResponseEntity.ok(pedidoDTO);
    }


//    Comentado porque vai ser usado a API que busca pelo 'codigo' do Pedido
//    @GetMapping(value = "{pedidoId}")
//    public ResponseEntity<PedidoDTO> findById(@PathVariable Long pedidoId) {
//
//        PedidoDTO pedidoDTO = pedidoService.findById(pedidoId);
//        return ResponseEntity.status(HttpStatus.OK).body(pedidoDTO);
//
//    }


//   TODO: Obs: Como está usando o Squiggly para fazer o filtro dos campos, quando essa API vai retornar todos os campos, dá erro no objeto 'enderecoEntrega'
    /** Essa API é um exemplo de como utilizar a annotation @JsonFilter da biblioteca Jackson para filtrar os campos dinamicamente durante a serialização de objetos JSON */
    @GetMapping("/com-json-filter")
    public MappingJacksonValue listaPedidoComJsonFilter(@RequestParam(required = false) String campos) {
        List<PedidoResumoFilterDTO> pedidoDTOS = pedidoService.listaPedidoComJsonFilter();
        MappingJacksonValue pedidosWrapper = pedidoService.listaFiltradaComSimpleFilterProvider(pedidoDTOS, campos);

        return pedidosWrapper;
    }


    /** Essa API é um exemplo de como utilizar os campos da classe passando como parâmetro na API e utilizando o Specification para consulta personalizada para filtrar. */
    @GetMapping("/pesquisa")
    public ResponseEntity<List<PedidoResumoDTO>> pesquisa(PedidoFilter filtro) {
        List<PedidoResumoDTO> pedidoResumoDTOS = pedidoService.listar(PedidoSpecs.usandoFiltro(filtro));

        return ResponseEntity.status(HttpStatus.OK).body(pedidoResumoDTOS);

    }

    /** Essa API é um exemplo de como utilizar os campos da classe passando como parâmetro na API e utilizando o Specification com paginação para consulta personalizada para filtro. */
    @GetMapping("/pesquisa/page")
    public Page<PedidoResumoDTO> pesquisaPage(PedidoFilter filtro, @PageableDefault(size = 12) Pageable pageable) {
        Page<PedidoResumoDTO> pedidoResumoDTOS = pedidoService.listar(PedidoSpecs.usandoFiltro(filtro), pageable);

        return pedidoResumoDTOS;

    }
}
