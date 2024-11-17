package com.algaworks.algafood.api.assemblers.models;

import com.algaworks.algafood.api.inputs.RestauranteInput;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.models.CidadeModel;
import com.algaworks.algafood.domain.models.CozinhaModel;
import com.algaworks.algafood.domain.models.RestauranteModel;
import com.algaworks.algafood.domain.repositories.CidadeRepository;
import com.algaworks.algafood.domain.repositories.CozinhaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class RestauranteModelAssembler {

    @Autowired
    private CozinhaRepository cozinhaRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private ModelMapper modelMapper;

    /** Converte classe RestauranteInput para classe RestauranteModel */
    public void convertToRestauranteModel(RestauranteInput restauranteInput, RestauranteModel restauranteModel){

        CozinhaModel cozinhaModel = cozinhaRepository.findById(restauranteInput.getCozinhaId()).orElseThrow(() ->
            new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de cozinha com código: %d", restauranteInput.getCozinhaId())));

        CidadeModel cidadeModel = cidadeRepository.findById(restauranteInput.getEndereco().getCidadeId()).orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de Cidade com id: %d", restauranteInput.getEndereco().getCidadeId())));


/*        Para evitar Exception: JpaSystemException
            identifier of an instance of com.algaworks.algafood.domain.models.CozinhaModel was altered from 1 to 2;
            nested exception is org.hibernate.HibernateException: identifier of an instance of com.algaworks.algafood.domain.models.CozinhaModel was altered from 1 to 2
            Obs: Outra opção seria configurar o ModelMapper para remover o mapeamento automático para entidade CozinhaModel, exemplo na linha 30 da classe ModelMapperConfig
*///        if(restauranteModel.getCozinha() != null){
//            restauranteModel.setCozinha(new CozinhaModel());
//        }

/*        Para evitar Exception: JpaSystemException
            identifier of an instance of com.algaworks.algafood.domain.models.CidadeModel was altered from 3 to 2;
            nested exception is org.hibernate.HibernateException: identifier of an instance of com.algaworks.algafood.domain.models.CidadeModel was altered from 3 to 2
*/      if (restauranteModel.getEndereco() != null){
            restauranteModel.getEndereco().setCidade(new CidadeModel());
        }

        modelMapper.map(restauranteInput, restauranteModel);

        restauranteModel.setCozinha(cozinhaModel);
        restauranteModel.getEndereco().setCidade(cidadeModel);

    }
}
