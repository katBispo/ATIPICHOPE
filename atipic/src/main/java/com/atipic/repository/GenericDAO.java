package com.atipic.repository;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T, ID> {
    T save(T entity); 
    void delete(T entity); 
    Optional<T> findById(ID id);
    List<T> findAll(); 
}