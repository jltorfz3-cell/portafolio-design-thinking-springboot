package com.portafolio.dt.repository;
import com.portafolio.dt.model.Idea; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface IdeaRepository extends JpaRepository<Idea,Long>{  }
