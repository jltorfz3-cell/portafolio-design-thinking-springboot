package com.portafolio.dt.controller;
import com.portafolio.dt.model.Auditoria; import com.portafolio.dt.repository.AuditoriaRepository; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/auditoria") public class AuditoriaController{
 private final AuditoriaRepository repo; public AuditoriaController(AuditoriaRepository r){repo=r;}
 @GetMapping public List<Auditoria> all(){return repo.findAll();} 
 @GetMapping("/{id}") public Auditoria one(@PathVariable Long id){return repo.findById(id).orElseThrow();}
 @PostMapping public ResponseEntity<Auditoria> create(@RequestBody Auditoria x){return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(x));}
 @PutMapping("/{id}") public Auditoria update(@PathVariable Long id,@RequestBody Auditoria x){if(!repo.existsById(id))throw new NoSuchElementException("No existe");x.setId(id);return repo.save(x);}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){if(!repo.existsById(id))return ResponseEntity.notFound().build();repo.deleteById(id);return ResponseEntity.noContent().build();}
}
