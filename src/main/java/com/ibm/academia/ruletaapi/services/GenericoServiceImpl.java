package com.ibm.academia.ruletaapi.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class GenericoServiceImpl<E, R extends JpaRepository<E, Long>> implements GenericoService<E> {


    protected final R repository;

    public GenericoServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<E> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public E guardar(E entidad) {
        return repository.save(entidad);
    }

    @Override
    public Iterable<E> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public void eliminarPorId(Long id) {
        repository.deleteById(id);
    }
}
