package com.gmail.vladbaransky.service;

import com.gmail.vladbaransky.service.model.CityDTO;

import java.util.List;

public interface CityService {

    CityDTO addCity(CityDTO cityDTO);

    List<CityDTO> getAllCity();

    CityDTO updateCity(CityDTO city);

    CityDTO deleteCity(CityDTO city);
}
