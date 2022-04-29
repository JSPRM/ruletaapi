package com.ibm.academia.ruletaapi.services;

import com.ibm.academia.ruletaapi.entities.Ruleta;

import java.util.List;

public interface RuletaDAO {
    List<Ruleta> listarTodasLasRuletas();

    Ruleta agregarNuevaRuleta();

    Ruleta abrirRuleta(Long id_ruleta);
}
