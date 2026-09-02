package com.portafolio.dt.controller;
import com.portafolio.dt.model.Docente; import com.portafolio.dt.repository.DocenteRepository; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/docentes") public class DocenteController{
 private final DocenteRepository repo; public DocenteController(DocenteRepository r){repo=r;}
 @GetMapping public List<Docente> all(){return repo.findAll();} 
 @GetMapping("/{id}") public Docente one(@PathVariable Long id){return repo.findById(id).orElseThrow();}
 @PostMapping public ResponseEntity<Docente> create(@RequestBody Docente x){return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(x));}
 @PutMapping("/{id}") public Docente update(@PathVariable Long id,@RequestBody Docente x){if(!repo.existsById(id))throw new NoSuchElementException("No existe");x.setId(id);return repo.save(x);}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){if(!repo.existsById(id))return ResponseEntity.notFound().build();repo.deleteById(id);return ResponseEntity.noContent().build();}
}
