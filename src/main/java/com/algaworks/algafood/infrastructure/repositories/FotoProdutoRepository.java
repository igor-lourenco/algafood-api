package com.algaworks.algafood.infrastructure.repositories;

import com.algaworks.algafood.domain.models.FotoProdutoModel;


public interface FotoProdutoRepository {

    FotoProdutoModel save(FotoProdutoModel fotoProdutoModel);

    void delete(FotoProdutoModel fotoProdutoModel);
}
