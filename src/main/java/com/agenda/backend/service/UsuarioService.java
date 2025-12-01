package com.agenda.backend.service;

import com.agenda.backend.model.Usuario;
import com.agenda.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario registrar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario login(String email, String password) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (usuario.getPassword().equals(password)) {
                return usuario;
            }
        }
        return null;
    }

    public Usuario buscarPorId(Integer id) { // 🔥 Integer
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario modificar(Integer id, Usuario nuevosDatos) { // 🔥 Integer
        Usuario usuario = buscarPorId(id);
        if (usuario != null) {
            usuario.setNombre(nuevosDatos.getNombre());
            usuario.setEmail(nuevosDatos.getEmail());
            if (nuevosDatos.getPassword() != null && !nuevosDatos.getPassword().isEmpty()) {
                usuario.setPassword(nuevosDatos.getPassword());
            }
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    public boolean eliminar(Integer id) { // 🔥 Integer
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}