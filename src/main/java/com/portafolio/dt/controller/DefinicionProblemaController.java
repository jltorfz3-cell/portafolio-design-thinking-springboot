package com.portafolio.dt.controller;
import com.portafolio.dt.model.DefinicionProblema; import com.portafolio.dt.repository.DefinicionProblemaRepository; import org.springframework.http.*; import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize; import java.util.*;
@RestController @RequestMapping("/api/definiciones-problema") @PreAuthorize("hasAnyRole('ADMIN','DOCENTE','ESTUDIANTE','COORDINADOR')")
public class DefinicionProblemaController{
 private final DefinicionProblemaRepository repo; public DefinicionProblemaController(DefinicionProblemaRepository r){repo=r;}
 @GetMapping public List<DefinicionProblema> all(){return repo.findAll();} 
 @GetMapping("/{id}") public DefinicionProblema one(@PathVariable Long id){return repo.findById(id).orElseThrow();}
 @PostMapping public ResponseEntity<DefinicionProblema> create(@RequestBody DefinicionProblema x){return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(x));}
 @PutMapping("/{id}") public DefinicionProblema update(@PathVariable Long id,@RequestBody DefinicionProblema x){if(!repo.existsById(id))throw new NoSuchElementException("No existe");x.setId(id);return repo.save(x);}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){if(!repo.existsById(id))return ResponseEntity.notFound().build();repo.deleteById(id);return ResponseEntity.noContent().build();}
}
