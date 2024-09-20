package com.algaworks.algafood.domain.repositories;

import com.algaworks.algafood.domain.filters.VendaDiariaFilter;
import com.algaworks.algafood.domain.models.UsuarioModel;
import com.algaworks.algafood.domain.models.VendaDiaria;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VendaDiariaRepository<T> {

    List<T> consultaVendasDiarias(VendaDiariaFilter filtro, Class<T> clazz);
}
