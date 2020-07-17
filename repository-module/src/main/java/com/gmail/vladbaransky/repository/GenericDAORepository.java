package com.gmail.vladbaransky.repository;

import java.util.List;

public interface GenericDAORepository<T> {
    T addObject(T object);

    List<T> getAllObject();

}
