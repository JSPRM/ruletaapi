package com.ibm.academia.ruletaapi.controller;

import com.ibm.academia.ruletaapi.entities.Ruleta;
import com.ibm.academia.ruletaapi.services.FiltrarBeanService;
import com.ibm.academia.ruletaapi.services.RuletaDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/ruleta")
public class RuletaController {
    private final RuletaDAO ruletaDAO;

    @Autowired
    public RuletaController(RuletaDAO ruletaDAO) {
        this.ruletaDAO = ruletaDAO;
    }

    @GetMapping("/listar")
    public ResponseEntity<MappingJacksonValue> listarTodasLasRuletas(){
        List<Ruleta> ruletas = ruletaDAO.listarTodasLasRuletas();
        String[] omitirCampos = {};
        MappingJacksonValue jacksonValue = FiltrarBeanService.filterBean(omitirCampos, "ruletaFiltro", ruletas);
        return new ResponseEntity<>(jacksonValue, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<MappingJacksonValue> crearRuleta(){
        Ruleta ruleta = ruletaDAO.agregarNuevaRuleta();
        String[] omitirCampos = {"estaAbierto","fechaModificacion"};
        MappingJacksonValue jacksonValue = FiltrarBeanService.filterBean(omitirCampos, "ruletaFiltro", ruleta);
        return new ResponseEntity<>(jacksonValue, HttpStatus.OK);
    }

    @PostMapping("/apertura")
    public ResponseEntity<MappingJacksonValue> abrirRuleta(@RequestBody Long id_ruleta){
        Ruleta ruleta = ruletaDAO.abrirRuleta(id_ruleta);
        String[] omitirCampos = {};
        MappingJacksonValue jacksonValue = FiltrarBeanService.filterBean(omitirCampos, "ruletaFiltro", ruleta);
        return new ResponseEntity<>(jacksonValue, HttpStatus.OK);
    }
}
