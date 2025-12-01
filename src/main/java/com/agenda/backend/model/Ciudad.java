package com.agenda.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ciudades")
public class Ciudad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 🔥 CAMBIADO A INTEGER

    private String nombreCiudad;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnore 
    private Usuario usuario;
}