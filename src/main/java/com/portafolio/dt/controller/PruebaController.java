package com.portafolio.dt.controller;
import com.portafolio.dt.model.Prueba; import com.portafolio.dt.repository.PruebaRepository; import org.springframework.http.*; import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize; import java.util.*;
@RestController @RequestMapping("/api/pruebas") @PreAuthorize("hasAnyRole('ADMIN','DOCENTE','ESTUDIANTE','COORDINADOR')")
public class PruebaController{
 private final PruebaRepository repo; public PruebaController(PruebaRepository r){repo=r;}
 @GetMapping public List<Prueba> all(){return repo.findAll();} 
 @GetMapping("/{id}") public Prueba one(@PathVariable Long id){return repo.findById(id).orElseThrow();}
 @PostMapping public ResponseEntity<Prueba> create(@RequestBody Prueba x){return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(x));}
 @PutMapping("/{id}") public Prueba update(@PathVariable Long id,@RequestBody Prueba x){if(!repo.existsById(id))throw new NoSuchElementException("No existe");x.setId(id);return repo.save(x);}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){if(!repo.existsById(id))return ResponseEntity.notFound().build();repo.deleteById(id);return ResponseEntity.noContent().build();}
}
