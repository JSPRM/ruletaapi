package com.ibm.academia.ruletaapi.services;

import com.ibm.academia.ruletaapi.models.entities.Ruleta;


public interface RuletaService extends GenericoService<Ruleta> {
    Ruleta abrirRuleta(Ruleta ruleta);
    Ruleta cerrarRuleta(Ruleta ruleta);
    Long abrir(Long ruleta);
}

