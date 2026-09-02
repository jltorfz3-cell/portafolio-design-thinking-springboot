package com.portafolio.dt.exception;
import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestControllerAdvice public class GlobalExceptionHandler{
 @ExceptionHandler(NoSuchElementException.class) ResponseEntity<?> n(NoSuchElementException e){return ResponseEntity.status(404).body(Map.of("error",e.getMessage()));}
 @ExceptionHandler(IllegalArgumentException.class) ResponseEntity<?> b(IllegalArgumentException e){return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));}
 @ExceptionHandler(Exception.class) ResponseEntity<?> x(Exception e){return ResponseEntity.status(500).body(Map.of("error","Error interno","detail",String.valueOf(e.getMessage())));}
}
