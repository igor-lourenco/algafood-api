package com.algaworks.algafood.domain.listeners;

import com.algaworks.algafood.domain.events.PedidoConfirmadoEvent;
import com.algaworks.algafood.domain.models.PedidoModel;
import com.algaworks.algafood.infrastructure.services.EnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/** Essa classe escuta o evento registrado na confirmação de pedido e assim que o Spring Data salva o model 'PedidoModel' no banco de dados,
 * o próprio Spring Data dispara o evento que essa classe está escutando e assim executa o método para enviar automaticamente um e-mail para o cliente
 * notificando que o pedido foi confirmado. */
@Component
public class NotificacaoEmailClientePedidoConfirmadoListener {

    @Autowired
    private EnvioEmailService envioEmailService;

    @EventListener // Anotação que marca o método como ouvinte de eventos de aplicação.
    public void aoConfirmarPedido(PedidoConfirmadoEvent event) {
        PedidoModel pedidoModel = event.getPedidoModel();

        EnvioEmailService.Mensagem mensagem = EnvioEmailService.Mensagem.builder()
            .assunto(pedidoModel.getRestaurante().getNome() + " - Pedido confirmado")
            .corpo("pedido-confirmado.html")
            .variavel("pedido", pedidoModel) // passa o objeto para ser processado no template carregado 'pedido-confirmado.html'
            .destinatario(pedidoModel.getCliente().getEmail())
            .build();

        envioEmailService.enviar(mensagem);
    }
}
