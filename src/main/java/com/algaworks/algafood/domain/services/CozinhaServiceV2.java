package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.DTOs.CozinhaDTOV2;
import com.algaworks.algafood.api.assemblers.DTOs.CozinhaDTOAssemblerV2;
import com.algaworks.algafood.api.assemblers.models.CozinhaModelAssemblerV2;
import com.algaworks.algafood.api.inputs.CozinhaInputV2;
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
public class CozinhaServiceV2 {

    @Autowired
    private CozinhaRepository cozinhaRepository;
    @Autowired
    private CozinhaModelAssemblerV2 cozinhaModelAssemblerV2;
    @Autowired
    private CozinhaDTOAssemblerV2 cozinhaDTOAssemblerV2;
    @Autowired
    private PagedResourcesAssembler<CozinhaModel> pagedAssembler;


    @Transactional(readOnly = true)
    public CollectionModel<CozinhaDTOV2> lista(){
        List<CozinhaModel> listaCozinhas  = cozinhaRepository.findAll();
        return cozinhaDTOAssemblerV2.toCollectionModel(listaCozinhas);
    }


    @Transactional(readOnly = true)
    public Page<CozinhaDTOV2> listaPaginada(Pageable pageable){
        Page<CozinhaModel> listasPage  = cozinhaRepository.findAll(pageable);
        return cozinhaDTOAssemblerV2.convertToCozinhaDTOPage(listasPage);
    }


//  Obs: como está retornando um PagedModel da biblioteca do hateoas, a nossa classe PageJsonSerializer não está
//   sendo usada para customizar os campos da paginação porque o Page que ela implementa é da biblioteca do Spring Data
    @Transactional(readOnly = true)
    public PagedModel<CozinhaDTOV2> listaPaginadaComLinks(Pageable pageable){
        Page<CozinhaModel> listasPage  = cozinhaRepository.findAll(pageable);

//      Converte Page<?> do Spring Data para o PagedModel<?> do hateoas é já adiciona link próprio para cada ID fornecido do objeto cozinha.
        return pagedAssembler.toModel(listasPage, cozinhaDTOAssemblerV2);

//      Obs: Do jeito que está ele preenche a 'collection' com a URI da lista e não o da própria paginação do objeto cozinhaDTO,
//      se caso precisar mudar, usar a implementação feita para o método listaPaginada(Pageable pageable) que está acima.
    }


    @Transactional(readOnly = true)
    public CozinhaDTOV2 buscaPorId(Long id){
        CozinhaModel cozinhaModel = findCozinhaModelById(id);
        return cozinhaDTOAssemblerV2.convertToCozinhaDTO(cozinhaModel);
    }


    @Transactional(readOnly = true)
    public CollectionModel<CozinhaDTOV2> consultaPorNome(String nome) {
        List<CozinhaModel> listaConsultaPorNome = cozinhaRepository.consultaPorNome(nome);
        return  cozinhaDTOAssemblerV2.toCollectionModel(listaConsultaPorNome);
    }


    @Transactional // Se der tudo certo e não lançar nenhuma exception na transação, dá um commit no banco, senão dá rollback para manter a consistência no banco
    public CozinhaDTOV2 salvar(CozinhaInputV2 cozinhaInput){
        CozinhaModel cozinhaModel = new CozinhaModel();
        cozinhaModelAssemblerV2.convertToCozinhaModel(cozinhaInput, cozinhaModel);

        cozinhaModel = cozinhaRepository.save(cozinhaModel);
        return cozinhaDTOAssemblerV2.convertToCozinhaDTO(cozinhaModel);
    }


    @Transactional // Se der tudo certo e não lançar nenhuma exception na transação, dá um commit no banco, senão dá rollback para manter a consistência no banco
    public CozinhaDTOV2 alterar(Long id, CozinhaInputV2 cozinhaInput){
        CozinhaModel cozinhaModel = findCozinhaModelById(id);
        cozinhaModelAssemblerV2.convertToCozinhaModel(cozinhaInput, cozinhaModel);

        cozinhaModel = cozinhaRepository.save(cozinhaModel);
        return cozinhaDTOAssemblerV2.convertToCozinhaDTO(cozinhaModel);
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
