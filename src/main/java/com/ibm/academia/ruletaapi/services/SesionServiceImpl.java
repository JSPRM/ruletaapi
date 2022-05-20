package com.ibm.academia.ruletaapi.services;

import com.ibm.academia.ruletaapi.models.entities.Sesion;
import com.ibm.academia.ruletaapi.repositories.SesionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SesionServiceImpl extends GenericoServiceImpl<Sesion, SesionRepository> implements SesionService{

    @Autowired
    public SesionServiceImpl(SesionRepository repository) {
        super(repository);
    }

    @Override
    public Sesion cerrar(Sesion sesion) {
        sesion.setFechaCierre(new Date());
        return repository.save(sesion);
    }
}
