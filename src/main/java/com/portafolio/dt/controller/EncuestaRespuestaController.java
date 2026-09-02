package com.portafolio.dt.controller;
import com.portafolio.dt.model.EncuestaRespuesta; import com.portafolio.dt.repository.EncuestaRespuestaRepository; import org.springframework.http.*; import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize; import java.util.*;
@RestController @RequestMapping("/api/encuesta-respuestas") @PreAuthorize("hasAnyRole('ADMIN','DOCENTE','ESTUDIANTE','COORDINADOR')")
public class EncuestaRespuestaController{
 private final EncuestaRespuestaRepository repo; public EncuestaRespuestaController(EncuestaRespuestaRepository r){repo=r;}
 @GetMapping public List<EncuestaRespuesta> all(){return repo.findAll();} 
 @GetMapping("/{id}") public EncuestaRespuesta one(@PathVariable Long id){return repo.findById(id).orElseThrow();}
 @PostMapping public ResponseEntity<EncuestaRespuesta> create(@RequestBody EncuestaRespuesta x){return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(x));}
 @PutMapping("/{id}") public EncuestaRespuesta update(@PathVariable Long id,@RequestBody EncuestaRespuesta x){if(!repo.existsById(id))throw new NoSuchElementException("No existe");x.setId(id);return repo.save(x);}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){if(!repo.existsById(id))return ResponseEntity.notFound().build();repo.deleteById(id);return ResponseEntity.noContent().build();}
}
