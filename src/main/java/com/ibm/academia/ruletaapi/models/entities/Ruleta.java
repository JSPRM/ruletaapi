package com.ibm.academia.ruletaapi.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ruletas")
public class Ruleta implements Serializable {
    @Id
    @SequenceGenerator(
            name = "ruleta_sequence",
            sequenceName = "ruleta_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "ruleta_sequence"
    )
    @Column(name = "id_ruleta", updatable = false)
    private Long idRuleta;
    @Column(name = "esta_abierta", nullable = false)
    private Boolean estaAbierto;
    @Column(name = "fecha_alta", nullable = false)
    private Date fechaAlta;
    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;


    @OneToMany(mappedBy = "ruleta", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"ruleta","apuestas"})
    private List<Sesion> sesiones;

    @Serial
    private static final long serialVersionUID = -3842543004787094180L;

    public Ruleta(Boolean estaAbierto, Date fechaAlta) {
        this.estaAbierto = estaAbierto;
    }

    public Ruleta(Long idRuleta) {
        this.idRuleta = idRuleta;
    }

    public Ruleta(Boolean estaAbierto) {
        this.estaAbierto = estaAbierto;
    }

    @PrePersist
    public void generarFechaAltaRuleta(){
        this.fechaAlta = new Date();
    }

    @PostUpdate
    public void despuesActualizar(){
        this.fechaModificacion = new Date();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Ruleta ruleta = (Ruleta) o;
        return idRuleta != null && Objects.equals(idRuleta, ruleta.idRuleta);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
