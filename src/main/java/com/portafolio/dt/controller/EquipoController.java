package com.portafolio.dt.controller;
import com.portafolio.dt.model.Equipo; import com.portafolio.dt.repository.EquipoRepository; import org.springframework.http.*; import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize; import java.util.*;
@RestController @RequestMapping("/api/equipos") @PreAuthorize("hasAnyRole('ADMIN','DOCENTE','COORDINADOR')")
public class EquipoController{
 private final EquipoRepository repo; public EquipoController(EquipoRepository r){repo=r;}
 @GetMapping public List<Equipo> all(){return repo.findAll();} 
 @GetMapping("/{id}") public Equipo one(@PathVariable Long id){return repo.findById(id).orElseThrow();}
 @PostMapping public ResponseEntity<Equipo> create(@RequestBody Equipo x){return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(x));}
 @PutMapping("/{id}") public Equipo update(@PathVariable Long id,@RequestBody Equipo x){if(!repo.existsById(id))throw new NoSuchElementException("No existe");x.setId(id);return repo.save(x);}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){if(!repo.existsById(id))return ResponseEntity.notFound().build();repo.deleteById(id);return ResponseEntity.noContent().build();}
}
