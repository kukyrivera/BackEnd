package com.portfolio.lucianoRivera.Repository;

import com.portfolio.lucianoRivera.Entity.Proyecto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RProyecto extends JpaRepository<Proyecto, Integer> {
    
    public Optional<Proyecto> findByNombreProy(String nombreProy);
    
    public Optional<Proyecto> findByDescripcionProy(String descripcionProy);
    
    public boolean existsByNombreProy(String nombreProy);
    
    public boolean existsByDescripcionProy(String descripcionProy);
}
