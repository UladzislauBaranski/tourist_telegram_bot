package com.gmail.vladbaransky.repository.impl;

import com.gmail.vladbaransky.repository.GenericDAORepository;
import com.gmail.vladbaransky.repository.model.City;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public abstract class GenericDAORepositoryImpl<T> implements GenericDAORepository<T> {
    protected Class<T> entityClass;
    @PersistenceContext
    protected EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public GenericDAORepositoryImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    @Override
    public T addObject(T object) {
        entityManager.persist(object);
        return object;
    }

    @Override
    public List<T> getAllObject() {
        String queryString = "from " + entityClass.getName();
        Query query = entityManager.createQuery(queryString);
        return query.getResultList();
    }

}
