package com.gmail.vladbaransky.repository.impl;

import com.gmail.vladbaransky.repository.BotRepository;
import com.gmail.vladbaransky.repository.model.City;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import static com.gmail.vladbaransky.repository.constant.StateMessage.EMPTY_CITY_MESSAGE;

@Repository
public class BotRepositoryImpl extends GenericDAORepositoryImpl<City> implements BotRepository {

    @Override
    public City getCityByName(String cityName) {
        String queryString = "FROM " + entityClass.getSimpleName() +
                " c WHERE c.name =:city";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("city", cityName);
        Object record;
        try {
            record = query.getSingleResult();
        } catch (NoResultException e) {
            City emptyCity = new City();
            emptyCity.setInfo(EMPTY_CITY_MESSAGE);
            return emptyCity;
        }
        return (City) record;

    }
}
