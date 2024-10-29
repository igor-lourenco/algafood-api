package com.algaworks.algafood.api.controllers;


import com.algaworks.algafood.domain.services.PedidoStatusService;
import com.algaworks.algafood.swaggerOpenApi.controllers.PedidoStatusControllerOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/pedidos/{codigoPedido}", produces = {MediaType.APPLICATION_JSON_VALUE})
public class PedidoStatusController implements PedidoStatusControllerOpenApi {

    @Autowired
    private PedidoStatusService pedidoStatusService;

    @PutMapping("/confirma")
    public ResponseEntity<Void> confirmaPedido(@PathVariable String codigoPedido){
        pedidoStatusService.confirmaPedido(codigoPedido);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PutMapping("/entrega")
    public ResponseEntity<Void> entregaPedido(@PathVariable String codigoPedido){
        pedidoStatusService.entregaPedido(codigoPedido);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PutMapping("/cancela")
    public ResponseEntity<Void> cancelaPedido(@PathVariable String codigoPedido){
        pedidoStatusService.cancelaPedido(codigoPedido);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
