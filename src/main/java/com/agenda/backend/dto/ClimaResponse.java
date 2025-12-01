package com.agenda.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClimaResponse {
    private String ciudad;
    private double temperatura;
    private String descripcion; // Ej: "Cielo claro"
    private double humedad;
}