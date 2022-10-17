package com.portfolio.lucianoRivera.Service;

import com.portfolio.lucianoRivera.Entity.Persona;
import com.portfolio.lucianoRivera.Interface.IPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.portfolio.lucianoRivera.Repository.RPersona;

@Service
public class SPersona implements IPersonaService {
    @Autowired RPersona rPersona;
    
    @Override
    public List<Persona> getPersona() {
        List<Persona> persona = rPersona.findAll();
        return persona;
    }

    @Override
    public void savePersona(Persona persona) {
        rPersona.save(persona);
    }

    @Override
    public void deletePersona(Long id) {
        rPersona.deleteById(id);
    }

    @Override
    public Persona findPersona(Long id) {
        Persona persona = rPersona.findById(id).orElse(null);
        return persona;
    }
}
