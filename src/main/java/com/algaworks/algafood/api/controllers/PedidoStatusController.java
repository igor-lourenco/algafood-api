package com.algaworks.algafood.api.controllers;


import com.algaworks.algafood.api.DTOs.PedidoDTO;
import com.algaworks.algafood.api.DTOs.PedidoResumoDTO;
import com.algaworks.algafood.api.inputs.PedidoInput;
import com.algaworks.algafood.domain.services.PedidoService;
import com.algaworks.algafood.domain.services.PedidoStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/pedidos/{codigoPedido}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class PedidoStatusController {

    @Autowired
    private PedidoStatusService pedidoStatusService;

    @PutMapping("/confirma")
    public ResponseEntity<?> confirmaPedido(@PathVariable String codigoPedido){
        pedidoStatusService.confirmaPedido(codigoPedido);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PutMapping("/entrega")
    public ResponseEntity<?> entregaPedido(@PathVariable String codigoPedido){
        pedidoStatusService.entregaPedido(codigoPedido);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PutMapping("/cancela")
    public ResponseEntity<?> cancelaPedido(@PathVariable String codigoPedido){
        pedidoStatusService.cancelaPedido(codigoPedido);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
