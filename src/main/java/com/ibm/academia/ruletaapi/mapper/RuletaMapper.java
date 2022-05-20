package com.ibm.academia.ruletaapi.mapper;

import com.ibm.academia.ruletaapi.models.dto.NuevaRuletaDTO;
import com.ibm.academia.ruletaapi.models.entities.Ruleta;
import org.springframework.stereotype.Component;

@Component
public class RuletaMapper {
    public NuevaRuletaDTO mapRuleta(Ruleta ruleta){
        return new NuevaRuletaDTO(
                ruleta.getIdRuleta(),
                ruleta.getFechaAlta()
        );
    }
}
