package com.ibm.academia.ruletaapi.services;

import com.ibm.academia.ruletaapi.entities.Ruleta;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface RuletaDAO {
    List<Ruleta> listarTodasLasRuletas();

    Ruleta agregarNuevaRuleta();

    ResponseEntity<?> abrirRuleta(Long id_ruleta);
}
