package com.portfolio.lucianoRivera.Security.Service;

import com.portfolio.lucianoRivera.Security.Entity.Usuario;
import com.portfolio.lucianoRivera.Security.Repository.iUsuarioRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UsuarioService {
    @Autowired
    iUsuarioRepository iusuRepository;
    
    public Optional<Usuario> getByNombreUsuario(String nombreUsuario) {
        return iusuRepository.findByNombreUsuario(nombreUsuario);
    }
    
    public boolean existsByNombreUsuario(String nombreUsuario) {
        return iusuRepository.existsByNombreUsuario(nombreUsuario);
    }
    
    public boolean existsByEmail(String email) {
        return iusuRepository.existsByEmail(email);
    }
    
    public void save(Usuario usuario) {
        iusuRepository.save(usuario);
    }
}
