package com.algaworks.algafood.api.assemblers;

import com.algaworks.algafood.api.DTOs.ProdutoDTO;
import com.algaworks.algafood.api.inputs.PedidoInput;
import com.algaworks.algafood.domain.models.*;
import com.algaworks.algafood.domain.services.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PedidoModelAssembler {

    @Autowired
    private RestauranteService restauranteService;
    @Autowired
    private RestauranteFormaPagamentoService restauranteFormaPagamentoService;
    @Autowired
    private RestauranteProdutoService restauranteProdutoService;
    @Autowired
    private CidadeService cidadeService;
    @Autowired
    private ModelMapper modelMapper;

    /** Converte classe PedidoInput para classe PedidoModel */
    public void convertToPedidoModel(PedidoInput pedidoInput, PedidoModel pedidoModel){

        RestauranteModel restauranteModel = restauranteService.findRestauranteModel(pedidoInput.getRestauranteId());
        CidadeModel cidadeModel = cidadeService.findCidadeModelByCidadeId(pedidoInput.getEnderecoEntrega().getCidadeId());

        List<ItemPedidoModel> itemPedidoModels = new ArrayList<>();

        pedidoInput.getItens().forEach(item -> {

            ItemPedidoModel itemPedidoModel = new ItemPedidoModel();
            ProdutoDTO produtoDTO = restauranteProdutoService.findProdutoById(pedidoInput.getRestauranteId(), item.getProdutoId());

            itemPedidoModel.setPrecoUnitario(produtoDTO.getPreco());
            itemPedidoModel.setQuantidade(item.getQuantidade());
            itemPedidoModel.setObservacao(item.getObservacao());
            itemPedidoModel.calculaPrecoTotal();

            itemPedidoModel.setProduto(restauranteModel.getProdutos(), produtoDTO.getId());
            itemPedidoModel.setPedido(pedidoModel);

            itemPedidoModels.add(itemPedidoModel);
        });

        restauranteModel.setEndereco(new Endereco()); // Para evitar NullPointerException
        restauranteModel.getEndereco().setCidade(cidadeModel);
        pedidoModel.setRestaurante(restauranteModel);

        modelMapper.map(pedidoInput, pedidoModel);

        pedidoModel.getItens().clear();
        pedidoModel.getItens().addAll(itemPedidoModels);
    }
}
