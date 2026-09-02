package com.portafolio.dt.controller;
import com.portafolio.dt.model.Etapa; import com.portafolio.dt.repository.EtapaRepository; import org.springframework.http.*; import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize; import java.util.*;
@RestController @RequestMapping("/api/etapas") @PreAuthorize("hasAnyRole('ADMIN','DOCENTE','ESTUDIANTE','COORDINADOR')")
public class EtapaController{
 private final EtapaRepository repo; public EtapaController(EtapaRepository r){repo=r;}
 @GetMapping public List<Etapa> all(){return repo.findAll();} @GetMapping("/proyecto/{proyectoId}") public List<Etapa> byProyecto(@PathVariable Long proyectoId){return repo.findByProyectoIdOrderByIdAsc(proyectoId);}
 @GetMapping("/{id}") public Etapa one(@PathVariable Long id){return repo.findById(id).orElseThrow();}
 @PostMapping public ResponseEntity<Etapa> create(@RequestBody Etapa x){return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(x));}
 @PutMapping("/{id}") public Etapa update(@PathVariable Long id,@RequestBody Etapa x){if(!repo.existsById(id))throw new NoSuchElementException("No existe");x.setId(id);return repo.save(x);}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){if(!repo.existsById(id))return ResponseEntity.notFound().build();repo.deleteById(id);return ResponseEntity.noContent().build();}
}
