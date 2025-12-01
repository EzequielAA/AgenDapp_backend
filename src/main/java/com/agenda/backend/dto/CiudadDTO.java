package com.agenda.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CiudadDTO {
    private Long id;
    private String nombreCiudad;
    private Long usuarioId;
}