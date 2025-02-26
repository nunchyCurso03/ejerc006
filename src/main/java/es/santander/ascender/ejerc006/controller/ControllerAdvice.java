package es.santander.ascender.ejerc006.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(exception = UnsupportedOperationException.class)
    public ResponseEntity<String> tratarUnsupportedException() {

        return ResponseEntity.internalServerError().body("Estamos en obras");
    }

}
