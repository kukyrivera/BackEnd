package com.portfolio.lucianoRivera.Controller;


import com.portfolio.lucianoRivera.Dto.dtoExperiencia;
import com.portfolio.lucianoRivera.Entity.Experiencia;
import com.portfolio.lucianoRivera.Security.Controller.Mensaje;
import com.portfolio.lucianoRivera.Service.SExperiencia;
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
@RequestMapping("/explab")
public class CExperiencia {
    @Autowired
    SExperiencia sExperiencia;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Experiencia>> list() {
        List<Experiencia> list = sExperiencia.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable("id") int id){
        
        if(!sExperiencia.existsById(id))
            return new ResponseEntity(new Mensaje("No se encontró ninguna experiencia"), HttpStatus.NOT_FOUND);
        
        Experiencia experiencia = sExperiencia.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoExperiencia dtoExp){
        
        if(StringUtils.isBlank(dtoExp.getNombreExp()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(dtoExp.getDescripcionExp()))
            return new ResponseEntity(new Mensaje("La descripcion es obligatoria"), HttpStatus.BAD_REQUEST);
        
        if(sExperiencia.existsByNombreExp(dtoExp.getNombreExp()))
            return new ResponseEntity(new Mensaje("Esa experiencia ya existe"), HttpStatus.BAD_REQUEST);
        
        if(sExperiencia.existsByDescripcionExp(dtoExp.getDescripcionExp()))
            return new ResponseEntity(new Mensaje("Esa descripción ya existe"), HttpStatus.BAD_REQUEST);
        
        Experiencia experiencia = new Experiencia(dtoExp.getNombreExp(), dtoExp.getDescripcionExp());
        sExperiencia.save(experiencia);
        
        return new ResponseEntity(new Mensaje("La experiencia fue creada con éxito"), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoExperiencia dtoExp){
        
        if(!sExperiencia.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        
        if(sExperiencia.existsByNombreExp(dtoExp.getNombreExp()) && sExperiencia.getByNombreExp(dtoExp.getNombreExp()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Esa experiencia ya existe"), HttpStatus.BAD_REQUEST);
        
        if(sExperiencia.existsByDescripcionExp(dtoExp.getDescripcionExp()) && sExperiencia.getByDescripcionExp(dtoExp.getDescripcionExp()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Esa descripción ya existe"), HttpStatus.BAD_REQUEST);
            
        if(StringUtils.isBlank(dtoExp.getNombreExp()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(dtoExp.getDescripcionExp()))
            return new ResponseEntity(new Mensaje("La descripcion es obligatoria"), HttpStatus.BAD_REQUEST);
        
        Experiencia experiencia = sExperiencia.getOne(id).get();
        experiencia.setNombreExp(dtoExp.getNombreExp());
        experiencia.setDescripcionExp(dtoExp.getDescripcionExp());
        
        sExperiencia.save(experiencia);
        return new ResponseEntity(new Mensaje("Experiencia actualizada con éxito"), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        
        if(!sExperiencia.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        
        sExperiencia.delete(id);
        return new ResponseEntity(new Mensaje("Experiencia eliminada con éxito"), HttpStatus.OK);
    }
}
