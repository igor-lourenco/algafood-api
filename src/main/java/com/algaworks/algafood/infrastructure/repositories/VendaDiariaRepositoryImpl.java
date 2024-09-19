package com.algaworks.algafood.infrastructure.repositories;

import com.algaworks.algafood.domain.filters.VendaDiariaFilter;
import com.algaworks.algafood.domain.models.VendaDiaria;
import com.algaworks.algafood.domain.repositories.VendaDiariaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class VendaDiariaRepositoryImpl<T> implements VendaDiariaRepository<T> {

    @Override
    public List<T> consultaVendasDiarias(VendaDiariaFilter filtro) {



        return Arrays.asList();
    }
}
