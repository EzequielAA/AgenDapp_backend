package com.agenda.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 🔥 CAMBIADO A INTEGER

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password; 

    private String nombre;
    private String gender; 

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Ciudad> ciudades;
}