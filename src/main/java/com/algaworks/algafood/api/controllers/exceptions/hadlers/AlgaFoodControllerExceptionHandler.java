package com.algaworks.algafood.api.controllers.exceptions.hadlers;

import com.algaworks.algafood.api.controllers.exceptions.StandardError;
import com.algaworks.algafood.api.controllers.exceptions.enums.ErrorTypeEnum;
import com.algaworks.algafood.domain.exceptions.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@ControllerAdvice
public class AlgaFoodControllerExceptionHandler extends ControllerExceptionHandler {

    @Autowired
    private MessageSource messageSource; // Interface estratégica para resolução de mensagens, com suporte à parametrização e internacionalização de tais mensagens.


    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e, WebRequest request){
        log.error("ERROR :: [handleEntidadeNaoEncontradaException]");
        log.error("EXCEPTION :: {}, MENSAGEM :: {}", e.getClass().getSimpleName(), e.getMessage());

        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorTypeEnum errorType = ErrorTypeEnum.RESOURCE_NOT_FOUND;

        StandardError error = createStandardErrorBuilder(status, errorType, e.getMessage()).build();
        return handleExceptionInternal(e, error, new HttpHeaders(), status,  request);

    }


    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handlerEntidadeEmUsoException(EntidadeEmUsoException e, WebRequest request){
        log.error("ERROR :: [handlerEntidadeEmUsoException]");
        log.error("EXCEPTION :: {}, MENSAGEM :: {}", e.getClass().getSimpleName(), e.getMessage());

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorTypeEnum errorType = ErrorTypeEnum.ENTITY_IN_USE;

        StandardError error = createStandardErrorBuilder(status, errorType, e.getMessage()).build();
        return handleExceptionInternal(e, error, new HttpHeaders(), status, request);

    }


    @ExceptionHandler(StorageException.class)
    public ResponseEntity<?> handlerEntidadeEmUsoException(StorageException e, WebRequest request){
        log.error("ERROR :: [handlerEntidadeEmUsoException]");
        log.error("EXCEPTION :: {}, MENSAGEM :: {}", e.getClass().getSimpleName(), e.getMessage());

        HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;
        ErrorTypeEnum errorType = ErrorTypeEnum.INTERNAL_ERROR;

        StandardError error = createStandardErrorBuilder(status, errorType, e.getMessage()).build();
        return handleExceptionInternal(e, error, new HttpHeaders(), status, request);

    }


    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<?> handlerValidacaoException(ValidacaoException ex, WebRequest request) {
        log.error("ERROR :: [handlerValidacaoException]");
        log.error("EXCEPTION :: {}, MENSAGEM :: {}", ex.getClass().getSimpleName(), ex.getMessage());

        ErrorTypeEnum errorType = ErrorTypeEnum.DATAS_INVALID;
        HttpStatus status = HttpStatus.BAD_REQUEST;

        String mensagem = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";

        BindingResult bindingResult = ex.getBindingResult();

        List<StandardError.Object> objectsErrors = bindingResult.getAllErrors() // Adiciona as propriedades com as constraints violadas
            .stream()
            .map(objectError -> {

                // vai ler o arquivo messages.properties para paga as mensagens que estão mapeadas com os erros de cada campo do das classes Model
                String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());

                String name = String.valueOf( // objectError.getObjectName() -> Pega o nome da classe que deu o erro
                    Character.toUpperCase(objectError.getObjectName().charAt(0)) // Pega a primera letra e converte pra maiúscula
                ).concat(
                    // Tira a palavra Model e concatena a palavra novamente sem a primeira letra
                    objectError.getObjectName().replace("Model", "").substring(1));

                if (objectError instanceof FieldError) {
                    name = ((FieldError) objectError).getField(); // Se for um FieldError, pega o nome do campo que deu o erro de validação
                }

                return StandardError.Object.builder()
                    .name(name)
                    .userMessage(message)
                    .build();
            })
            .collect(Collectors.toList());

        StandardError error = createStandardErrorBuilder(status, errorType, mensagem)
            .objects(objectsErrors)
            .build();

        return handleExceptionInternal(ex, error, null, status, request);
    }

    @ExceptionHandler(EntidadeComIdException.class)
    public ResponseEntity<?> handlerEntidadeComIdException(EntidadeComIdException e, WebRequest request){
        log.error("ERROR :: [handlerEntidadeComIdException]");
        log.error("EXCEPTION :: {}, MENSAGEM :: {}", e.getClass().getSimpleName(), e.getMessage());

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorTypeEnum errorType = ErrorTypeEnum.JSON_INVALID;

        StandardError error = createStandardErrorBuilder(status, errorType, e.getMessage()).build();
        return handleExceptionInternal(e, error, new HttpHeaders(), status, request);

    }


    @ExceptionHandler(SenhaInvalidaException.class)
    public ResponseEntity<?> handlerSenhaInvalidaException(SenhaInvalidaException e, WebRequest request){
        log.error("ERROR :: [handlerSenhaInvalidaException]");
        log.error("EXCEPTION :: {}, MENSAGEM :: {}", e.getClass().getSimpleName(), e.getMessage());

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorTypeEnum errorType = ErrorTypeEnum.JSON_INVALID;

        StandardError error = createStandardErrorBuilder(status, errorType, e.getMessage()).build();
        return handleExceptionInternal(e, error, new HttpHeaders(), status, request);

    }


    @ExceptionHandler(UsuarioExistenteException.class)
    public ResponseEntity<?> handlerUsuarioExistenteException(UsuarioExistenteException e, WebRequest request){
        log.error("ERROR :: [handlerUsuarioExistenteException]");
        log.error("EXCEPTION :: {}, MENSAGEM :: {}", e.getClass().getSimpleName(), e.getMessage());

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorTypeEnum errorType = ErrorTypeEnum.JSON_INVALID;

        StandardError error = createStandardErrorBuilder(status, errorType, e.getMessage()).build();
        return handleExceptionInternal(e, error, new HttpHeaders(), status, request);

    }


    @ExceptionHandler(StatusException.class)
    public ResponseEntity<?> handlerStatusException(StatusException e, WebRequest request){
        log.error("ERROR :: [handlerStatusException]");
        log.error("EXCEPTION :: {}, MENSAGEM :: {}", e.getClass().getSimpleName(), e.getMessage());

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorTypeEnum errorType = ErrorTypeEnum.PARAMETER_INVALID;

        StandardError error = createStandardErrorBuilder(status, errorType, e.getMessage()).build();
        return handleExceptionInternal(e, error, new HttpHeaders(), status, request);

    }


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handlerAccessDeniedException(AccessDeniedException e, WebRequest request){
        log.error("ERROR :: [handlerAccessDeniedException]");
        log.error("EXCEPTION :: {}, MENSAGEM :: {}", e.getClass().getSimpleName(), e.getMessage());

        HttpStatus status = HttpStatus.FORBIDDEN;
        ErrorTypeEnum errorType = ErrorTypeEnum.ACCESS_DENIED;

        String detail = "Você não tem permissão para executar essa operação.";

        StandardError error = createStandardErrorBuilder(status, errorType, detail).build();
        return handleExceptionInternal(e, error, new HttpHeaders(), status, request);

    }


    @ExceptionHandler(FiltroException.class)
    public ResponseEntity<?> handlerFiltroException(FiltroException e, WebRequest request){
        log.error("ERROR :: [handlerFiltroException]");
        log.error("EXCEPTION :: {}, MENSAGEM :: {}", e.getClass().getSimpleName(), e.getMessage());

        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ErrorTypeEnum errorType = ErrorTypeEnum.FILTER_INVALID;

        StandardError error = createStandardErrorBuilder(status, errorType, e.getMessage()).build();
        return handleExceptionInternal(e, error, new HttpHeaders(), status, request);

    }


    @ExceptionHandler(UsuarioNaoAutenticadoException.class)
    public ResponseEntity<?> handlerUsuarioNaoAutenticadoException(UsuarioNaoAutenticadoException e, WebRequest request){
        log.error("ERROR :: [handlerUsuarioNaoAutenticadoException]");
        log.error("EXCEPTION :: {}, MENSAGEM :: {}", e.getClass().getSimpleName(), e.getMessage());

        HttpStatus status = HttpStatus.FORBIDDEN;
        ErrorTypeEnum errorType = ErrorTypeEnum.ACCESS_DENIED;

        StandardError error = createStandardErrorBuilder(status, errorType, e.getMessage()).build();
        return handleExceptionInternal(e, error, new HttpHeaders(), status, request);

    }
}
