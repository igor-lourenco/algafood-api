package com.algaworks.algafood.api.controllers.hadlers;

import com.algaworks.algafood.api.controllers.exceptions.StandardError;
import com.algaworks.algafood.api.controllers.exceptions.enums.ErrorType;
import com.algaworks.algafood.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {


    @Override // sobrescreve o método para retornar nosso body de resposta padrão
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if(body == null) {
            body = StandardError.builder().timestamp(LocalDateTime.now()).status(status.value()).title(ex.getMessage()).build();
        }else if(body instanceof String){
            body = StandardError.builder().timestamp(LocalDateTime.now()).status(status.value()).title((String)body).build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handlerEntidadeEmUsoException(EntidadeEmUsoException e, WebRequest request){

        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e, WebRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorType errorType = ErrorType.ENTIDADE_NAO_ENCONTRADA;

        StandardError error = createStandardErrorBuilder(status, errorType, e.getMessage()).build();

        return handleExceptionInternal(e, error, new HttpHeaders(), status,  request);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleErrorException(Exception e, WebRequest request){

        e.printStackTrace();

        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST,  request);
    }

    private StandardError.StandardErrorBuilder createStandardErrorBuilder(HttpStatus status, ErrorType errorType, String detail) {

        return StandardError.builder()
                .timestamp(LocalDateTime.now())
                .type(errorType.getUri())
                .title(errorType.getTitle())
                .detail(detail);
    }
}
