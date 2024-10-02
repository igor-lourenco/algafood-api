package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.DTOs.PedidoDTO;
import com.algaworks.algafood.api.DTOs.PedidoResumoDTO;
import com.algaworks.algafood.api.assemblers.DTOs.PedidoDTOAssembler;
import com.algaworks.algafood.api.assemblers.PedidoModelAssembler;
import com.algaworks.algafood.api.inputs.PedidoInput;
import com.algaworks.algafood.domain.enums.StatusPedido;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exceptions.StatusException;
import com.algaworks.algafood.domain.models.PedidoModel;
import com.algaworks.algafood.domain.repositories.PedidoRepository;
import com.algaworks.algafood.infrastructure.services.EnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoStatusService {

    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private EnvioEmailService envioEmailService;

    @Transactional
    public void confirmaPedido(String codigoPedido){
        PedidoModel pedidoModel = pedidoService.findPedidoModelByCodigo(codigoPedido);

        /* Obs: Como o pedidoModel está em estado gerenciado pelo EntityManager, significa que qualquer alteração feita
          na entidade será automaticamente sincronizada com o banco de dados ao final da transação,
          sem a necessidade de chamar explicitamente um método save() por exemplo.*/
        pedidoModel.confirma();

        EnvioEmailService.Mensagem mensagem = EnvioEmailService.Mensagem.builder()
            .assunto(pedidoModel.getRestaurante().getNome() + " - Pedido confirmado")
            .corpo("pedido-confirmado.html")
            .variavel("pedido", pedidoModel) // passa o objeto para ser processado no template carregado 'pedido-confirmado.html'
            .destinatario(pedidoModel.getCliente().getEmail())
            .build();

        envioEmailService.enviar(mensagem);

    }


    @Transactional
    public void entregaPedido(String codigoPedido){
        PedidoModel pedidoModel = pedidoService.findPedidoModelByCodigo(codigoPedido);

        pedidoModel.entrega();

        /* Obs: Como o pedidoModel está em estado gerenciado pelo EntityManager, significa que qualquer alteração feita
            na entidade será automaticamente sincronizada com o banco de dados ao final da transação,
            sem a necessidade de chamar explicitamente um método save() por exemplo.
        */
    }


    @Transactional
    public void cancelaPedido(String codigoPedido){
        PedidoModel pedidoModel = pedidoService.findPedidoModelByCodigo(codigoPedido);

        pedidoModel.cancela();

        /* Obs: Como o pedidoModel está em estado gerenciado pelo EntityManager, significa que qualquer alteração feita
            na entidade será automaticamente sincronizada com o banco de dados ao final da transação,
            sem a necessidade de chamar explicitamente um método save() por exemplo.
        */
    }
}
