package com.ibm.academia.ruletaapi.services;

import com.ibm.academia.ruletaapi.models.entities.Sesion;

public interface SesionService extends GenericoService<Sesion>{
    Sesion cerrar(Sesion sesion);
}
