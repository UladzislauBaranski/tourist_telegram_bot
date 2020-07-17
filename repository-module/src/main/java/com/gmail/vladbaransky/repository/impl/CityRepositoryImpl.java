package com.gmail.vladbaransky.repository.impl;

import com.gmail.vladbaransky.repository.CityRepository;
import com.gmail.vladbaransky.repository.constant.StateMessage;
import com.gmail.vladbaransky.repository.model.City;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.lang.invoke.MethodHandles;

@Repository
public class CityRepositoryImpl extends GenericDAORepositoryImpl<City> implements CityRepository {
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public City updateObject(City city) {
        String hql = "UPDATE " + entityClass.getSimpleName() + " c SET c.info=:infoString WHERE c.name=:nameString";
        Query query = entityManager.createQuery(hql);
        query.setParameter("infoString", city.getInfo());
        query.setParameter("nameString", city.getName());
        int result = query.executeUpdate();
        if (result == 1) {
            logger.info(StateMessage.UPDATED_STATE_MESSAGE);
        } else if (result == 0) {
            logger.info(StateMessage.DO_NOT_UPDATED_STATE_MESSAGE);
        }
        return city;
    }

    @Override
    public City deleteObject(City city) {
        String hql = "DELETE FROM " + entityClass.getSimpleName() + " c WHERE c.name=:nameString";
        Query query = entityManager.createQuery(hql);
        query.setParameter("nameString", city.getName());
        int result = query.executeUpdate();
        if (result == 1) {
            logger.info(StateMessage.DELETED_STATE_MESSAGE);
        } else if (result == 0) {
            logger.info(StateMessage.DO_NOT_DELETED_STATE_MESSAGE);
        }
        return city;
    }
}
