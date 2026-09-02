package com.portafolio.dt.controller;

import com.portafolio.dt.model.Proyecto;
import com.portafolio.dt.repository.ProyectoRepository;
import com.portafolio.dt.service.ProyectoService;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoController {
    private final ProyectoRepository repo;
    private final ProyectoService service;

    public ProyectoController(ProyectoRepository repo, ProyectoService service) {
        this.repo = repo;
        this.service = service;
    }

    @GetMapping
    public List<Proyecto> all() { return repo.findAll(); }

    @GetMapping("/{id}")
    public Proyecto one(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','DOCENTE','COORDINADOR')")
    public ResponseEntity<Proyecto> create(@RequestBody Proyecto x) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(x));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DOCENTE','COORDINADOR')")
    public Proyecto update(@PathVariable Long id, @RequestBody Proyecto x) {
        if (!repo.existsById(id)) throw new NoSuchElementException("No existe");
        x.setId(id);
        return repo.save(x);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','COORDINADOR')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
