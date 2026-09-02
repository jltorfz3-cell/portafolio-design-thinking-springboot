package com.portafolio.dt.controller;
import com.portafolio.dt.model.Rol; import com.portafolio.dt.repository.RolRepository; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/roles") public class RolController{
 private final RolRepository repo; public RolController(RolRepository r){repo=r;}
 @GetMapping public List<Rol> all(){return repo.findAll();} 
 @GetMapping("/{id}") public Rol one(@PathVariable Long id){return repo.findById(id).orElseThrow();}
 @PostMapping public ResponseEntity<Rol> create(@RequestBody Rol x){return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(x));}
 @PutMapping("/{id}") public Rol update(@PathVariable Long id,@RequestBody Rol x){if(!repo.existsById(id))throw new NoSuchElementException("No existe");x.setId(id);return repo.save(x);}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){if(!repo.existsById(id))return ResponseEntity.notFound().build();repo.deleteById(id);return ResponseEntity.noContent().build();}
}
