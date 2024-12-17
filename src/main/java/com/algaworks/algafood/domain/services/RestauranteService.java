package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.DTOs.RestauranteDTO;
import com.algaworks.algafood.api.DTOs.jsonView.RestauranteViewDTO;
import com.algaworks.algafood.api.assemblers.DTOs.RestauranteDTOAssembler;
import com.algaworks.algafood.api.assemblers.models.RestauranteModelAssembler;
import com.algaworks.algafood.api.inputs.RestauranteInput;
import com.algaworks.algafood.core.constraints.groups.Groups;
import com.algaworks.algafood.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exceptions.ValidacaoException;
import com.algaworks.algafood.domain.models.RestauranteModel;
import com.algaworks.algafood.domain.repositories.RestauranteRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Log4j2
@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private RestauranteDTOAssembler restauranteDTOAssembler;
    @Autowired
    private RestauranteModelAssembler restauranteModelAssembler;
    @Autowired
    private SmartValidator smartValidator; // Variante estendida da interface do Validador, adicionando suporte para 'grupos' de validação.

    public CollectionModel<RestauranteDTO> lista(){
        List<RestauranteModel> listaRestaurantes  = restauranteRepository.findAllDistinct();
        return restauranteDTOAssembler.toCollectionModel(listaRestaurantes);
    }


    public RestauranteDTO buscaPorId(Long id){
        RestauranteModel restauranteModel = findRestauranteModel(id);

        RestauranteDTO restauranteDTO = restauranteDTOAssembler.convertToRestauranteDTO(restauranteModel);
        restauranteDTO.setDataCadastro(restauranteModel.getDataCadastro());
        restauranteDTO.setDataAtualizacao(restauranteModel.getDataAtualizacao());
        return restauranteDTO;
    }


    @Transactional // Se der tudo certo e não lançar nenhuma exception na transação, dá um commit no banco, senão dá rollback para manter a consistência no banco
    public RestauranteDTO salva(RestauranteInput restauranteInput){
        RestauranteModel restauranteModel = new RestauranteModel();
        restauranteModelAssembler.convertToRestauranteModel(restauranteInput, restauranteModel);

        restauranteModel = restauranteRepository.save(restauranteModel);

        RestauranteDTO restauranteDTO = restauranteDTOAssembler.convertToRestauranteDTO(restauranteModel);
        restauranteDTO.setDataCadastro(restauranteModel.getDataCadastro());
        restauranteDTO.setDataAtualizacao(restauranteModel.getDataAtualizacao());
        return restauranteDTO;
    }


    @Transactional // Se der tudo certo e não lançar nenhuma exception na transação, dá um commit no banco, senão dá rollback para manter a consistência no banco
    public RestauranteDTO altera(Long id, RestauranteInput restauranteInput){
        RestauranteModel restauranteModel = findRestauranteModel(id);

        restauranteModelAssembler.convertToRestauranteModel(restauranteInput, restauranteModel);

        restauranteRepository.save(restauranteModel);
        restauranteRepository.flush(); // Libera todas as alterações pendentes no banco de dados e sincroniza as alterações com o banco de dados

        RestauranteDTO restauranteDTO = restauranteDTOAssembler.convertToRestauranteDTO(restauranteModel);
        restauranteDTO.setDataCadastro(restauranteModel.getDataCadastro());
        restauranteDTO.setDataAtualizacao(restauranteModel.getDataAtualizacao());
        return restauranteDTO;
    }


    @Transactional // Se der tudo certo e não lançar nenhuma exception na transação, dá um commit no banco, senão dá rollback para manter a consistência no banco
    public RestauranteDTO alteraParcial(Long id, Map<String, Object> campos, HttpServletRequest request){
        RestauranteModel restauranteModel = findRestauranteModel(id);

        merge(campos, restauranteModel, request);
        validate(restauranteModel, "restauranteModel");

        restauranteRepository.save(restauranteModel);
        restauranteRepository.flush(); // Libera todas as alterações pendentes no banco de dados e sincroniza as alterações com o banco de dados

        RestauranteDTO restauranteDTO = restauranteDTOAssembler.convertToRestauranteDTO(restauranteModel);
        restauranteDTO.setDataCadastro(restauranteModel.getDataCadastro());
        restauranteDTO.setDataAtualizacao(restauranteModel.getDataAtualizacao());
        return restauranteDTO;
    }


    @Transactional // Se der tudo certo e não lançar nenhuma exception na transação, dá um commit no banco, senão dá rollback para manter a consistência no banco
    public void deleta(Long id) {
        try {
            restauranteRepository.deleteById(id);
            restauranteRepository.flush(); // Libera todas as alterações pendentes no banco de dados e sincroniza as alterações com o banco de dados

        } catch (EmptyResultDataAccessException e) {
            log.error("ERROR :: {}", e.getMessage());
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de restaurante com código: %d", id));
        } catch (DataIntegrityViolationException e) {
            log.error("ERROR :: {}", e.getMessage());
            throw new EntidadeEmUsoException(String.format("Restaurante de código: %d não pode ser removida, pois está em uso.", id));
        }
    }


    @Transactional
    public void ativa(Long restauranteId){
        RestauranteModel restauranteModel = findRestauranteModel(restauranteId);
        restauranteModel.ativa();

        // Como o restauranteModel está em estado gerenciado pelo EntityManager, significa que qualquer alteração feita
        // na entidade será automaticamente sincronizada com o banco de dados ao final da transação,
        // sem a necessidade de chamar explicitamente um método como save().
        restauranteRepository.save(restauranteModel);
    }


    @Transactional
    public void ativa(List<Long> restauranteIds){
        restauranteIds.forEach(this::ativa);

    }


    @Transactional
    public void inativa(Long restauranteId){
        RestauranteModel restauranteModel = findRestauranteModel(restauranteId);
        restauranteModel.inativa();

        // Como o restauranteModel está em estado gerenciado pelo EntityManager, significa que qualquer alteração feita
        // na entidade será automaticamente sincronizada com o banco de dados ao final da transação,
        // sem a necessidade de chamar explicitamente um método como save().
        restauranteRepository.save(restauranteModel);
    }


    @Transactional
    public void inativa(List<Long> restauranteIds){
        restauranteIds.forEach(this::inativa);

    }


    @Transactional
    public void abertura(Long restauranteId) {
        RestauranteModel restauranteModel = findRestauranteModel(restauranteId);
        restauranteModel.abertura();

        // Obs: Como o restauranteModel está em estado gerenciado pelo EntityManager, significa que qualquer alteração feita
        // na entidade será automaticamente sincronizada com o banco de dados ao final da transação,
        // sem a necessidade de chamar explicitamente um método como save().
    }


    @Transactional
    public void fechamento(Long restauranteId) {
        RestauranteModel restauranteModel = findRestauranteModel(restauranteId);
        restauranteModel.fechamento();

        // Obs: Como o restauranteModel está em estado gerenciado pelo EntityManager, significa que qualquer alteração feita
        // na entidade será automaticamente sincronizada com o banco de dados ao final da transação,
        // sem a necessidade de chamar explicitamente um método como save().
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


    public CollectionModel<RestauranteDTO> findAllSpec(Specification<RestauranteModel> and) {
        List<RestauranteModel> restauranteModels = restauranteRepository.findAll(and);
        return restauranteDTOAssembler.toCollectionModel(restauranteModels);
    }

    public RestauranteModel findRestauranteModel(Long id) {
        return restauranteRepository.findById(id).orElseThrow(() ->
            new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de restaurante com código: %d", id)));
    }


    public List<RestauranteViewDTO> listarComJsonView(){
        List<RestauranteModel> listaRestaurantes  = restauranteRepository.findAllDistinct();

        Set<RestauranteViewDTO> restauranteDTOs = listaRestaurantes.stream()
            .map(restauranteModel -> restauranteDTOAssembler.convertToRestauranteViewDTOBuilder(restauranteModel).build())
            .collect(Collectors.toSet());

        return restauranteDTOs.stream().sorted(Comparator.comparingLong(RestauranteViewDTO::getId)).collect(Collectors.toList());
    }

}
