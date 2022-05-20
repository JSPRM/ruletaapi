package com.ibm.academia.ruletaapi.services;

import com.ibm.academia.ruletaapi.models.entities.Apuesta;
import com.ibm.academia.ruletaapi.models.entities.Sesion;
import com.ibm.academia.ruletaapi.repositories.ApuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApuestaServiceImpl extends GenericoServiceImpl<Apuesta, ApuestaRepository> implements ApuestaService{

    @Autowired
    public ApuestaServiceImpl(ApuestaRepository repository) {
        super(repository);
    }

    @Override
    public Apuesta asociarConSesion(Apuesta apuesta, Sesion sesion) {
        apuesta.setSesion(sesion);
        return repository.save(apuesta);
    }
}
