package com.ibm.academia.ruletaapi.repositories;

import com.ibm.academia.ruletaapi.models.entities.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SesionRepository extends JpaRepository<Sesion, Long> {
}
