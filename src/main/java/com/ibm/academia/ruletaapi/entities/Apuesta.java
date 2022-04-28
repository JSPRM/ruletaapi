package com.ibm.academia.ruletaapi.entities;

import java.util.Date;

public class Apuesta {
    private Long id;
    private Long idRuleta;
    private Float cantidadApostada;
    private String colorElegido;
    private Integer numeroElegido;
    private Date fechaAlta;
    private Date fechaModificacion;

    private static final long serialVersionUID = 8273282887262681200L;
}
