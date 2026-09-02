package com.portafolio.dt.controller;
import com.portafolio.dt.model.Observacion; import com.portafolio.dt.repository.ObservacionRepository; import org.springframework.http.*; import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize; import java.util.*;
@RestController @RequestMapping("/api/observaciones") @PreAuthorize("hasAnyRole('ADMIN','DOCENTE','ESTUDIANTE','COORDINADOR')")
public class ObservacionController{
 private final ObservacionRepository repo; public ObservacionController(ObservacionRepository r){repo=r;}
 @GetMapping public List<Observacion> all(){return repo.findAll();} 
 @GetMapping("/{id}") public Observacion one(@PathVariable Long id){return repo.findById(id).orElseThrow();}
 @PostMapping public ResponseEntity<Observacion> create(@RequestBody Observacion x){return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(x));}
 @PutMapping("/{id}") public Observacion update(@PathVariable Long id,@RequestBody Observacion x){if(!repo.existsById(id))throw new NoSuchElementException("No existe");x.setId(id);return repo.save(x);}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){if(!repo.existsById(id))return ResponseEntity.notFound().build();repo.deleteById(id);return ResponseEntity.noContent().build();}
}
