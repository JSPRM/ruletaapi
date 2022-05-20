package com.ibm.academia.ruletaapi.controller;

import com.ibm.academia.ruletaapi.exceptions.NotFoundException;
import com.ibm.academia.ruletaapi.mapper.RuletaMapper;
import com.ibm.academia.ruletaapi.models.entities.Apuesta;
import com.ibm.academia.ruletaapi.models.entities.Ruleta;
import com.ibm.academia.ruletaapi.models.entities.Sesion;
import com.ibm.academia.ruletaapi.services.RuletaService;
import com.ibm.academia.ruletaapi.services.SesionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("api/v1/ruleta")
public class RuletaController {
    private final RuletaService ruletaService;
    private final SesionService sesionService;

    private final RuletaMapper ruletaMapper;

    @Autowired
    public RuletaController(RuletaService ruletaService, SesionService sesionService, RuletaMapper ruletaMapper) {
        this.ruletaService = ruletaService;
        this.sesionService = sesionService;
        this.ruletaMapper = ruletaMapper;
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarTodasLasRuletas(){
        Iterable<Ruleta> ruletas = ruletaService.buscarTodos();
        return new ResponseEntity<>(ruletas, HttpStatus.OK);
    }

    @PostMapping("/nueva")
    public ResponseEntity<?> nuevaRuleta(){
        Ruleta ruleta = ruletaService.guardar(new Ruleta(false));
        return new ResponseEntity<>(ruletaMapper.mapRuleta(ruleta), HttpStatus.CREATED);
    }

    @PutMapping("/apertura")
    public ResponseEntity<?> abrirRuleta(Long idRuleta){
        Optional<Ruleta> oRuleta = ruletaService.buscarPorId(idRuleta);
        if(oRuleta.isEmpty()){
            throw new NotFoundException(String.format("Ruleta con id %d no existe", idRuleta));
        }

        Ruleta ruleta = ruletaService.abrirRuleta(oRuleta.get());
        Sesion sesion = sesionService.guardar(new Sesion(ruleta));
        return new ResponseEntity<>(oRuleta.get(), HttpStatus.OK);
    }

    @PostMapping("/apuesta")
    public ResponseEntity<?> nuevaApuestas(@RequestBody Apuesta apuesta){
        return null;
    }
}
