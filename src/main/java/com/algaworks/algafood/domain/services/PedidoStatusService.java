package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.domain.models.PedidoModel;
import com.algaworks.algafood.domain.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoStatusService {

    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private PedidoRepository pedidoRepository;

    @Transactional
    public void confirmaPedido(String codigoPedido) {
        PedidoModel pedidoModel = pedidoService.findPedidoModelByCodigo(codigoPedido);

        pedidoModel.confirma(); // também registra o evento
        pedidoRepository.save(pedidoModel); // assim que salvar no banco de dados vai realmente disparar o evento

//      Obs: Para realmente funcionar o registro e disparo do evento, o repository precisa ser do Spring Data porque quem
//      realmente realiza o processo de registrar e disparar os eventos é o Spring Data.
    }


    @Transactional
    public void entregaPedido(String codigoPedido) {
        PedidoModel pedidoModel = pedidoService.findPedidoModelByCodigo(codigoPedido);

        pedidoModel.entrega();

/*      Obs: Como o pedidoModel está em estado gerenciado pelo EntityManager, significa que qualquer alteração feita
        na entidade será automaticamente sincronizada com o banco de dados ao final da transação,
        sem a necessidade de chamar explicitamente um método save() por exemplo.
*/
    }


    @Transactional
    public void cancelaPedido(String codigoPedido) {
        PedidoModel pedidoModel = pedidoService.findPedidoModelByCodigo(codigoPedido);

        pedidoModel.cancela(); // também registra o evento
        pedidoRepository.save(pedidoModel); // assim que salvar no banco de dados vai realmente disparar o evento

//      Obs: Para realmente funcionar o registro e disparo do evento, o repository precisa ser do Spring Data porque quem
//      realmente realiza o processo de registrar e disparar os eventos é o Spring Data.
    }
}
