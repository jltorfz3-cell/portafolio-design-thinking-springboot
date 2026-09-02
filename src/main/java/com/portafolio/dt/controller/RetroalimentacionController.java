package com.portafolio.dt.controller;
import com.portafolio.dt.model.Retroalimentacion; import com.portafolio.dt.repository.RetroalimentacionRepository; import org.springframework.http.*; import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize; import java.util.*;
@RestController @RequestMapping("/api/retroalimentaciones") @PreAuthorize("hasAnyRole('ADMIN','DOCENTE','ESTUDIANTE','COORDINADOR')")
public class RetroalimentacionController{
 private final RetroalimentacionRepository repo; public RetroalimentacionController(RetroalimentacionRepository r){repo=r;}
 @GetMapping public List<Retroalimentacion> all(){return repo.findAll();} 
 @GetMapping("/{id}") public Retroalimentacion one(@PathVariable Long id){return repo.findById(id).orElseThrow();}
 @PostMapping public ResponseEntity<Retroalimentacion> create(@RequestBody Retroalimentacion x){return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(x));}
 @PutMapping("/{id}") public Retroalimentacion update(@PathVariable Long id,@RequestBody Retroalimentacion x){if(!repo.existsById(id))throw new NoSuchElementException("No existe");x.setId(id);return repo.save(x);}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){if(!repo.existsById(id))return ResponseEntity.notFound().build();repo.deleteById(id);return ResponseEntity.noContent().build();}
}
