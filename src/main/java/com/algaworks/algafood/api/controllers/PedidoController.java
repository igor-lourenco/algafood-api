package com.algaworks.algafood.api.controllers;


import com.algaworks.algafood.api.DTOs.PedidoDTO;
import com.algaworks.algafood.api.DTOs.PedidoResumoDTO;
import com.algaworks.algafood.api.DTOs.jsonFilter.PedidoResumoFilterDTO;
import com.algaworks.algafood.api.inputs.PedidoInput;
import com.algaworks.algafood.domain.repositories.filters.PedidoFilter;
import com.algaworks.algafood.domain.services.PedidoService;
import com.algaworks.algafood.infrastructure.repositories.specs.PedidoSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/pedidos", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<PedidoResumoDTO>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.listar());

    }


    @GetMapping(value = "/{codigoPedido}")
    public ResponseEntity<PedidoDTO> findByCodigo(@PathVariable String codigoPedido) {
        PedidoDTO pedidoDTO = pedidoService.findByCodigo(codigoPedido);
        return ResponseEntity.status(HttpStatus.OK).body(pedidoDTO);

    }


    // Usar o cliente fixo
    @PostMapping
    public ResponseEntity<PedidoDTO> savePedido(@Valid @RequestBody PedidoInput pedidoInput){
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


    /** Essa API é um exemplo de como utilizar a annotation @JsonFilter da biblioteca Jackson para filtrar os campos dinamicamente durante a serialização de objetos JSON */
    @GetMapping("/com-json-filter")
    public MappingJacksonValue listaPedidoComJsonFilter(@RequestParam(required = false) String campos) {
        List<PedidoResumoFilterDTO> pedidoDTOS = pedidoService.listaPedidoComJsonFilter();
        MappingJacksonValue pedidosWrapper = pedidoService.listaFiltradaComSimpleFilterProvider(pedidoDTOS, campos);

        return pedidosWrapper;
    }


    /** Essa API é um exemplo de como utilizar os campos da classe passando como parâmetro na API e utilizando o Specification para consulta personalizada para filtrar. */
    @GetMapping("/pesquisar")
    public ResponseEntity<List<PedidoResumoDTO>> pesquisar(PedidoFilter filtro) {

        List<PedidoResumoDTO> pedidoResumoDTOS = pedidoService.listar(PedidoSpecs.usandoFiltro(filtro));

        return ResponseEntity.status(HttpStatus.OK).body(pedidoResumoDTOS);

    }


}
