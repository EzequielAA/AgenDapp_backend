package com.agenda.backend.controller;

import com.agenda.backend.dto.ClimaResponse;
import com.agenda.backend.model.Ciudad;
import com.agenda.backend.service.ClimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clima")
public class ClimaController {

    @Autowired
    private ClimaService climaService;

    @PostMapping
    public ResponseEntity<?> agregarCiudad(@RequestBody Map<String, Object> payload) {
        try {
            // 🔥 Convertimos a Integer
            Integer usuarioId = Integer.valueOf(payload.get("usuarioId").toString());
            String ciudad = payload.get("nombreCiudad").toString();
            return ResponseEntity.ok(climaService.guardarCiudad(usuarioId, ciudad));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al guardar: " + e.getMessage());
        }
    }

    @GetMapping("/usuario/{id}")
    public List<ClimaResponse> verClima(@PathVariable Integer id) { // 🔥 Integer
        return climaService.obtenerClimaUsuario(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarCiudad(@PathVariable Integer id, @RequestBody Map<String, String> payload) { // 🔥 Integer
        Ciudad ciudad = climaService.modificarCiudad(id, payload.get("nombreCiudad"));
        if (ciudad != null) return ResponseEntity.ok(ciudad);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCiudad(@PathVariable Integer id) { // 🔥 Integer
        if (climaService.eliminarCiudad(id)) {
            return ResponseEntity.ok("Ciudad eliminada correctamente");
        }
        return ResponseEntity.notFound().build();
    }
}