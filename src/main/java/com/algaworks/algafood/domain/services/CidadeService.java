package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.domain.exceptions.EntidadeComIdException;
import com.algaworks.algafood.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.models.CidadeModel;
import com.algaworks.algafood.domain.models.CozinhaModel;
import com.algaworks.algafood.domain.models.EstadoModel;
import com.algaworks.algafood.domain.repositories.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoService estadoService;

    public List<CidadeModel> listar(){
        List<CidadeModel> listaCozinhas  = cidadeRepository.findAll();
        return listaCozinhas;
    }

    public CidadeModel buscaPorId(Long id){
        Optional<CidadeModel> cozinhaOptional = cidadeRepository.findById(id);

        if(cozinhaOptional.isEmpty()){
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de cidade com código: %d", id));
        }

        return cozinhaOptional.get();
    }

//    public List<CidadeModel> consultaPorNome(String nome) {
//        List<CidadeModel> listaConsultaPorNome = cidadeRepository.consultaPorNome(nome);
//        return  listaConsultaPorNome;
//    }


    @Transactional // Se der tudo certo e não lançar nenhuma exception na transação, dá um commit no banco, senão dá rollback para manter a consistência no banco
    public CidadeModel salvar(CidadeModel cozinha){
        EstadoModel estadoModel = estadoService.buscaPorId(cozinha.getEstado().getId());

        cozinha.setEstado(estadoModel);
        cozinha = cidadeRepository.save(cozinha);
        return cozinha;
    }


    @Transactional // Se der tudo certo e não lançar nenhuma exception na transação, dá um commit no banco, senão dá rollback para manter a consistência no banco
    public CidadeModel alterar(Long id, CidadeModel cidade){
        CidadeModel cidadeModel = buscaPorId(id);
        EstadoModel estadoModel = estadoService.buscaPorId(cidade.getEstado().getId());

        if(cidade.getNome() == null || cidade.getNome().trim().isEmpty()){
            throw new EntidadeComIdException("O campo 'nome' não pode estar vazio. Preencha e tente novamente.");
        }

        cidadeModel.setNome(cidade.getNome());
        cidadeModel.setEstado(estadoModel);
        cidadeModel = cidadeRepository.save(cidadeModel);
        return cidadeModel;
    }


//    @Transactional // Se der tudo certo e não lançar nenhuma exception na transação, dá um commit no banco, senão dá rollback para manter a consistência no banco
//    public void deletar(Long id) {
//        try {
//            cidadeRepository.deleteById(id);
//
//        } catch (EmptyResultDataAccessException e) {
//            System.out.println("ERROR: " + e.getMessage());
//            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de cidade com código: %d", id));
//        } catch (DataIntegrityViolationException e) {
//            System.out.println("ERROR: " + e.getMessage());
//            throw new EntidadeEmUsoException(String.format("Cidade de código: %d não pode ser removida, pois está em uso.", id));
//        }
//    }


}
