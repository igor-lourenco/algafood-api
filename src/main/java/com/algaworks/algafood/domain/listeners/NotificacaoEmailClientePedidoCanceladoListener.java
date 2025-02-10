package com.algaworks.algafood.domain.listeners;

import com.algaworks.algafood.domain.events.PedidoCanceladoEvent;
import com.algaworks.algafood.domain.models.PedidoModel;
import com.algaworks.algafood.infrastructure.services.EnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/** Essa classe escuta o evento registrado na confirmação de pedido e assim que o Spring Data salva o model 'PedidoModel' no banco de dados,
 * o próprio Spring Data dispara o evento que essa classe está escutando e assim executa o método para enviar automaticamente um e-mail para o cliente
 * notificando que o pedido foi confirmado. */
@Component
public class NotificacaoEmailClientePedidoCanceladoListener {

    @Autowired
    private EnvioEmailService envioEmailService;

//  @EventListener // Anotação que marca o método como ouvinte de eventos de aplicação.
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT) // Indíca qual a fase especifica da transação que esse evento deve ser disparado
    public void aoCancelarPedido(PedidoCanceladoEvent event) {
        PedidoModel pedidoModel = event.getPedidoModel();

        EnvioEmailService.Mensagem mensagem = EnvioEmailService.Mensagem.builder()
            .assunto(pedidoModel.getRestaurante().getNome() + " - Pedido cancelado")
            .corpo("emails/pedido-cancelado.html") // // nome do arquivo que está na pasta templates do projeto para usar como base do corpo
            .variavel("pedido", pedidoModel) // passa o objeto para ser processado no template carregado 'pedido-cancelado.html'
            .destinatario(pedidoModel.getCliente().getEmail())
            .build();

        envioEmailService.enviar(mensagem);
    }
}
