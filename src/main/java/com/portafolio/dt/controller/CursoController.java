package com.portafolio.dt.controller;
import com.portafolio.dt.model.Curso; import com.portafolio.dt.repository.CursoRepository; import org.springframework.http.*; import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize; import java.util.*;
@RestController @RequestMapping("/api/cursos") @PreAuthorize("hasAnyRole('ADMIN','DOCENTE','COORDINADOR')")
public class CursoController{
 private final CursoRepository repo; public CursoController(CursoRepository r){repo=r;}
 @GetMapping public List<Curso> all(){return repo.findAll();} 
 @GetMapping("/{id}") public Curso one(@PathVariable Long id){return repo.findById(id).orElseThrow();}
 @PostMapping public ResponseEntity<Curso> create(@RequestBody Curso x){return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(x));}
 @PutMapping("/{id}") public Curso update(@PathVariable Long id,@RequestBody Curso x){if(!repo.existsById(id))throw new NoSuchElementException("No existe");x.setId(id);return repo.save(x);}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){if(!repo.existsById(id))return ResponseEntity.notFound().build();repo.deleteById(id);return ResponseEntity.noContent().build();}
}
