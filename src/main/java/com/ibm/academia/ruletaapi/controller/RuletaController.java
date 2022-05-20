package com.ibm.academia.ruletaapi.controller;

import com.ibm.academia.ruletaapi.exceptions.NotFoundException;
import com.ibm.academia.ruletaapi.exceptions.RuletaCerrada;
import com.ibm.academia.ruletaapi.exceptions.RuletaNoEncontrada;
import com.ibm.academia.ruletaapi.mapper.RuletaMapper;
import com.ibm.academia.ruletaapi.models.entities.Apuesta;
import com.ibm.academia.ruletaapi.models.entities.Ruleta;
import com.ibm.academia.ruletaapi.models.entities.Sesion;
import com.ibm.academia.ruletaapi.services.ApuestaService;
import com.ibm.academia.ruletaapi.services.RuletaService;
import com.ibm.academia.ruletaapi.services.SesionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("api/v1/ruleta")
public class RuletaController {
    private final RuletaService ruletaService;
    private final SesionService sesionService;
    private final ApuestaService apuestaService;


    private final RuletaMapper ruletaMapper;

    @Autowired
    public RuletaController(RuletaService ruletaService, SesionService sesionService, ApuestaService apuestaService, RuletaMapper ruletaMapper) {
        this.ruletaService = ruletaService;
        this.sesionService = sesionService;
        this.apuestaService = apuestaService;
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

    @PutMapping("/{idRuleta}/abrir")
    public ResponseEntity<?> abrirRuleta(@PathVariable Long idRuleta){
        Optional<Ruleta> oRuleta = ruletaService.buscarPorId(idRuleta);
        if(oRuleta.isEmpty()){
            throw new NotFoundException(String.format("Ruleta con id %d no existe", idRuleta));
        }

        Ruleta ruleta = oRuleta.get();
        if (!ruleta.getEstaAbierto()){
            ruleta = ruletaService.abrirRuleta(ruleta);
            sesionService.guardar(new Sesion(ruleta));
        }

        return new ResponseEntity<>(ruleta, HttpStatus.OK);
    }

    @PutMapping("/{idRuleta}/cerrar")
    public ResponseEntity<?> cerrarRuleta(@PathVariable Long idRuleta){
        Optional<Ruleta> oRuleta = ruletaService.buscarPorId(idRuleta);
        if(oRuleta.isEmpty()){
            throw new NotFoundException(String.format("Ruleta con id %d no existe", idRuleta));
        }

        Ruleta ruleta = ruletaService.cerrarRuleta(oRuleta.get());

        List<Sesion> sesiones = ruleta.getSesiones();

        if(!sesiones.isEmpty()){
            Sesion sesionActual = sesiones.get(sesiones.size()-1);
            Sesion sesion = sesionService.cerrar(sesionActual);
            return new ResponseEntity<>(sesion.getApuestas(), HttpStatus.OK);
        }
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    @PostMapping("/apuesta/{idRuleta}")
    public ResponseEntity<?> nuevaApuesta(@PathVariable Long idRuleta,@RequestBody Apuesta apuesta){
        Optional<Ruleta> oRuleta = ruletaService.buscarPorId(idRuleta);
        if(oRuleta.isEmpty()){
            throw new RuletaNoEncontrada(String.format("Ruleta con id %d no existe", idRuleta));
        }
        Ruleta ruleta = oRuleta.get();
        if(ruleta.getEstaAbierto()){
            List<Sesion> sesiones = ruleta.getSesiones();
            Apuesta apuestaNueva = new Apuesta();
            if(sesiones.isEmpty()) {
                throw new RuntimeException("No hay sesiones");
            }
            Sesion sesionActual = sesiones.get(sesiones.size() - 1);
            apuestaNueva = apuestaService.asociarConSesion(apuesta, sesionActual);
            apuestaService.guardar(apuestaNueva);

            return new ResponseEntity<>(apuestaNueva,HttpStatus.OK);
        }else{
            throw new RuletaCerrada(String.format("Ruleta con id %d esta cerrar", idRuleta));
        }

    }


}
