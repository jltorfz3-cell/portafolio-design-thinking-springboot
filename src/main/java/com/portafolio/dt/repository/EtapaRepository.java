package com.portafolio.dt.repository;
import com.portafolio.dt.model.Etapa; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface EtapaRepository extends JpaRepository<Etapa,Long>{ List<Etapa> findByProyectoIdOrderByIdAsc(Long proyectoId); }
