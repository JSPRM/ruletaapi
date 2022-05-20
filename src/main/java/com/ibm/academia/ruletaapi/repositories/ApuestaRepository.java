package com.ibm.academia.ruletaapi.repositories;
import com.ibm.academia.ruletaapi.models.entities.Apuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApuestaRepository extends JpaRepository<Apuesta, Long>{
}
