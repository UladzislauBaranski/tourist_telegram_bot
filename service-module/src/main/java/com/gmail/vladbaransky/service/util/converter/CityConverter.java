package com.gmail.vladbaransky.service.util.converter;

import com.gmail.vladbaransky.repository.model.City;
import com.gmail.vladbaransky.service.model.CityDTO;

public class CityConverter {
    public static City getObjectFromDTO(CityDTO cityDTO) {
        City city = new City();
        city.setId(cityDTO.getId());
        city.setName(cityDTO.getName());
        city.setInfo(cityDTO.getInfo());
        return city;
    }

    public static CityDTO getDTOFromObject(City city) {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(city.getId());
        cityDTO.setName(city.getName());
        cityDTO.setInfo(city.getInfo());
        return cityDTO;
    }
}
