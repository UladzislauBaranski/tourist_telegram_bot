package com.gmail.vladbaransky.service;

import com.gmail.vladbaransky.repository.BotRepository;
import com.gmail.vladbaransky.repository.model.City;
import com.gmail.vladbaransky.service.impl.BotServiceImpl;
import com.gmail.vladbaransky.service.model.CityDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BotServiceImplTest {
    private BotService botService;

    @Mock
    private BotRepository botRepository;

    @BeforeEach
    public void setup() {
        this.botService = new BotServiceImpl(botRepository);
    }

    @Test
    void getCityByName_returnCity() {
        String cityName = "Name";
        City addedCity = getAddedCity();

        when(botRepository.getCityByName(cityName)).thenReturn(addedCity);
        CityDTO returnedCity = botService.getCityByName(cityName);
        verify(botRepository, times(1)).getCityByName(cityName);
        getAssertion(returnedCity, addedCity);
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

    private City getAddedCity() {
        City city = getCity();
        city.setId(1L);
        return city;
    }
}