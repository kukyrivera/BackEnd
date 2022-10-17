package com.portfolio.lucianoRivera.Service;

import com.portfolio.lucianoRivera.Entity.Habilidad;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.portfolio.lucianoRivera.Repository.RHabilidad;

@Service
@Transactional
public class SHabilidades {
    @Autowired
    RHabilidad rhab;
    
    public List<Habilidad> list(){
        return rhab.findAll();
    }
    
    public Optional<Habilidad> getOne(int id) {
        return rhab.findById(id);
    }
    
    public Optional<Habilidad> getByNombreHab(String nombreHab) {
        return rhab.findByNombreHab(nombreHab);
    }
    
    public void save(Habilidad skill) {
        rhab.save(skill);
    }
    
    public void delete(int id) {
        rhab.deleteById(id);
    }
    
    public boolean existsById(int id) {
        return rhab.existsById(id);
    }
    
    public boolean existsByNombreHab(String nombreHab) {
        return rhab.existsByNombreHab(nombreHab);
    }
}
