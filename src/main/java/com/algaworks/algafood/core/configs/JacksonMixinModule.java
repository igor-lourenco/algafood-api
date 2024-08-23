package com.algaworks.algafood.core.configs;

import com.algaworks.algafood.api.models.mixin.CidadeModelMixin;
import com.algaworks.algafood.api.models.mixin.RestauranteModelMixin;
import com.algaworks.algafood.domain.models.CidadeModel;
import com.algaworks.algafood.domain.models.RestauranteModel;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

/** Essa classe é um Mixin do Jackson, ou seja, é uma maneira de associar uma classe (que não pode ou não deve ser modificada diretamente)
 * com anotações adicionais, como as usadas para controlar a serialização e desserialização.
 * Isso é útil quando você quer adicionar anotações da biblioteca Jackson, remover ou alterar como propriedades de uma classe são tratadas pelo
 * Jackson, mas sem alterar o código da classe original. */
@Component
public class JacksonMixinModule extends SimpleModule {
    private static final long serialVersionUID = 1L;

    public JacksonMixinModule(){

//        Foi comentado porque usa as classes de input e DTO para converter as classes de modelo(Model)
//        setMixInAnnotation(RestauranteModel.class, RestauranteModelMixin.class);
//        setMixInAnnotation(CidadeModel.class, CidadeModelMixin.class);

    }
}
