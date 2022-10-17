package com.portfolio.lucianoRivera.Repository;

import com.portfolio.lucianoRivera.Entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RPersona extends JpaRepository<Persona,Long> {
}
