package com.iNotes.service;

import com.iNotes.model.Type;

public interface TypeService {
    Iterable<Type> findAll();

    Type findById(Long id);

    void save(Type type);

    void remove(Long id);
}
