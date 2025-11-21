package com.sistemasDigitales.proyectoHTTP.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemasDigitales.proyectoHTTP.model.Juego;

@Repository
public interface JuegoRepository extends JpaRepository<Juego, Integer> {

        Juego findByNombre(String nombre);
    
}
