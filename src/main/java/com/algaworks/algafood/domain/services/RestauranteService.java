package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.DTOs.CozinhaDTO;
import com.algaworks.algafood.api.DTOs.RestauranteDTO;
import com.algaworks.algafood.api.inputs.CozinhaIdInput;
import com.algaworks.algafood.api.inputs.RestauranteInput;
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
import java.util.stream.Collectors;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;
//    @Autowired
//    private CozinhaService cozinhaService;
    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private SmartValidator smartValidator; // Variante estendida da interface do Validador, adicionando suporte para 'grupos' de validação.


    public List<RestauranteDTO> listar(){
        List<RestauranteModel> listaRestaurantes  = restauranteRepository.findAll();

        List<RestauranteDTO> restauranteDTOs = listaRestaurantes.stream()
            .map(r -> convertToRestauranteDTO(r).build())
            .collect(Collectors.toList());

        return restauranteDTOs;
    }


    public RestauranteDTO buscaPorId(Long id){
        Optional<RestauranteModel> restauranteOptional = restauranteRepository.findById(id);

        if(restauranteOptional.isEmpty()){
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de restaurante com código: %d", id));
        }

        RestauranteDTO restauranteDTO = convertToRestauranteDTO(restauranteOptional.get())
            .dataCadastro(restauranteOptional.get().getDataCadastro())
            .dataAtualizacao(restauranteOptional.get().getDataAtualizacao())
            .build();

        return restauranteDTO;
    }


    @Transactional // Se der tudo certo e não lançar nenhuma exception na transação, dá um commit no banco, senão dá rollback para manter a consistência no banco
    public RestauranteDTO salvar(RestauranteInput restauranteInput){
        RestauranteModel restauranteModel = convertToRestauranteModel(restauranteInput);
        restauranteModel = restauranteRepository.save(restauranteModel);

        RestauranteDTO restauranteDTO = convertToRestauranteDTO(restauranteModel)
            .dataCadastro(restauranteModel.getDataCadastro())
            .dataAtualizacao(restauranteModel.getDataAtualizacao())
            .build();

        return restauranteDTO;
    }


//    @Transactional // Se der tudo certo e não lançar nenhuma exception na transação, dá um commit no banco, senão dá rollback para manter a consistência no banco
//    public RestauranteDTO alterar(Long id, RestauranteModel restauranteModel){
//        RestauranteModel restaurante = restauranteRepository.findById(id).orElseThrow(() ->
//            new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de restaurante com código: %d", id)));
//
//        CozinhaModel cozinhaModel = cozinhaService.buscaPorId(restauranteModel.getCozinha().getId());
//
//        restaurante.setNome(restauranteModel.getNome());
//        restaurante.setTaxaFrete(restauranteModel.getTaxaFrete());
//        restaurante.setCozinha(cozinhaModel);
//        restauranteRepository.save(restaurante);
//        restauranteRepository.flush(); // Libera todas as alterações pendentes no banco de dados e sincroniza as alterações com o banco de dados
//
//        RestauranteDTO restauranteDTO = convertToRestauranteDTO(restaurante)
//            .dataCadastro(restaurante.getDataCadastro())
//            .dataAtualizacao(restaurante.getDataAtualizacao())
//            .build();
//
//        return restauranteDTO;
//    }


    @Transactional // Se der tudo certo e não lançar nenhuma exception na transação, dá um commit no banco, senão dá rollback para manter a consistência no banco
    public RestauranteDTO alterarParcial(Long id,  Map<String, Object> campos, HttpServletRequest request){
        RestauranteModel restaurante = restauranteRepository.findById(id).orElseThrow(() ->
            new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de restaurante com código: %d", id)));

        merge(campos, restaurante, request);
        validate(restaurante, "restauranteModel");

        restauranteRepository.save(restaurante);
        restauranteRepository.flush(); // Libera todas as alterações pendentes no banco de dados e sincroniza as alterações com o banco de dados

        RestauranteDTO restauranteDTO = convertToRestauranteDTO(restaurante)
            .dataCadastro(restaurante.getDataCadastro())
            .dataAtualizacao(restaurante.getDataAtualizacao())
            .build();

        return restauranteDTO;
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


    public List<RestauranteDTO> findAllSpec(Specification<RestauranteModel> and) {
        List<RestauranteModel> restauranteModels = restauranteRepository.findAll(and);
        List<RestauranteDTO> restauranteDTOs = restauranteModels.stream()
            .map(r -> convertToRestauranteDTO(r).build())
            .collect(Collectors.toList());

        return restauranteDTOs;
    }


    private RestauranteDTO.RestauranteDTOBuilder convertToRestauranteDTO(RestauranteModel restauranteModel) {
        RestauranteDTO.RestauranteDTOBuilder restauranteDTOBuilder = RestauranteDTO.builder();
        restauranteDTOBuilder.id(restauranteModel.getId());
        restauranteDTOBuilder.nome(restauranteModel.getNome());
        restauranteDTOBuilder.taxaFrete(restauranteModel.getTaxaFrete());

        CozinhaDTO cozinhaDTO = new CozinhaDTO();
        cozinhaDTO.setId(restauranteModel.getCozinha().getId());
        cozinhaDTO.setNome(restauranteModel.getCozinha().getNome());

        restauranteDTOBuilder.cozinha(cozinhaDTO);
        return restauranteDTOBuilder;
    }


    private RestauranteModel convertToRestauranteModel(RestauranteInput restauranteInput){
        RestauranteModel restauranteModel = new RestauranteModel();
        restauranteModel.setNome(restauranteInput.getNome());
        restauranteModel.setTaxaFrete(restauranteInput.getTaxaFrete());

        CozinhaModel cozinhaModel = cozinhaRepository.findById(restauranteInput.getCozinha().getId()).orElseThrow(() ->
            new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de cozinha com código: %d", restauranteInput.getCozinha().getId())));

        restauranteModel.setCozinha(cozinhaModel);

        return restauranteModel;

    }
}
