package com.portfolio.lucianoRivera.Controller;

import com.portfolio.lucianoRivera.Dto.dtoPersona;
import com.portfolio.lucianoRivera.Entity.Persona;
import com.portfolio.lucianoRivera.Security.Controller.Mensaje;
import com.portfolio.lucianoRivera.Service.SPersona;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200", "https://portfolio-lr.web.app"}, allowedHeaders = "*")
@RestController
@RequestMapping("/persona")
public class CPersona {
    @Autowired
    SPersona sPersona;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list() {
        List<Persona> list = sPersona.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") int id){
        
        if(!sPersona.existsById(id))
            return new ResponseEntity(new Mensaje("No se encontró ninguna persona"), HttpStatus.NOT_FOUND);
        
        Persona persona = sPersona.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoPersona dtopersona){
        
        if(!sPersona.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        
        if(sPersona.existsByNombre(dtopersona.getNombre() + " " + dtopersona.getApellido()) && sPersona.getByNombre(dtopersona.getNombre() + " " + dtopersona.getApellido()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Esa persona ya existe"), HttpStatus.BAD_REQUEST);
        
        if(sPersona.existsByDescripcion(dtopersona.getDescripcion()) && sPersona.getByDescripcion(dtopersona.getDescripcion()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Esa descripción ya existe"), HttpStatus.BAD_REQUEST);
        
        if(sPersona.existsByImg(dtopersona.getImg()) && sPersona.getByImg(dtopersona.getImg()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Esa imagen ya existe"), HttpStatus.BAD_REQUEST);
            
        if(StringUtils.isBlank(dtopersona.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(dtopersona.getApellido()))
            return new ResponseEntity(new Mensaje("El apellido es obligatorio"), HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(dtopersona.getDescripcion()))
            return new ResponseEntity(new Mensaje("La descripcion es obligatoria"), HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(dtopersona.getImg()))
            return new ResponseEntity(new Mensaje("La imagen es obligatoria"), HttpStatus.BAD_REQUEST);
        
        Persona persona = sPersona.getOne(id).get();
        persona.setNombre(dtopersona.getNombre());
        persona.setApellido(dtopersona.getApellido());
        persona.setDescripcion(dtopersona.getDescripcion());
        persona.setImg(dtopersona.getImg());
        
        sPersona.save(persona);
        return new ResponseEntity(new Mensaje("Persona actualizada con éxito"), HttpStatus.OK);
    }
}
