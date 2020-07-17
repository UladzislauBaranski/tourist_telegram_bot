package com.gmail.vladbaransky.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.vladbaransky.service.CityService;
import com.gmail.vladbaransky.service.model.CityDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CityController.class)
@AutoConfigureMockMvc
class CityControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @MockBean
    private CityService cityService;

    //----------------------addCity---------------------------
    @Test
    void addCity_returnStatusOk() throws Exception {
        CityDTO city = getCity();
        String content = objectMapper.writeValueAsString(city);
        mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content))
                .andExpect(status().isOk());
    }

    @Test
    void addCity_callBusinessLogic() throws Exception {
        CityDTO city = getCity();
        String content = objectMapper.writeValueAsString(city);
        mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content))
                .andExpect(status().isOk());
        verify(cityService, times(1)).addCity(eq(city));
    }

    @Test
    void addCity_returnCity() throws Exception {
        CityDTO city = getCity();
        CityDTO addedCity = getAddedCity();
        String content = objectMapper.writeValueAsString(city);

        when(cityService.addCity(city)).thenReturn(addedCity);
        MvcResult result = mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content)).andReturn();
        verify(cityService, times(1)).addCity(eq(city));

        String expectedReturnedContent = result.getResponse().getContentAsString();
        String returnedContent = objectMapper.writeValueAsString(addedCity);
        Assertions.assertThat(expectedReturnedContent).isEqualTo(returnedContent);

    }

    //---------------------------getAllCity----------------------------------
    @Test
    void getAllCity_returnStatusOk() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllCity_callBusinessLogic() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
        verify(cityService, times(1)).getAllCity();
    }

    @Test
    void getAllCity_returnAllCity() throws Exception {
        List<CityDTO> addedCities = getAddedCityList();
        when(cityService.getAllCity()).thenReturn(addedCities);
        MvcResult result = mockMvc.perform(get("/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        verify(cityService, times(1)).getAllCity();

        String expectedReturnedContent = result.getResponse().getContentAsString();
        String returnedContent = objectMapper.writeValueAsString(addedCities);
        Assertions.assertThat(expectedReturnedContent).isEqualTo(returnedContent);
    }

    //---------------------------updateCity---------------------------
    @Test
    void updateCity_returnStatusOk() throws Exception {
        CityDTO city = getCity();
        String content = objectMapper.writeValueAsString(city);
        mockMvc.perform(put("/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content))
                .andExpect(status().isOk());
    }

    @Test
    void updateCity_callBusinessLogic() throws Exception {
        CityDTO city = getCity();
        String content = objectMapper.writeValueAsString(city);
        mockMvc.perform(put("/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content))
                .andExpect(status().isOk());
        verify(cityService, times(1)).updateCity(eq(city));
    }

    @Test
    void updateCity_returnCity() throws Exception {
        CityDTO city = getCity();
        CityDTO updatedCity = getAddedCity();
        String content = objectMapper.writeValueAsString(city);

        when(cityService.updateCity(city)).thenReturn(updatedCity);
        MvcResult result = mockMvc.perform(put("/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content)).andReturn();
        verify(cityService, times(1)).updateCity(eq(city));

        String expectedReturnedContent = result.getResponse().getContentAsString();
        String returnedContent = objectMapper.writeValueAsString(updatedCity);
        Assertions.assertThat(expectedReturnedContent).isEqualTo(returnedContent);
    }

    //---------------------------deleteCity-----------------------------------------
    @Test
    void deleteCity_returnStatusOk() throws Exception {
        CityDTO city = getCity();
        String content = objectMapper.writeValueAsString(city);
        mockMvc.perform(delete("/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCity_callBusinessLogic() throws Exception {
        CityDTO city = getCity();
        String content = objectMapper.writeValueAsString(city);
        mockMvc.perform(delete("/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content))
                .andExpect(status().isOk());
        verify(cityService, times(1)).deleteCity(eq(city));
    }

    @Test
    void deleteCity_returnCity() throws Exception {
        CityDTO city = getCity();
        CityDTO deletedCity = getAddedCity();
        String content = objectMapper.writeValueAsString(city);

        when(cityService.deleteCity(city)).thenReturn(deletedCity);
        MvcResult result = mockMvc.perform(delete("/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content)).andReturn();
        verify(cityService, times(1)).deleteCity(eq(city));

        String expectedReturnedContent = result.getResponse().getContentAsString();
        String returnedContent = objectMapper.writeValueAsString(deletedCity);
        Assertions.assertThat(expectedReturnedContent).isEqualTo(returnedContent);
    }

    private CityDTO getCity() {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setName("Name");
        cityDTO.setInfo("Info");
        return cityDTO;
    }

    private CityDTO getAddedCity() {
        CityDTO city = getCity();
        city.setId(1L);
        return city;
    }

    private List<CityDTO> getAddedCityList() {
        List<CityDTO> cities = new ArrayList<>();
        for (Long i = 1L; i <= 10L; i++) {
            CityDTO city = getCity();
            city.setId(i);
            cities.add(city);
        }
        return cities;
    }
}