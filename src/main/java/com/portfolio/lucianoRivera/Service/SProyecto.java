package com.portfolio.lucianoRivera.Service;

import com.portfolio.lucianoRivera.Entity.Proyecto;
import com.portfolio.lucianoRivera.Repository.RProyecto;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SProyecto {
    @Autowired
    RProyecto rProy;
    
    public List<Proyecto> list(){
        return rProy.findAll();
    }
    
    public Optional<Proyecto> getOne(int id){
        return rProy.findById(id);
    }
    
    public Optional<Proyecto> getByNombreProy(String nombreProy) {
        return rProy.findByNombreProy(nombreProy);
    }
    
    public Optional<Proyecto> getByDescripcionProy(String descripcionProy){
        return rProy.findByDescripcionProy(descripcionProy);
    }
    
    public void save(Proyecto proy) {
        rProy.save(proy);
    }
    
    public void delete(int id) {
        rProy.deleteById(id);
    }
    
    public boolean existsById(int id) {
        return rProy.existsById(id);
    }
    
    public boolean existsByNombreProy(String nombreProy) {
        return rProy.existsByNombreProy(nombreProy);
    }
    
    public boolean existsByDescripcionProy(String descripcionProy){
        return rProy.existsByDescripcionProy(descripcionProy);
    }
}
