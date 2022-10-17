package com.portfolio.lucianoRivera.Repository;

import com.portfolio.lucianoRivera.Entity.Habilidad;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RHabilidad extends JpaRepository<Habilidad, Integer> {
    
    Optional<Habilidad> findByNombreHab(String nombreHab);
    
    public boolean existsByNombreHab(String nombreHab);
}
