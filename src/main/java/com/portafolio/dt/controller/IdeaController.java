package com.portafolio.dt.controller;
import com.portafolio.dt.model.Idea; import com.portafolio.dt.repository.IdeaRepository; import org.springframework.http.*; import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize; import java.util.*;
@RestController @RequestMapping("/api/ideas") @PreAuthorize("hasAnyRole('ADMIN','DOCENTE','ESTUDIANTE','COORDINADOR')")
public class IdeaController{
 private final IdeaRepository repo; public IdeaController(IdeaRepository r){repo=r;}
 @GetMapping public List<Idea> all(){return repo.findAll();} 
 @GetMapping("/{id}") public Idea one(@PathVariable Long id){return repo.findById(id).orElseThrow();}
 @PostMapping public ResponseEntity<Idea> create(@RequestBody Idea x){return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(x));}
 @PutMapping("/{id}") public Idea update(@PathVariable Long id,@RequestBody Idea x){if(!repo.existsById(id))throw new NoSuchElementException("No existe");x.setId(id);return repo.save(x);}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){if(!repo.existsById(id))return ResponseEntity.notFound().build();repo.deleteById(id);return ResponseEntity.noContent().build();}
}
