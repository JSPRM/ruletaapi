package com.ibm.academia.ruletaapi.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Ruleta implements Serializable {
    @Id
    private Long idRuleta;
    private Boolean estaAbierto;
    private Date fechaAlta;
    private Date fechaModificacion;

    @Serial
    private static final long serialVersionUID = -3842543004787094180L;

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
