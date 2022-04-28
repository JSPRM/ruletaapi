package com.ibm.academia.ruletaapi.controller;

import com.ibm.academia.ruletaapi.request.RuletaRegistroRequest;
import com.ibm.academia.ruletaapi.services.RuletaDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/ruleta")
public record RuletaController(RuletaDAO ruletaDAO) {
    @PostMapping
    public void registrarRulera(@RequestBody RuletaRegistroRequest ruletaRegistroRequest){
        log.info("nueva ruleta registrada {}", ruletaRegistroRequest);
        ruletaDAO.registrarRulera(ruletaRegistroRequest);
    }
}
