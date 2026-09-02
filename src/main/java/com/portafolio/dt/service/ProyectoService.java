package com.portafolio.dt.service;

import com.portafolio.dt.model.Proyecto;
import com.portafolio.dt.repository.ProyectoRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ProyectoService {
    private final ProyectoRepository repo;
    private final EntityManager entityManager;

    public ProyectoService(ProyectoRepository repo, EntityManager entityManager) {
        this.repo = repo;
        this.entityManager = entityManager;
    }

    @Transactional
    public Proyecto create(Proyecto proyecto) {
        Proyecto saved = repo.save(proyecto);

        // La BD suministrada contiene la función crear_etapas_proyecto(id),
        // que crea automáticamente las cinco etapas de Design Thinking.
        entityManager.createNativeQuery(
            "SELECT crear_etapas_proyecto(:id)"
        ).setParameter("id", saved.getId()).getSingleResult();

        return saved;
    }
}
