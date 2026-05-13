package com.prueba.TaskManagement.RestController;

import com.prueba.TaskManagement.ML.Result;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result> manejoGeneralExcepciones(Exception ex){
        Result Resultado = new Result();
        
        Resultado.correct = false;
        Resultado.ErrorMessage = "Problema con el servidor"+ ex.getLocalizedMessage();
        Resultado.ex = ex;
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Resultado);
    }
    
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Result> manejoMetodoNoPermitido(HttpRequestMethodNotSupportedException ex){
        Result Resultado = new Result();
        
        Resultado.correct = false;
        Resultado.ErrorMessage = "El método: " + ex.getMethod() + " no tiene soporte.";
        Resultado.ex = ex;
        
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(Resultado);
    
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Result> handleValidationErrors(MethodArgumentNotValidException ex){
        List<String> errores = ex.getBindingResult()
                .getFieldErrors()
                .stream().
                map(e -> e.getField() + ": "+ e.getDefaultMessage())
                .collect(Collectors.toList());
        
        Result Resultado = new Result();
        
        Resultado.correct = false;
        Resultado.ex = ex;
        Resultado.object = HttpStatus.BAD_REQUEST.value();
        Resultado.objects.addAll(errores);
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Resultado);
    }
}
