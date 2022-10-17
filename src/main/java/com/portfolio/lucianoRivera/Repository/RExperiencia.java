package com.portfolio.lucianoRivera.Repository;

import com.portfolio.lucianoRivera.Entity.Experiencia;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RExperiencia extends JpaRepository<Experiencia, Integer> {
    
    public Optional<Experiencia> findByNombreExp(String nombreExp);
    
    public Optional<Experiencia> findByDescripcionExp(String descripcionExp);
    
    public boolean existsByNombreExp(String nombreExp);
    
    public boolean existsByDescripcionExp(String descripcionExp);
}
