package com.agenda.backend.controller;

import com.agenda.backend.model.Usuario;
import com.agenda.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registro")
    public Usuario registrar(@RequestBody Usuario usuario) {
        return usuarioService.registrar(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario loginData) {
        Usuario usuario = usuarioService.login(loginData.getEmail(), loginData.getPassword());
        if (usuario != null) return ResponseEntity.ok(usuario);
        return ResponseEntity.status(401).body("Credenciales incorrectas");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable Integer id) { // 🔥 Integer
        Usuario usuario = usuarioService.buscarPorId(id);
        if (usuario != null) return ResponseEntity.ok(usuario);
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Integer id, @RequestBody Usuario usuario) { // 🔥 Integer
        Usuario actualizado = usuarioService.modificar(id, usuario);
        if (actualizado != null) return ResponseEntity.ok(actualizado);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) { // 🔥 Integer
        if (usuarioService.eliminar(id)) return ResponseEntity.ok("Usuario eliminado");
        return ResponseEntity.notFound().build();
    }
}