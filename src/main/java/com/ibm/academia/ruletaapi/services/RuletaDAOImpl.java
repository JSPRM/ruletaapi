package com.ibm.academia.ruletaapi.services;

import com.ibm.academia.ruletaapi.entities.Ruleta;
import com.ibm.academia.ruletaapi.repositories.RuletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public List<Ruleta> listarTodasLasRuletas() {
        return ruletaRepository.findAll();
    }

    @Override
    @Transactional
    public Ruleta agregarNuevaRuleta() {
        return ruletaRepository.save(new Ruleta(false, new Date()));
    }

    @Override
    @Transactional
    public ResponseEntity<?> abrirRuleta(Long id_ruleta) {
        Optional<Ruleta> ruleta = ruletaRepository.findById(id_ruleta);
        Date fechaActual = new Date();
        if (ruleta.isPresent()){
            Ruleta ruletaActualizar = ruleta.get();
            ruletaActualizar.setFechaModificacion(fechaActual);
            ruletaActualizar.setEstaAbierto(true);
            ruletaRepository.save(ruletaActualizar);
            String[] omitirCampos = {"fechaAlta"};
            MappingJacksonValue jacksonValue = FiltrarBeanService.filterBean(omitirCampos, "ruletaFiltro", ruletaActualizar);
            return new ResponseEntity<>(jacksonValue, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error lol");
    }
}
