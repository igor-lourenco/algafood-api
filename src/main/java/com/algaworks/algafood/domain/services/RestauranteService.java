package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.core.constraints.groups.Groups;
import com.algaworks.algafood.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exceptions.ValidacaoException;
import com.algaworks.algafood.domain.models.CozinhaModel;
import com.algaworks.algafood.domain.models.RestauranteModel;
import com.algaworks.algafood.domain.repositories.CozinhaRepository;
import com.algaworks.algafood.domain.repositories.RestauranteRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private CozinhaService cozinhaService;
    @Autowired
    private SmartValidator smartValidator; // Variante estendida da interface do Validador, adicionando suporte para 'grupos' de validação.


    public List<RestauranteModel> listar(){
        List<RestauranteModel> listaRestaurantes  = restauranteRepository.findAll();

        return listaRestaurantes;
    }


    public RestauranteModel buscaPorId(Long id){
        Optional<RestauranteModel> restauranteOptional = restauranteRepository.findById(id);

        if(restauranteOptional.isEmpty()){
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de restaurante com código: %d", id));
        }

        return restauranteOptional.get();
    }


    @Transactional // Se der tudo certo e não lançar nenhuma exception na transação, dá um commit no banco, senão dá rollback para manter a consistência no banco
    public RestauranteModel salvar(RestauranteModel restauranteModel){
        CozinhaModel cozinhaModel = cozinhaService.buscaPorId(restauranteModel.getCozinha().getId());

        restauranteModel.setCozinha(cozinhaModel);
        restauranteModel = restauranteRepository.save(restauranteModel);

        return restauranteModel;
    }


    @Transactional // Se der tudo certo e não lançar nenhuma exception na transação, dá um commit no banco, senão dá rollback para manter a consistência no banco
    public RestauranteModel alterar(Long id, RestauranteModel restauranteModel){
        RestauranteModel restaurante = buscaPorId(id);
        CozinhaModel cozinhaModel = cozinhaService.buscaPorId(restauranteModel.getCozinha().getId());

        restaurante.setNome(restauranteModel.getNome());
        restaurante.setCozinha(cozinhaModel);
        restaurante = restauranteRepository.save(restaurante);

        return restaurante;
    }


    @Transactional // Se der tudo certo e não lançar nenhuma exception na transação, dá um commit no banco, senão dá rollback para manter a consistência no banco
    public RestauranteModel alterarParcial(Long id,  Map<String, Object> campos, HttpServletRequest request){
        RestauranteModel restaurante = buscaPorId(id);

        merge(campos, restaurante, request);

        validate(restaurante, "restauranteModel");

        restaurante = restauranteRepository.save(restaurante);

        return restaurante;
    }


    private void validate(RestauranteModel restaurante, String objectName) {

//      Implementação padrão das interfaces 'Errors' e 'BindingResult', para registro e avaliação de erros de ligação em objetos JavaBean.
        BeanPropertyBindingResult  bindingResult = new BeanPropertyBindingResult(restaurante, objectName);

//      Vai validar o objeto passado no primeiro parametro e se caso tiver algum erro de validação, vai adicionar no 'bindingResult'
        smartValidator.validate(restaurante, bindingResult, Groups.CadastroRestaurante.class);

        if(bindingResult.hasErrors()){ // Se tiver algum erro de validação
            throw new ValidacaoException(bindingResult); // Lança a nossa exception personalizada
        }
    }


    @Transactional // Se der tudo certo e não lançar nenhuma exception na transação, dá um commit no banco, senão dá rollback para manter a consistência no banco
    public void deletar(Long id) {
        try {
            restauranteRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            System.out.println("ERROR: " + e.getMessage());
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de restaurante com código: %d", id));
        } catch (DataIntegrityViolationException e) {
            System.out.println("ERROR: " + e.getMessage());
            throw new EntidadeEmUsoException(String.format("Restaurante de código: %d não pode ser removida, pois está em uso.", id));
        }
    }


    private void merge(Map<String, Object> objOrigem, RestauranteModel restauranteDestino, HttpServletRequest request){
        ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true); //Habilita para retornar erro quando passar campo que nao existe na entidade
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true); // Habilita para retornar erro quando passar campo que está mapeado para ser ignorada na entidade

            RestauranteModel restauranteOrigem = objectMapper.convertValue(objOrigem, RestauranteModel.class); // converte o objOrigem para uma instância de RestauranteModel

            objOrigem.forEach((campo, valor) -> {

                Field field = ReflectionUtils.findField(RestauranteModel.class, campo); // busca o campo preenchido correspondente no RestauranteModel
                field.setAccessible(true); // deixa o atributo do objeto privado acessível

                Object novoValor = ReflectionUtils.getField(field, restauranteOrigem); // Obtém o valor do campo do objeto 'restauranteOrigem'.

                ReflectionUtils.setField(field, restauranteDestino, novoValor);  // atribui o valor do campo preenchido ao campo do objeto destino com o 'novoValor' convertido

            });

        }catch (IllegalArgumentException e){
            Throwable rootCause = ExceptionUtils.getRootCause(e); // Pega a causa raiz da exception
            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest); // Relança para cair na nossa exception para ser tratada
        }
    }


    public List<RestauranteModel> findAll(Specification<RestauranteModel> and) {
        return restauranteRepository.findAll(and);
    }
}
