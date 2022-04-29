package com.ibm.academia.ruletaapi.repositories;

import com.ibm.academia.ruletaapi.entities.Ruleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuletaRepository extends JpaRepository<Ruleta, Long> {
}
