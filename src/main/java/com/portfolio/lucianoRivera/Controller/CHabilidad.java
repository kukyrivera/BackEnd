package com.portfolio.lucianoRivera.Controller;

import com.portfolio.lucianoRivera.Dto.dtoHabilidad;
import com.portfolio.lucianoRivera.Entity.Habilidad;
import com.portfolio.lucianoRivera.Security.Controller.Mensaje;
import com.portfolio.lucianoRivera.Service.SHabilidades;
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
@RequestMapping("/habilidad")
public class CHabilidad {
    @Autowired
    SHabilidades shab;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Habilidad>> list() {
        List<Habilidad> list = shab.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Habilidad> getById(@PathVariable("id") int id){
        
        if(!shab.existsById(id))
            return new ResponseEntity(new Mensaje("No se encontró ninguna habilidad"), HttpStatus.NOT_FOUND);
        
        Habilidad hab = shab.getOne(id).get();
        return new ResponseEntity(hab, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoHabilidad dtohab){
        
        if(StringUtils.isBlank(dtohab.getNombreHab()))
            return new ResponseEntity(new Mensaje("El campo nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        if(dtohab.getPorcentajeHab() <= 0 )
            return new ResponseEntity(new Mensaje("El campo porcentaje es obligatorio"), HttpStatus.BAD_REQUEST);
        
        if(shab.existsByNombreHab(dtohab.getNombreHab()))
            return new ResponseEntity(new Mensaje("Esa habilidad ya existe"), HttpStatus.BAD_REQUEST);
        
        Habilidad habilidad = new Habilidad(dtohab.getNombreHab(), dtohab.getPorcentajeHab());
        shab.save(habilidad);
        return new ResponseEntity(new Mensaje("La habilidad fue creada con éxito"), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoHabilidad dtohab){
        
        if(!shab.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        
        if(shab.existsByNombreHab(dtohab.getNombreHab()) && shab.getByNombreHab(dtohab.getNombreHab()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Esa habilidad ya existe"), HttpStatus.BAD_REQUEST);
            
        if(StringUtils.isBlank(dtohab.getNombreHab()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        if(dtohab.getPorcentajeHab() <= 0)
            return new ResponseEntity(new Mensaje("El campo porcentaje es obligatorio"), HttpStatus.BAD_REQUEST);
        
        Habilidad habilidad = shab.getOne(id).get();
        habilidad.setNombreHab(dtohab.getNombreHab());
        habilidad.setPorcentajeHab(dtohab.getPorcentajeHab());
        
        shab.save(habilidad);
        return new ResponseEntity(new Mensaje("Habilidad actualizada con éxito"), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        
        if(!shab.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        
        shab.delete(id);
        return new ResponseEntity(new Mensaje("Habilidad eliminada con éxito"), HttpStatus.OK);
    }
}