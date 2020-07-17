package com.gmail.vladbaransky.service.impl;

import com.gmail.vladbaransky.repository.CityRepository;
import com.gmail.vladbaransky.repository.model.City;
import com.gmail.vladbaransky.service.CityService;
import com.gmail.vladbaransky.service.model.CityDTO;
import com.gmail.vladbaransky.service.util.converter.CityConverter;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public CityDTO addCity(CityDTO cityDTO) {
        City city = CityConverter.getObjectFromDTO(cityDTO);
        List<City> allCity = cityRepository.getAllObject();
        if (!allCity.contains(city)) {
            City returnedCity = cityRepository.addObject(city);
            return CityConverter.getDTOFromObject(returnedCity);
        } else {
            CityDTO emptyCity = new CityDTO();
            emptyCity.setName(city.getName());
            emptyCity.setInfo("This city already exist.Use method update.");
            return emptyCity;
        }
    }

    @Override
    public List<CityDTO> getAllCity() {
        List<City> cities = cityRepository.getAllObject();
        return cities.stream()
                .map(CityConverter::getDTOFromObject)
                .collect(Collectors.toList());
    }

    @Override
    public CityDTO updateCity(CityDTO cityDTO) {
        City city = CityConverter.getObjectFromDTO(cityDTO);
        City updatedCity = cityRepository.updateObject(city);
        return CityConverter.getDTOFromObject(updatedCity);
    }

    @Override
    public CityDTO deleteCity(CityDTO cityDTO) {
        City city = CityConverter.getObjectFromDTO(cityDTO);
        City deletedCity = cityRepository.deleteObject(city);
        return CityConverter.getDTOFromObject(deletedCity);
    }
}

