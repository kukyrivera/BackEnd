package com.portfolio.lucianoRivera.Repository;

import com.portfolio.lucianoRivera.Entity.Persona;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RPersona extends JpaRepository<Persona, Integer> {
    
    public Optional<Persona> findByNombre(String nombre);
    
    public Optional<Persona> findByDescripcion(String descripcion);
    
    public Optional<Persona> findByImg(String img);
    
    public boolean existsByNombre(String nombre);
    
    public boolean existsByDescripcion(String descripcion);
    
    public boolean existsByImg(String img);
}
