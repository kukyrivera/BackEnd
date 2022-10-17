package com.portfolio.lucianoRivera.Repository;

import com.portfolio.lucianoRivera.Entity.Educacion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface REducacion extends JpaRepository<Educacion, Integer> {
    public Optional<Educacion> findByNombreEd(String nombreEd);
    
    public Optional<Educacion> findByDescripcionEd(String descripcionEd);
    
    public boolean existsByNombreEd(String nombreEd);
    
    public boolean existsByDescripcionEd(String descripcionEd);
}