package com.josko.proyecto_bancario.exeptions;


import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.josko.proyecto_bancario.DTOs.ResponseObjectError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.lang.reflect.InvocationTargetException;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final String ERROR_400 = "400 Bad Request";
    private final String ERROR_418 = "418 I'm a teapot (RFC 2324)";
    private final String ERROR_500 = "500 Internal Server Error";

//    @ExceptionHandler(value = {InvocationTargetException.class, MismatchedInputException.class, NotValidDataException.class})
    @ExceptionHandler(value = {NotValidDataException.class})
    protected ResponseEntity<ResponseObjectError> handleNotValidData(RuntimeException exception, WebRequest webRequest) {
        log.error(ERROR_418);
        return getResponseError(HttpStatus.I_AM_A_TEAPOT, exception.getMessage());
    }

//    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class, IllegalArgumentException.class, IdNotFoundExeption.class, IdNotValidExeption.class})
    @ExceptionHandler(value = {IllegalArgumentException.class, IdNotFoundExeption.class, IdNotValidExeption.class})
    protected ResponseEntity<ResponseObjectError> handleIdNotFound(RuntimeException exception, WebRequest webRequest) {
        log.error(ERROR_400);
        return getResponseError(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(value = {NullPointerException.class})
    protected ResponseEntity<ResponseObjectError> handleNullPointer(RuntimeException exception, WebRequest webRequest) {
        log.error(ERROR_500);
        return getResponseError(HttpStatus.INTERNAL_SERVER_ERROR,  exception.getMessage());
    }

    /*
    @ExceptionHandler(value = {StatusNotValidExeption.class})
    protected ResponseEntity<ResponseObjectError> handleStatusNotValidFound(RuntimeException exception, WebRequest webRequest) {
        log.error(ERROR_400);
        return getResponseError(HttpStatus.BAD_REQUEST, exception.getMessage());
    }
    */

    private ResponseEntity<ResponseObjectError> getResponseError(HttpStatus httpStatus, String message) {

        log.info(httpStatus.toString());
        return ResponseEntity.status(httpStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ResponseObjectError.builder()
                        .statusCode(httpStatus.value())
                        .statusType(httpStatus.value() + "-" + httpStatus.getReasonPhrase())
                        .errorDescription(message)
                        .build());
    }


}
