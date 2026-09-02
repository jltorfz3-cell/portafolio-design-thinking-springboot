package com.portafolio.dt.controller;
import com.portafolio.dt.model.IndicadorPrueba; import com.portafolio.dt.repository.IndicadorPruebaRepository; import org.springframework.http.*; import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize; import java.util.*;
@RestController @RequestMapping("/api/indicadores-prueba") @PreAuthorize("hasAnyRole('ADMIN','DOCENTE','ESTUDIANTE','COORDINADOR')")
public class IndicadorPruebaController{
 private final IndicadorPruebaRepository repo; public IndicadorPruebaController(IndicadorPruebaRepository r){repo=r;}
 @GetMapping public List<IndicadorPrueba> all(){return repo.findAll();} 
 @GetMapping("/{id}") public IndicadorPrueba one(@PathVariable Long id){return repo.findById(id).orElseThrow();}
 @PostMapping public ResponseEntity<IndicadorPrueba> create(@RequestBody IndicadorPrueba x){return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(x));}
 @PutMapping("/{id}") public IndicadorPrueba update(@PathVariable Long id,@RequestBody IndicadorPrueba x){if(!repo.existsById(id))throw new NoSuchElementException("No existe");x.setId(id);return repo.save(x);}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){if(!repo.existsById(id))return ResponseEntity.notFound().build();repo.deleteById(id);return ResponseEntity.noContent().build();}
}
