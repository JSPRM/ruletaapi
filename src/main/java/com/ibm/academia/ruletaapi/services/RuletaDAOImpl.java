package com.ibm.academia.ruletaapi.services;

import com.ibm.academia.ruletaapi.entities.Ruleta;
import com.ibm.academia.ruletaapi.repositories.RuletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RuletaDAOImpl implements RuletaDAO{

    private final RuletaRepository ruletaRepository;

    @Autowired
    public RuletaDAOImpl(RuletaRepository ruletaRepository) {
        this.ruletaRepository = ruletaRepository;
    }


    @Override
    public List<Ruleta> listarTodasLasRuletas() {
        return ruletaRepository.findAll();
    }

    @Override
    public Ruleta agregarNuevaRuleta() {
        return ruletaRepository.save(new Ruleta(false, new Date()));
    }

    @Override
    public Ruleta abrirRuleta(Long id_ruleta) {
        Optional<Ruleta> ruleta = ruletaRepository.findById(id_ruleta);
        if (ruleta.isPresent()){
            Ruleta ruletaActualizar = ruleta.get();
            ruletaActualizar.setFechaModificacion(new Date());
            ruletaActualizar.setEstaAbierto(true);
            ruletaRepository.save(ruletaActualizar);
            return ruletaActualizar;
        }else {
            return null;
        }
    }
}
