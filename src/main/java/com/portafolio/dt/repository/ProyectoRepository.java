package com.portafolio.dt.repository;
import com.portafolio.dt.entity.Proyecto; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface ProyectoRepository extends JpaRepository<Proyecto,Long>{List<Proyecto> findByEquipoId(Long equipoId);}
