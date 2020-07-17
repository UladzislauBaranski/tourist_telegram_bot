package com.gmail.vladbaransky.service.impl;

import com.gmail.vladbaransky.repository.BotRepository;
import com.gmail.vladbaransky.repository.model.City;
import com.gmail.vladbaransky.service.BotService;
import com.gmail.vladbaransky.service.model.CityDTO;
import com.gmail.vladbaransky.service.util.converter.CityConverter;
import org.springframework.stereotype.Service;

@Service
public class BotServiceImpl implements BotService {
    private final BotRepository botRepository;

    public BotServiceImpl(BotRepository botRepository) {
        this.botRepository = botRepository;
    }

    @Override
    public CityDTO getCityByName(String cityName) {
        City city = botRepository.getCityByName(cityName);
        return CityConverter.getDTOFromObject(city);
    }
}
