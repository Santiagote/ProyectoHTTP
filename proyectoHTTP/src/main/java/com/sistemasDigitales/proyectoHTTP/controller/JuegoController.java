package com.sistemasDigitales.proyectoHTTP.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemasDigitales.proyectoHTTP.model.Juego;
import com.sistemasDigitales.proyectoHTTP.service.JuegoService;

@RestController
@RequestMapping("/api/juegos")
@CrossOrigin(origins = "*")
public class JuegoController {

    private final JuegoService juegoService;

    public JuegoController(JuegoService juegoService) {
        this.juegoService = juegoService;
    }

    // GET - Obtener todos los juegos
    @GetMapping
    public ResponseEntity<List<Juego>> getAllJuegos() {
        return ResponseEntity.ok(juegoService.getAllJuegos());
    }

    // GET - Obtener un juego por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getJuegoById(@PathVariable Integer id) {
        Juego juego = juegoService.getJuegoById(id);
        if (juego != null) {
            return ResponseEntity.ok(juego);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Juego no encontrado con ID: " + id);
    }

    // POST - Crear un nuevo juego (ruta /guardar)
    @PostMapping("/guardar")
    public ResponseEntity<?> createJuego(@RequestBody Juego juego) {
        try {
            Juego createdJuego = juegoService.createJuego(juego);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdJuego);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al crear el juego: " + e.getMessage());
        }
    }

    // PUT - Actualizar un juego
    @PutMapping("/{id}")
    public ResponseEntity<?> updateJuego(@PathVariable Integer id, @RequestBody Juego juegoDetails) {
        Juego updatedJuego = juegoService.updateJuego(id, juegoDetails);
        if (updatedJuego != null) {
            return ResponseEntity.ok(updatedJuego);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Juego no encontrado con ID: " + id);
    }

    // DELETE - Eliminar un juego
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJuego(@PathVariable Integer id) {
        boolean deleted = juegoService.deleteJuego(id);
        if (deleted) {
            return ResponseEntity.ok("Juego eliminado exitosamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Juego no encontrado con ID: " + id);
    }
}
