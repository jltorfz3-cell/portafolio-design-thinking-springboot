package com.portafolio.dt.controller;
import com.portafolio.dt.model.Evidencia; import com.portafolio.dt.repository.EvidenciaRepository; import org.springframework.http.*; import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize; import java.util.*;
@RestController @RequestMapping("/api/evidencias") @PreAuthorize("hasAnyRole('ADMIN','DOCENTE','ESTUDIANTE','COORDINADOR')")
public class EvidenciaController{
 private final EvidenciaRepository repo; public EvidenciaController(EvidenciaRepository r){repo=r;}
 @GetMapping public List<Evidencia> all(){return repo.findAll();} 
 @GetMapping("/{id}") public Evidencia one(@PathVariable Long id){return repo.findById(id).orElseThrow();}
 @PostMapping public ResponseEntity<Evidencia> create(@RequestBody Evidencia x){return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(x));}
 @PutMapping("/{id}") public Evidencia update(@PathVariable Long id,@RequestBody Evidencia x){if(!repo.existsById(id))throw new NoSuchElementException("No existe");x.setId(id);return repo.save(x);}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){if(!repo.existsById(id))return ResponseEntity.notFound().build();repo.deleteById(id);return ResponseEntity.noContent().build();}
}
