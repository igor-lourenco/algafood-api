package com.algaworks.algafood.infrastructure.repositories.impl;

import com.algaworks.algafood.domain.models.FotoProdutoModel;
import com.algaworks.algafood.infrastructure.repositories.FotoProdutoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class FotoProdutoRepositoryImpl implements FotoProdutoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional
    public FotoProdutoModel save(FotoProdutoModel fotoProdutoModel) {
        return manager.merge(fotoProdutoModel);
    }

    @Transactional
    @Override
    public void delete(FotoProdutoModel fotoProdutoModel) {

        manager.remove(fotoProdutoModel);
    }
}
