package com.ibm.academia.ruletaapi.exceptions.handler;

import com.ibm.academia.ruletaapi.exceptions.NotFoundException;
import com.ibm.academia.ruletaapi.exceptions.RuletaNoEncontrada;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(value = RuletaNoEncontrada.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,Object> ruletaNoEncontrada(RuletaNoEncontrada ex){
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return response;
    }

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,Object> notFoundException(RuletaNoEncontrada ex){
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return response;
    }
}
