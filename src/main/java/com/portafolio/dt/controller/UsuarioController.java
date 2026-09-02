package com.portafolio.dt.controller;

import com.portafolio.dt.model.Usuario;
import com.portafolio.dt.repository.UsuarioRepository;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/usuarios")
@PreAuthorize("hasRole('ADMIN')")
public class UsuarioController {
    private final UsuarioRepository repo;
    public UsuarioController(UsuarioRepository repo) { this.repo = repo; }

    @GetMapping public List<Usuario> all() { return repo.findAll(); }
    @GetMapping("/{id}") public Usuario one(@PathVariable Long id) { return repo.findById(id).orElseThrow(); }
    @PostMapping public ResponseEntity<Usuario> create(@RequestBody Usuario x) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(x));
    }
    @PutMapping("/{id}") public Usuario update(@PathVariable Long id, @RequestBody Usuario x) {
        if (!repo.existsById(id)) throw new NoSuchElementException("No existe");
        x.setId(id); return repo.save(x);
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id); return ResponseEntity.noContent().build();
    }
}
