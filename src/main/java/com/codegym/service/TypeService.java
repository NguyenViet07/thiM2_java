package com.codegym.service;

import com.codegym.model.Type;

public interface TypeService extends GeneralService<Type> {
    Iterable<Type> findAll();
}
