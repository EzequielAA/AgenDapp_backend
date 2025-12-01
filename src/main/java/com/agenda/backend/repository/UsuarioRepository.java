package com.agenda.backend.repository;

import com.agenda.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// 🔥 JpaRepository<Usuario, Integer>
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);
}