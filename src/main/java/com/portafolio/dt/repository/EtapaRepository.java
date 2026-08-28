package com.portafolio.dt.repository;
import com.portafolio.dt.entity.Etapa; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface EtapaRepository extends JpaRepository<Etapa,Long>{List<Etapa> findByProyectoIdOrderById(Long proyectoId);}
