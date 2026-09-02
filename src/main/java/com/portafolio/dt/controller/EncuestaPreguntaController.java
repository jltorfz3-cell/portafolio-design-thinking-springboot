package com.portafolio.dt.controller;
import com.portafolio.dt.model.EncuestaPregunta; import com.portafolio.dt.repository.EncuestaPreguntaRepository; import org.springframework.http.*; import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize; import java.util.*;
@RestController @RequestMapping("/api/encuesta-preguntas") @PreAuthorize("hasAnyRole('ADMIN','DOCENTE','ESTUDIANTE','COORDINADOR')")
public class EncuestaPreguntaController{
 private final EncuestaPreguntaRepository repo; public EncuestaPreguntaController(EncuestaPreguntaRepository r){repo=r;}
 @GetMapping public List<EncuestaPregunta> all(){return repo.findAll();} 
 @GetMapping("/{id}") public EncuestaPregunta one(@PathVariable Long id){return repo.findById(id).orElseThrow();}
 @PostMapping public ResponseEntity<EncuestaPregunta> create(@RequestBody EncuestaPregunta x){return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(x));}
 @PutMapping("/{id}") public EncuestaPregunta update(@PathVariable Long id,@RequestBody EncuestaPregunta x){if(!repo.existsById(id))throw new NoSuchElementException("No existe");x.setId(id);return repo.save(x);}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){if(!repo.existsById(id))return ResponseEntity.notFound().build();repo.deleteById(id);return ResponseEntity.noContent().build();}
}
