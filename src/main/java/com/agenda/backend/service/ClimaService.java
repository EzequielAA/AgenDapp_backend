package com.agenda.backend.service;

import com.agenda.backend.dto.ClimaResponse;
import com.agenda.backend.model.Ciudad;
import com.agenda.backend.model.Usuario;
import com.agenda.backend.repository.CiudadRepository;
import com.agenda.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ClimaService {

    @Autowired
    private CiudadRepository ciudadRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Value("${weather.api.key}")
    private String apiKey;
    @Value("${weather.api.url}")
    private String apiUrl;

    public Ciudad guardarCiudad(Integer usuarioId, String nombreCiudad) { // 🔥 Integer
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Ciudad ciudad = new Ciudad();
        ciudad.setNombreCiudad(nombreCiudad);
        ciudad.setUsuario(usuario);
        return ciudadRepository.save(ciudad);
    }

    public List<ClimaResponse> obtenerClimaUsuario(Integer usuarioId) { // 🔥 Integer
        List<Ciudad> misCiudades = ciudadRepository.findByUsuarioId(usuarioId);
        List<ClimaResponse> reporteClima = new ArrayList<>();

        for (Ciudad c : misCiudades) {
            try {
                String url = String.format("%s?q=%s&appid=%s&units=metric&lang=es", apiUrl, c.getNombreCiudad(), apiKey);
                Map response = restTemplate.getForObject(url, Map.class);
                
                Map main = (Map) response.get("main");
                List weatherList = (List) response.get("weather");
                Map weather = (Map) weatherList.get(0);

                double temp = Double.valueOf(main.get("temp").toString());
                double hum = Double.valueOf(main.get("humidity").toString());
                String desc = weather.get("description").toString();

                reporteClima.add(new ClimaResponse(c.getNombreCiudad(), temp, desc, hum));
            } catch (Exception e) {
                reporteClima.add(new ClimaResponse(c.getNombreCiudad(), 0, "Error: No encontrada", 0));
            }
        }
        return reporteClima;
    }

    public Ciudad modificarCiudad(Integer id, String nuevoNombre) { // 🔥 Integer
        Ciudad ciudad = ciudadRepository.findById(id).orElse(null);
        if (ciudad != null) {
            ciudad.setNombreCiudad(nuevoNombre);
            return ciudadRepository.save(ciudad);
        }
        return null;
    }

    public boolean eliminarCiudad(Integer id) { // 🔥 Integer
        if (ciudadRepository.existsById(id)) {
            ciudadRepository.deleteById(id);
            return true;
        }
        return false;
    }
}