package com.algaworks.algafood.api.controllers;


import com.algaworks.algafood.domain.services.PedidoStatusService;
import com.algaworks.algafood.swaggerOpenApi.controllers.PedidoStatusControllerOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/pedidos/{codigoPedido}")
public class PedidoStatusController implements PedidoStatusControllerOpenApi {

    @Autowired
    private PedidoStatusService pedidoStatusService;

    @PutMapping("/confirma")
    public void confirmaPedido(@PathVariable String codigoPedido){
        pedidoStatusService.confirmaPedido(codigoPedido);
    }


    @PutMapping("/entrega")
    public void entregaPedido(@PathVariable String codigoPedido){
        pedidoStatusService.entregaPedido(codigoPedido);
    }


    @PutMapping("/cancela")
    public void cancelaPedido(@PathVariable String codigoPedido){
        pedidoStatusService.cancelaPedido(codigoPedido);
    }
}
