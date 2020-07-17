package com.gmail.vladbaransky.repository;

import com.gmail.vladbaransky.repository.model.City;

public interface BotRepository extends GenericDAORepository<City> {
    City getCityByName(String cityName);
}
