package com.algaworks.algafood.domain.exceptions;

public class FotoProdutoNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public FotoProdutoNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public FotoProdutoNaoEncontradaException(Long produtoId, Long restauranteId) {
        this( String.format("Não existe cadastro de foto para o produto com código: %d do restaurante com código: %d",
            produtoId, restauranteId));
    }
}
