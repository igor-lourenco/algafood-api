package com.algaworks.algafood.api.assemblers;

import com.algaworks.algafood.api.DTOs.FormaPagamentoDTO;
import com.algaworks.algafood.api.DTOs.ProdutoDTO;
import com.algaworks.algafood.api.inputs.CidadeInput;
import com.algaworks.algafood.api.inputs.PedidoInput;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.models.*;
import com.algaworks.algafood.domain.repositories.CidadeRepository;
import com.algaworks.algafood.domain.repositories.EstadoRepository;
import com.algaworks.algafood.domain.services.FormaPagamentoService;
import com.algaworks.algafood.domain.services.RestauranteFormaPagamentoService;
import com.algaworks.algafood.domain.services.RestauranteProdutoService;
import com.algaworks.algafood.domain.services.RestauranteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class PedidoModelAssembler {

    @Autowired
    private RestauranteService restauranteService;
    @Autowired
    private RestauranteFormaPagamentoService restauranteFormaPagamentoService;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private RestauranteProdutoService restauranteProdutoService;

    @Autowired
    private ModelMapper modelMapper;

    /** Converte classe CidadeInput para classe CidadeModel */
    public void convertToPedidoModel(PedidoInput pedidoInput, PedidoModel pedidoModel){

        RestauranteModel restauranteModel = restauranteService.findRestauranteModel(pedidoInput.getRestauranteId());
        FormaPagamentoDTO formaPagamentoDTO = restauranteFormaPagamentoService.findFormaPagamentoByRestauranteId(pedidoInput.getRestauranteId(), pedidoInput.getFormaPagamentoId());

        CidadeModel cidadeModel = cidadeRepository.findById(pedidoInput.getEnderecoEntrega().getCidadeId()).orElseThrow(() ->
            new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de Cidade com id: %d", pedidoInput.getEnderecoEntrega().getCidadeId())));

        List<ItemPedidoModel> itemPedidoModels = new ArrayList<>();

        pedidoInput.getItens().forEach(item -> {

            ItemPedidoModel itemPedidoModel = new ItemPedidoModel();
            ProdutoDTO produtoDTO = restauranteProdutoService.findAllProdutosById(pedidoInput.getRestauranteId(), item.getProdutoId());

            itemPedidoModel.setPrecoUnitario(produtoDTO.getPreco());
            itemPedidoModel.setPrecoTotal(produtoDTO.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade())));
            itemPedidoModel.setObservacao(item.getObservacao());
            itemPedidoModel.setProduto(restauranteModel.getProdutos().stream().filter(p -> p.getId() == produtoDTO.getId()).findAny().get());

            itemPedidoModels.add(itemPedidoModel);
        });

        restauranteModel.getEndereco().setCidade(cidadeModel);
        pedidoModel.setRestaurante(restauranteModel);
        pedidoModel.setEnderecoEntrega(restauranteModel.getEndereco());
        pedidoModel.getItens().addAll(itemPedidoModels);


        modelMapper.map(pedidoInput, pedidoModel);

    }

}
