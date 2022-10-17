package com.portfolio.lucianoRivera.Controller;

import com.portfolio.lucianoRivera.Dto.dtoProyecto;
import com.portfolio.lucianoRivera.Entity.Proyecto;
import com.portfolio.lucianoRivera.Security.Controller.Mensaje;
import com.portfolio.lucianoRivera.Service.SProyecto;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/proyecto")
public class CProyecto {
    @Autowired
    SProyecto sProy;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Proyecto>> list() {
        List<Proyecto> list = sProy.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyecto> getById(@PathVariable("id") int id){
        
        if(!sProy.existsById(id))
            return new ResponseEntity(new Mensaje("No se encontró ningun proyecto"), HttpStatus.NOT_FOUND);
        
        Proyecto proyecto = sProy.getOne(id).get();
        return new ResponseEntity(proyecto, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoProyecto dtoProy){
        
        if(StringUtils.isBlank(dtoProy.getNombreProy()))
            return new ResponseEntity(new Mensaje("El campo nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(dtoProy.getDescripcionProy()))
            return new ResponseEntity(new Mensaje("El campo descripcion es obligatorio"), HttpStatus.BAD_REQUEST);
        
        if(sProy.existsByNombreProy(dtoProy.getNombreProy()))
            return new ResponseEntity(new Mensaje("Ese proyecto ya existe"), HttpStatus.BAD_REQUEST);
        
        if(sProy.existsByDescripcionProy(dtoProy.getDescripcionProy()))
            return new ResponseEntity(new Mensaje("Esa descripción ya existe"), HttpStatus.BAD_REQUEST);
        
        Proyecto proyecto = new Proyecto(dtoProy.getNombreProy(), dtoProy.getDescripcionProy());
        sProy.save(proyecto);
        
        return new ResponseEntity(new Mensaje("El proyecto fue creado con éxito"), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoProyecto dtoProy){
        
        if(!sProy.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        
        if(sProy.existsByNombreProy(dtoProy.getNombreProy()) && sProy.getByNombreProy(dtoProy.getNombreProy()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Ese proyecto ya existe"), HttpStatus.BAD_REQUEST);
        
        if(sProy.existsByDescripcionProy(dtoProy.getDescripcionProy()) && sProy.getByDescripcionProy(dtoProy.getDescripcionProy()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Esa descripción ya existe"), HttpStatus.BAD_REQUEST);
            
        if(StringUtils.isBlank(dtoProy.getNombreProy()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(dtoProy.getDescripcionProy()))
            return new ResponseEntity(new Mensaje("El campo descripcion es obligatorio"), HttpStatus.BAD_REQUEST);
        
        Proyecto proyecto = sProy.getOne(id).get();
        proyecto.setNombreProy(dtoProy.getNombreProy());
        proyecto.setDescripcionProy(dtoProy.getDescripcionProy());
        
        sProy.save(proyecto);
        return new ResponseEntity(new Mensaje("Proyecto actualizado con éxito"), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        
        if(!sProy.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        
        sProy.delete(id);
        return new ResponseEntity(new Mensaje("Proyecto eliminado con éxito"), HttpStatus.OK);
    }
}
