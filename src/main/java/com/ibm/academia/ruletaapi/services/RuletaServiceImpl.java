package com.ibm.academia.ruletaapi.services;

import com.ibm.academia.ruletaapi.models.entities.Ruleta;
import com.ibm.academia.ruletaapi.repositories.RuletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class RuletaServiceImpl extends GenericoServiceImpl<Ruleta, RuletaRepository> implements RuletaService {

    @Autowired
    public RuletaServiceImpl(RuletaRepository repository) {
        super(repository);
    }


    @Override
    public Ruleta abrirRuleta(Ruleta ruleta) {
        ruleta.setEstaAbierto(true);
        repository.save(ruleta);
        return ruleta;
    }

    @Override
    public Ruleta cerrarRuleta(Ruleta ruleta) {
        ruleta.setEstaAbierto(false);
        return repository.save(ruleta);
    }

    @Override
    public Long abrir(Long ruleta) {

        return ruleta;
    }
}
