package com.portafolio.dt.controller;
import com.portafolio.dt.model.Estudiante; import com.portafolio.dt.repository.EstudianteRepository; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/estudiantes") public class EstudianteController{
 private final EstudianteRepository repo; public EstudianteController(EstudianteRepository r){repo=r;}
 @GetMapping public List<Estudiante> all(){return repo.findAll();} 
 @GetMapping("/{id}") public Estudiante one(@PathVariable Long id){return repo.findById(id).orElseThrow();}
 @PostMapping public ResponseEntity<Estudiante> create(@RequestBody Estudiante x){return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(x));}
 @PutMapping("/{id}") public Estudiante update(@PathVariable Long id,@RequestBody Estudiante x){if(!repo.existsById(id))throw new NoSuchElementException("No existe");x.setId(id);return repo.save(x);}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){if(!repo.existsById(id))return ResponseEntity.notFound().build();repo.deleteById(id);return ResponseEntity.noContent().build();}
}
