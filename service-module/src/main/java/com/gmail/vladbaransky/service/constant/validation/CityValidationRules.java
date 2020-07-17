package com.gmail.vladbaransky.service.constant.validation;

public interface CityValidationRules {

    int MIN_NAME_SIZE = 2;
    int MAX_NAME_SIZE = 50;
    String NAME_PATTERN = "^[a-zA-Z]*$";
    int MIN_INFO_SIZE = 1;
    int MAX_INFO_SIZE = 1000;
}