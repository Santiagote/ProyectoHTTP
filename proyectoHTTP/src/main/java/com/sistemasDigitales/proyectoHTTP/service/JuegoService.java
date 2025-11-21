package com.sistemasDigitales.proyectoHTTP.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemasDigitales.proyectoHTTP.model.Juego;
import com.sistemasDigitales.proyectoHTTP.repository.JuegoRepository;
@Service
public class JuegoService {
    @Autowired
    private JuegoRepository juegosRepository;

    public List<Juego> getAllJuegos() {
        return juegosRepository.findAll();
    }

    public Juego getJuegoById(int id) {
        return juegosRepository.findById(id).orElse(null);
    }

    public Juego createJuego(Juego juego) {
        return juegosRepository.save(juego);
    }

    public Juego updateJuego(int id, Juego juegoDetails) {
        Juego juego = juegosRepository.findById(id).orElse(null);
        if (juego != null) {
            juego.setNombre(juegoDetails.getNombre());
            juego.setGenero(juegoDetails.getGenero());
            juego.setPlataforma(juegoDetails.getPlataforma());
            juego.setPrecio(juegoDetails.getPrecio());
            return juegosRepository.save(juego);
        }
        return null;
    }

    public boolean deleteJuego(int id) {
        if (juegosRepository.existsById(id)) {
            juegosRepository.deleteById(id);
            return true;
        }
        return false;
    }

    
}
