package com.ibm.academia.ruletaapi.services;

import java.util.Optional;

public interface GenericoService<E>{
    Optional<E> buscarPorId(Long id);
    E guardar(E entidad);
    Iterable<E> buscarTodos();
    void eliminarPorId(Long id);
}