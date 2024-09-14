package com.algaworks.algafood.api.controllers;


import com.algaworks.algafood.api.DTOs.CidadeDTO;
import com.algaworks.algafood.api.DTOs.PedidoDTO;
import com.algaworks.algafood.api.DTOs.PedidoResumoDTO;
import com.algaworks.algafood.api.inputs.CidadeInput;
import com.algaworks.algafood.api.inputs.PedidoInput;
import com.algaworks.algafood.domain.services.CidadeService;
import com.algaworks.algafood.domain.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
}
