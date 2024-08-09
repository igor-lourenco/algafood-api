package com.algaworks.algafood.api.controllers.hadlers;

import com.algaworks.algafood.api.controllers.exceptions.StandardError;
import com.algaworks.algafood.api.controllers.exceptions.enums.ErrorTypeEnum;
import com.algaworks.algafood.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e, WebRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorTypeEnum errorType = ErrorTypeEnum.ENTITY_NOT_FOUND;

        StandardError error = createStandardErrorBuilder(status, errorType, e.getMessage()).build();
        return handleExceptionInternal(e, error, new HttpHeaders(), status,  request);

    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handlerEntidadeEmUsoException(EntidadeEmUsoException e, WebRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorTypeEnum errorType = ErrorTypeEnum.ENTITY_IN_USE;

        StandardError error = createStandardErrorBuilder(status, errorType, e.getMessage()).build();
        return handleExceptionInternal(e, error, new HttpHeaders(), status, request);

    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorTypeEnum errorType = ErrorTypeEnum.JSON_INVALID;
        String mensagem = "O corpo da requisicao esta invalido. Verifique erro de sintaxe";

        StandardError error = createStandardErrorBuilder(status, errorType, mensagem).build();
        return super.handleExceptionInternal(ex, error, headers, status, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleErrorException(Exception e, WebRequest request){

        e.printStackTrace();
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST,  request);
    }

    @Override // sobrescreve o método para retornar nosso body de resposta padrão
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (body == null) {
            body = StandardError.builder().timestamp(LocalDateTime.now()).status(status.value()).title(ex.getMessage()).build();
        } else if (body instanceof String) {
            body = StandardError.builder().timestamp(LocalDateTime.now()).status(status.value()).title((String) body).build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private StandardError.StandardErrorBuilder createStandardErrorBuilder(HttpStatus status, ErrorTypeEnum errorType, String detail) {

        return StandardError.builder()
                .timestamp(LocalDateTime.now())
                .type(errorType.getUri())
                .title(errorType.getTitle())
                .detail(detail);
    }
}
