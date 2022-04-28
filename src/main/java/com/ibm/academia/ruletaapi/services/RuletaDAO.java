package com.ibm.academia.ruletaapi.services;

import com.ibm.academia.ruletaapi.entities.Ruleta;
import com.ibm.academia.ruletaapi.request.RuletaRegistroRequest;
import org.springframework.stereotype.Service;

@Service
public record RuletaDAO() {
    public void registrarRulera(RuletaRegistroRequest request) {
        Ruleta ruleta = Ruleta.builder().build();
        //todo: agregar fechas de creacion y modificacion
    }
}
