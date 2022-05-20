package com.ibm.academia.ruletaapi.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sesiones")
public class Sesion implements Serializable {
    @Id
    @SequenceGenerator(
            name = "sesion_sequence",
            sequenceName = "sesion_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "sesion_sequence"
    )
    @Column(name = "id_sesion", updatable = false)
    private Long idSesion;

    @Column(name = "fecha_inicio", nullable = false, updatable = false)
    private Date fechaInicio;

    @Column(name = "fecha_cierre")
    private Date fechaCierre;

    @OneToMany(mappedBy = "sesion", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"sesion","apuestas","ruleta"})
    private List<Apuesta> apuestas;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="id_ruleta", referencedColumnName = "id_ruleta", foreignKey = @ForeignKey(name = "FK_RULETA_ID"))
    private Ruleta ruleta;

    public Sesion(Ruleta ruleta) {
        this.ruleta = ruleta;
    }

    @Serial
    private static final long serialVersionUID = 1216115897232187492L;

    @PrePersist
    public void antesPersistir(){
        this.fechaInicio = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Sesion sesion = (Sesion) o;
        return idSesion != null && Objects.equals(idSesion, sesion.idSesion);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
