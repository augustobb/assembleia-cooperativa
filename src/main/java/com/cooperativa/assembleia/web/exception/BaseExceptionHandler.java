package com.cooperativa.assembleia.web.exception;


import com.cooperativa.assembleia.web.message.MessageHandler;
import com.cooperativa.assembleia.web.message.MessageKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@ControllerAdvice
public class BaseExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageHandler messageHandler;

    @Autowired
    public BaseExceptionHandler(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        String message = messageHandler.getMessage(MessageKey.PARAMETROS_INVALIDOS);
        List<String> details = exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(messageHandler::getMessage)
                .collect(Collectors.toList());
        log.error(message);
        log.error(details.toString());
        return new ResponseEntity<>(new ErrorDetails(message, details), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidParameterException.class)
    public final ResponseEntity<Object> handleInvalidParameterException(InvalidParameterException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(new ErrorDetails(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(new ErrorDetails(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    public final ResponseEntity<Object> handleBusinessException(BusinessException exception) {
        String message = messageHandler.getMessage(exception.getMessageKey(), exception.getArgs());
        log.error(message);
        return new ResponseEntity<>(new ErrorDetails(message), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception exception) {
        logger.error(exception.getMessage(), exception);
        String message = messageHandler.getMessage(MessageKey.ERRO_NAO_ESPERADO);
        log.error(exception.getMessage());
        return new ResponseEntity<>(new ErrorDetails(message, Collections.singletonList(exception.getMessage())),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}