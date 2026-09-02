package com.portafolio.dt.controller;
import com.portafolio.dt.model.Institucion; import com.portafolio.dt.repository.InstitucionRepository; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/instituciones") public class InstitucionController{
 private final InstitucionRepository repo; public InstitucionController(InstitucionRepository r){repo=r;}
 @GetMapping public List<Institucion> all(){return repo.findAll();} 
 @GetMapping("/{id}") public Institucion one(@PathVariable Long id){return repo.findById(id).orElseThrow();}
 @PostMapping public ResponseEntity<Institucion> create(@RequestBody Institucion x){return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(x));}
 @PutMapping("/{id}") public Institucion update(@PathVariable Long id,@RequestBody Institucion x){if(!repo.existsById(id))throw new NoSuchElementException("No existe");x.setId(id);return repo.save(x);}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){if(!repo.existsById(id))return ResponseEntity.notFound().build();repo.deleteById(id);return ResponseEntity.noContent().build();}
}
