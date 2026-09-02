package com.portafolio.dt.controller;
import com.portafolio.dt.model.Encuesta; import com.portafolio.dt.repository.EncuestaRepository; import org.springframework.http.*; import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize; import java.util.*;
@RestController @RequestMapping("/api/encuestas") @PreAuthorize("hasAnyRole('ADMIN','DOCENTE','ESTUDIANTE','COORDINADOR')")
public class EncuestaController{
 private final EncuestaRepository repo; public EncuestaController(EncuestaRepository r){repo=r;}
 @GetMapping public List<Encuesta> all(){return repo.findAll();} 
 @GetMapping("/{id}") public Encuesta one(@PathVariable Long id){return repo.findById(id).orElseThrow();}
 @PostMapping public ResponseEntity<Encuesta> create(@RequestBody Encuesta x){return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(x));}
 @PutMapping("/{id}") public Encuesta update(@PathVariable Long id,@RequestBody Encuesta x){if(!repo.existsById(id))throw new NoSuchElementException("No existe");x.setId(id);return repo.save(x);}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){if(!repo.existsById(id))return ResponseEntity.notFound().build();repo.deleteById(id);return ResponseEntity.noContent().build();}
}
