package com.gmail.vladbaransky.web.controller;

import com.gmail.vladbaransky.service.CityService;
import com.gmail.vladbaransky.service.model.CityDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.invoke.MethodHandles;
import java.util.List;

import static com.gmail.vladbaransky.web.constant.ValidationError.ADD_ERROR_MESSAGE;
import static com.gmail.vladbaransky.web.constant.ValidationError.UPDATE_ERROR_MESSAGE;

@RestController
@RequestMapping("/")
public class CityController {
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping
    public CityDTO addCity(@RequestBody @Valid CityDTO city, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.info(ADD_ERROR_MESSAGE);
            return city;
        } else {
            return cityService.addCity(city);
        }
    }

    @GetMapping
    public List<CityDTO> getAllCity() {
        return cityService.getAllCity();
    }

    @PutMapping
    public CityDTO updateCity(@RequestBody CityDTO city, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.info(UPDATE_ERROR_MESSAGE);
            return city;
        } else {
            return cityService.updateCity(city);
        }
    }

    @DeleteMapping
    public CityDTO deleteCity(@RequestBody CityDTO city) {
        return cityService.deleteCity(city);
    }
}
