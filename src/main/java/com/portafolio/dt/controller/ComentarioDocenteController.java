package com.portafolio.dt.controller;
import com.portafolio.dt.model.ComentarioDocente; import com.portafolio.dt.repository.ComentarioDocenteRepository; import org.springframework.http.*; import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize; import java.util.*;
@RestController @RequestMapping("/api/comentarios-docente") @PreAuthorize("hasAnyRole('ADMIN','DOCENTE','COORDINADOR')")
public class ComentarioDocenteController{
 private final ComentarioDocenteRepository repo; public ComentarioDocenteController(ComentarioDocenteRepository r){repo=r;}
 @GetMapping public List<ComentarioDocente> all(){return repo.findAll();} 
 @GetMapping("/{id}") public ComentarioDocente one(@PathVariable Long id){return repo.findById(id).orElseThrow();}
 @PostMapping public ResponseEntity<ComentarioDocente> create(@RequestBody ComentarioDocente x){return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(x));}
 @PutMapping("/{id}") public ComentarioDocente update(@PathVariable Long id,@RequestBody ComentarioDocente x){if(!repo.existsById(id))throw new NoSuchElementException("No existe");x.setId(id);return repo.save(x);}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){if(!repo.existsById(id))return ResponseEntity.notFound().build();repo.deleteById(id);return ResponseEntity.noContent().build();}
}
