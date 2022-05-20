package com.ibm.academia.ruletaapi.models.entities;

import com.ibm.academia.ruletaapi.enums.ColorElegido;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "apuestas")
public class Apuesta implements Serializable {
    @Id
    @SequenceGenerator(
            name = "apuesta_sequence",
            sequenceName = "apuesta_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "apuesta_sequence"
    )
    @Column(name = "id_apuesta", updatable = false)
    private Long idApuesta;

    @Column(name = "cantidad_apostada", nullable = false)
    private Float cantidadApostada;

    @Column(name = "color_elegido")
    @Enumerated(EnumType.STRING)
    private ColorElegido colorElegido;

    @Column(name = "numero_elegido")
    private Integer numeroElegido;

    @Column(name = "fecha_alta", nullable = false)
    private Date fechaAlta;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_ruleta", referencedColumnName = "id_ruleta", foreignKey = @ForeignKey(name = "FK_RULETA_ID"))
    private Ruleta ruleta;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_sesion", referencedColumnName = "id_sesion", foreignKey = @ForeignKey(name = "FK_SESION_ID"))
    private Sesion sesion;

    public Apuesta(Ruleta ruleta, Float cantidadApostada, ColorElegido colorElegido) {
        this.cantidadApostada = cantidadApostada;
        this.colorElegido = colorElegido;
        this.ruleta = ruleta;
    }

    public Apuesta(Ruleta ruleta, Float cantidadApostada, Integer numeroElegido) {
        this.cantidadApostada = cantidadApostada;
        this.numeroElegido = numeroElegido;
        this.ruleta = ruleta;
    }

    @PrePersist
    public void generarFechaAltaRuleta(){
        this.fechaAlta = new Date();
    }

    @Serial
    private static final long serialVersionUID = 8273282887262681200L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Apuesta apuesta = (Apuesta) o;
        return idApuesta != null && Objects.equals(idApuesta, apuesta.idApuesta);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
