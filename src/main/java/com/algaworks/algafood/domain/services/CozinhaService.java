package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.DTOs.CozinhaDTO;
import com.algaworks.algafood.api.assemblers.models.CozinhaModelAssembler;
import com.algaworks.algafood.api.assemblers.DTOs.CozinhaDTOAssembler;
import com.algaworks.algafood.api.inputs.CozinhaInput;
import com.algaworks.algafood.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.models.CozinhaModel;
import com.algaworks.algafood.domain.repositories.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;
    @Autowired
    private CozinhaModelAssembler cozinhaModelAssembler;
    @Autowired
    private CozinhaDTOAssembler cozinhaDTOAssembler;
    @Autowired
    private PagedResourcesAssembler<CozinhaModel> pagedAssembler;


    @Transactional(readOnly = true)
    public CollectionModel<CozinhaDTO> lista(){
        List<CozinhaModel> listaCozinhas  = cozinhaRepository.findAll();
        return cozinhaDTOAssembler.toCollectionModel(listaCozinhas);
    }


    @Transactional(readOnly = true)
    public Page<CozinhaDTO> listaPaginada(Pageable pageable){
        Page<CozinhaModel> listasPage  = cozinhaRepository.findAll(pageable);
        return cozinhaDTOAssembler.convertToCozinhaDTOPage(listasPage);
    }


//  Obs: como está retornando um PagedModel da biblioteca do hateoas, a nossa classe PageJsonSerializer não está
//   sendo usada para customizar os campos da paginação porque o Page que ela implementa é da biblioteca do Spring Data
    @Transactional(readOnly = true)
    public PagedModel<CozinhaDTO> listaPaginadaComLinks(Pageable pageable){
        Page<CozinhaModel> listasPage  = cozinhaRepository.findAll(pageable);

//      Converte Page<?> do Spring Data para o PagedModel<?> do hateoas é já adiciona link próprio para cada ID fornecido do objeto cozinha.
        return pagedAssembler.toModel(listasPage, cozinhaDTOAssembler);

//      Obs: Do jeito que está ele preenche a 'collection' com a URI da lista e não o da própria paginação do objeto cozinhaDTO,
//      se caso precisar mudar, usar a implementação feita para o método listaPaginada(Pageable pageable) que está acima.
    }


    @Transactional(readOnly = true)
    public CozinhaDTO buscaPorId(Long id){
        CozinhaModel cozinhaModel = findCozinhaModelById(id);
        return cozinhaDTOAssembler.convertToCozinhaDTO(cozinhaModel);
    }


    @Transactional(readOnly = true)
    public CollectionModel<CozinhaDTO> consultaPorNome(String nome) {
        List<CozinhaModel> listaConsultaPorNome = cozinhaRepository.consultaPorNome(nome);
        return  cozinhaDTOAssembler.toCollectionModel(listaConsultaPorNome);
    }


    @Transactional // Se der tudo certo e não lançar nenhuma exception na transação, dá um commit no banco, senão dá rollback para manter a consistência no banco
    public CozinhaDTO salvar(CozinhaInput cozinhaInput){
        CozinhaModel cozinhaModel = new CozinhaModel();
        cozinhaModelAssembler.convertToCozinhaModel(cozinhaInput, cozinhaModel);

        cozinhaModel = cozinhaRepository.save(cozinhaModel);
        return cozinhaDTOAssembler.convertToCozinhaDTO(cozinhaModel);
    }


    @Transactional // Se der tudo certo e não lançar nenhuma exception na transação, dá um commit no banco, senão dá rollback para manter a consistência no banco
    public CozinhaDTO alterar(Long id, CozinhaInput cozinhaInput){
        CozinhaModel cozinhaModel = findCozinhaModelById(id);
        cozinhaModelAssembler.convertToCozinhaModel(cozinhaInput, cozinhaModel);

        cozinhaModel = cozinhaRepository.save(cozinhaModel);
        return cozinhaDTOAssembler.convertToCozinhaDTO(cozinhaModel);
    }


    @Transactional // Se der tudo certo e não lançar nenhuma exception na transação, dá um commit no banco, senão dá rollback para manter a consistência no banco
    public void deletar(Long id) {
        try {
            cozinhaRepository.deleteById(id);
            cozinhaRepository.flush(); // Libera todas as alterações pendentes no banco de dados e sincroniza as alterações com o banco de dados

        } catch (EmptyResultDataAccessException e) {
            System.out.println("ERROR: " + e.getMessage());
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de cozinha com código: %d", id));
        } catch (DataIntegrityViolationException e) {
            System.out.println("ERROR: " + e.getMessage());
            throw new EntidadeEmUsoException(String.format("Cozinha de código: %d não pode ser removida, pois está em uso.", id));
        }
    }


    private CozinhaModel findCozinhaModelById(Long id) {
        return cozinhaRepository.findById(id).orElseThrow(() ->
            new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de Cozinha com id: %d", id)));
    }
}
