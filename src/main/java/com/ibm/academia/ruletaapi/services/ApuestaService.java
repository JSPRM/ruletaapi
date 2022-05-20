package com.ibm.academia.ruletaapi.services;

import com.ibm.academia.ruletaapi.models.entities.Apuesta;
import com.ibm.academia.ruletaapi.models.entities.Sesion;

public interface ApuestaService extends GenericoService<Apuesta>{
    Apuesta asociarConSesion(Apuesta apuesta, Sesion sesion);
}
