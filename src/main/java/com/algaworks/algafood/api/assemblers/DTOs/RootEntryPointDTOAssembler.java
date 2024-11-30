package com.algaworks.algafood.api.assemblers.DTOs;

import com.algaworks.algafood.api.DTOs.RootEntryPointDTO;
import com.algaworks.algafood.api.assemblers.links.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RootEntryPointDTOAssembler {

    @Autowired
    private CozinhaLinks cozinhaLinks;
    @Autowired
    private PedidoLinks pedidoLinks;
    @Autowired
    private RestauranteLinks restauranteLinks;
    @Autowired
    private GrupoLinks grupoLinks;
    @Autowired
    private UsuarioLinks usuarioLinks;
    @Autowired
    private PermissaoLinks permissaoLinks;
    @Autowired
    private FormaPagamentoLinks formaPagamentoLinks;
    @Autowired
    private EstadoLinks estadoLinks;
    @Autowired
    private CidadeLinks cidadeLinks;

    public RootEntryPointDTO toModel(){
        RootEntryPointDTO rootEntryPointDTO = new RootEntryPointDTO();

        rootEntryPointDTO.add(cozinhaLinks.addCollectionLink("cozinhas"));
        rootEntryPointDTO.add(pedidoLinks.addCollectionLink("pedidos"));
        rootEntryPointDTO.add(restauranteLinks.addCollectionLink("restaurantes"));
        rootEntryPointDTO.add(grupoLinks.addCollectionLink("grupos"));
        rootEntryPointDTO.add(usuarioLinks.addCollectionLink("usuarios"));
        rootEntryPointDTO.add(permissaoLinks.addCollectionLink("permissoes"));
        rootEntryPointDTO.add(formaPagamentoLinks.addCollectionLink("formas-pagamento", null));
        rootEntryPointDTO.add(estadoLinks.addCollectionLink("estados"));
        rootEntryPointDTO.add(cidadeLinks.addCollectionLink("cidades"));

        return rootEntryPointDTO;
    }

}
