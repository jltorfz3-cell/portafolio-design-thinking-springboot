package com.portafolio.dt.controller;
import com.portafolio.dt.model.Prototipo; import com.portafolio.dt.repository.PrototipoRepository; import org.springframework.http.*; import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize; import java.util.*;
@RestController @RequestMapping("/api/prototipos") @PreAuthorize("hasAnyRole('ADMIN','DOCENTE','ESTUDIANTE','COORDINADOR')")
public class PrototipoController{
 private final PrototipoRepository repo; public PrototipoController(PrototipoRepository r){repo=r;}
 @GetMapping public List<Prototipo> all(){return repo.findAll();} 
 @GetMapping("/{id}") public Prototipo one(@PathVariable Long id){return repo.findById(id).orElseThrow();}
 @PostMapping public ResponseEntity<Prototipo> create(@RequestBody Prototipo x){return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(x));}
 @PutMapping("/{id}") public Prototipo update(@PathVariable Long id,@RequestBody Prototipo x){if(!repo.existsById(id))throw new NoSuchElementException("No existe");x.setId(id);return repo.save(x);}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){if(!repo.existsById(id))return ResponseEntity.notFound().build();repo.deleteById(id);return ResponseEntity.noContent().build();}
}
