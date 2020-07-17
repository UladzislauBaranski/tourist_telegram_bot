package com.gmail.vladbaransky.repository;


import com.gmail.vladbaransky.repository.model.City;

public interface CityRepository extends GenericDAORepository<City> {
    City updateObject(City city);

    City deleteObject(City city);
}
