package com.ibm.academia.ruletaapi.entities;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonFilter("ruletaFiltro")
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

    @JsonIgnore
    @OneToMany(mappedBy = "ruleta")
    private Set<Apuesta> apuestas = new HashSet<>();

    @Serial
    private static final long serialVersionUID = -3842543004787094180L;

    public Ruleta(Boolean estaAbierto, Date fechaAlta) {
        this.estaAbierto = estaAbierto;
        this.fechaAlta = fechaAlta;
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
