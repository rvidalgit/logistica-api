package com.rodrigovidal.logisticaapi.exceptionHandler;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ValidationErrorResponse.Campo> campos = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String nome = ((FieldError) error).getField();

            String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            //campos.add(new ValidationErrorResponse.Campo(nome, error.getDefaultMessage()));
            campos.add(new ValidationErrorResponse.Campo(nome, mensagem));
        }

        ValidationErrorResponse response = new ValidationErrorResponse();
        response.setStatus(status.value());
        response.setData(LocalDateTime.now());
        response.setTitulo(ex.getMessage());
        response.setCampos(campos);

        return ResponseEntity.badRequest().body(response);
    }
}
