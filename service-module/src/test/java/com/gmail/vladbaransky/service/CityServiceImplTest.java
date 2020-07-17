package com.gmail.vladbaransky.service;

import com.gmail.vladbaransky.repository.CityRepository;
import com.gmail.vladbaransky.repository.model.City;
import com.gmail.vladbaransky.service.impl.CityServiceImpl;
import com.gmail.vladbaransky.service.model.CityDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CityServiceImplTest {
    private CityService cityService;
    @Mock
    private CityRepository cityRepository;

    @BeforeEach
    public void setup() {
        this.cityService = new CityServiceImpl(cityRepository);
    }

    @Test
    void addCity_returnCity() {
        City city = getCity();
        City addedCity = getAddedCity();
        CityDTO cityDTO = getCityDTO();

        when(cityRepository.addObject(city)).thenReturn(addedCity);
        CityDTO returnedCity = cityService.addCity(cityDTO);
        verify(cityRepository, times(1)).addObject(city);
        getAssertion(returnedCity, addedCity);
    }

    @Test
    void getAllCity_returnAllCity() {
        List<City> addedCityList = getAddedCityList();
        when(cityRepository.getAllObject()).thenReturn(addedCityList);
        List<CityDTO> returnedCities = cityService.getAllCity();
        verify(cityRepository, times(1)).getAllObject();
        for (int i = 0; i < returnedCities.size(); i++) {
            getAssertion(returnedCities.get(i), addedCityList.get(i));
        }
    }

    @Test
    void updateCity_returnCity() {
        City city = getCity();
        City updatedCity = getAddedCity();
        CityDTO cityDTO = getCityDTO();

        when(cityRepository.updateObject(city)).thenReturn(updatedCity);
        CityDTO returnedCity = cityService.updateCity(cityDTO);
        verify(cityRepository, times(1)).updateObject(city);
        getAssertion(returnedCity, updatedCity);
    }

    @Test
    void deleteCity_returnCity() {
        City city = getCity();
        City deletedCity = getAddedCity();
        CityDTO cityDTO = getCityDTO();

        when(cityRepository.deleteObject(city)).thenReturn(deletedCity);
        CityDTO returnedCity = cityService.deleteCity(cityDTO);
        verify(cityRepository, times(1)).deleteObject(city);
        getAssertion(returnedCity, deletedCity);
    }

    private void getAssertion(CityDTO returnedCity, City city) {
        assertThat(returnedCity).isNotNull();
        assertThat(returnedCity.getId()).isEqualTo(city.getId());
        assertThat(returnedCity.getName()).isEqualTo(city.getName());
        assertThat(returnedCity.getInfo()).isEqualTo(city.getInfo());
    }

    private City getCity() {
        City city = new City();
        city.setName("Name");
        city.setInfo("Info");
        return city;
    }

    private CityDTO getCityDTO() {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setName("Name");
        cityDTO.setInfo("Info");
        return cityDTO;
    }

    private City getAddedCity() {
        City city = getCity();
        city.setId(1L);
        return city;
    }

    private List<City> getAddedCityList() {
        List<City> cities = new ArrayList<>();
        for (Long i = 1L; i <= 10L; i++) {
            City city = getCity();
            city.setId(i);
            cities.add(city);
        }
        return cities;
    }
}