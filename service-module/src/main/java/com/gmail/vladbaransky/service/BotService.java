package com.gmail.vladbaransky.service;

import com.gmail.vladbaransky.service.model.CityDTO;

public interface BotService {
    CityDTO getCityByName(String cityName);
}
