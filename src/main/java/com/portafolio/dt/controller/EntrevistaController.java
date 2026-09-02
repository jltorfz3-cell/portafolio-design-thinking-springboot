package com.portafolio.dt.controller;
import com.portafolio.dt.model.Entrevista; import com.portafolio.dt.repository.EntrevistaRepository; import org.springframework.http.*; import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize; import java.util.*;
@RestController @RequestMapping("/api/entrevistas") @PreAuthorize("hasAnyRole('ADMIN','DOCENTE','ESTUDIANTE','COORDINADOR')")
public class EntrevistaController{
 private final EntrevistaRepository repo; public EntrevistaController(EntrevistaRepository r){repo=r;}
 @GetMapping public List<Entrevista> all(){return repo.findAll();} 
 @GetMapping("/{id}") public Entrevista one(@PathVariable Long id){return repo.findById(id).orElseThrow();}
 @PostMapping public ResponseEntity<Entrevista> create(@RequestBody Entrevista x){return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(x));}
 @PutMapping("/{id}") public Entrevista update(@PathVariable Long id,@RequestBody Entrevista x){if(!repo.existsById(id))throw new NoSuchElementException("No existe");x.setId(id);return repo.save(x);}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){if(!repo.existsById(id))return ResponseEntity.notFound().build();repo.deleteById(id);return ResponseEntity.noContent().build();}
}
