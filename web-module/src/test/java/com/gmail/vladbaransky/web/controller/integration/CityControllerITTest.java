package com.gmail.vladbaransky.web.controller.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.vladbaransky.service.model.CityDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource("/application-integration.properties")
public class CityControllerITTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Sql("/data.sql")
    public void addCity_returnCity() throws Exception {
        CityDTO city = getCity();
        String content = objectMapper.writeValueAsString(city);
        mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("name", is("Minsk")).exists())
                .andExpect(jsonPath("info", is("info")).exists());
    }

    @Test
    @Sql("/data.sql")
    public void getAllCity_returnAllCity() throws Exception {
        mockMvc.perform(get("/")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$[0].name", is("name")).exists())
                .andExpect(jsonPath("$[0].info", is("info")).exists());
    }

    @Test
    @Sql("/data.sql")
    public void updateCity_returnCity() throws Exception {
        CityDTO city = getCity();
        String content = objectMapper.writeValueAsString(city);
        mockMvc.perform(put("/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("name", is("Minsk")).exists())
                .andExpect(jsonPath("info", is("info")).exists());
    }

    @Test
    @Sql("/data.sql")
    public void deleteCity_returnCity() throws Exception {
        CityDTO city = getCity();
        String content = objectMapper.writeValueAsString(city);
        mockMvc.perform(delete("/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("name", is("Minsk")).exists())
                .andExpect(jsonPath("info", is("info")).exists());
    }

    private CityDTO getCity() {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setName("Minsk");
        cityDTO.setInfo("info");
        return cityDTO;
    }
}
