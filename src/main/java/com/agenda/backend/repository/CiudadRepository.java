package com.agenda.backend.repository;

import com.agenda.backend.model.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// 🔥 JpaRepository<Ciudad, Integer>
public interface CiudadRepository extends JpaRepository<Ciudad, Integer> {
    List<Ciudad> findByUsuarioId(Integer usuarioId); // 🔥 Parámetro Integer
}