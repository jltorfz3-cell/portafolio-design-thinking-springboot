package com.portafolio.dt.controller;
import com.portafolio.dt.model.Evaluacion; import com.portafolio.dt.repository.EvaluacionRepository; import org.springframework.http.*; import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize; import java.util.*;
@RestController @RequestMapping("/api/evaluaciones") @PreAuthorize("hasAnyRole('ADMIN','DOCENTE','COORDINADOR')")
public class EvaluacionController{
 private final EvaluacionRepository repo; public EvaluacionController(EvaluacionRepository r){repo=r;}
 @GetMapping public List<Evaluacion> all(){return repo.findAll();} 
 @GetMapping("/{id}") public Evaluacion one(@PathVariable Long id){return repo.findById(id).orElseThrow();}
 @PostMapping public ResponseEntity<Evaluacion> create(@RequestBody Evaluacion x){return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(x));}
 @PutMapping("/{id}") public Evaluacion update(@PathVariable Long id,@RequestBody Evaluacion x){if(!repo.existsById(id))throw new NoSuchElementException("No existe");x.setId(id);return repo.save(x);}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){if(!repo.existsById(id))return ResponseEntity.notFound().build();repo.deleteById(id);return ResponseEntity.noContent().build();}
}
