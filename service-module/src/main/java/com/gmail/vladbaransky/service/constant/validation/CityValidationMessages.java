package com.gmail.vladbaransky.service.constant.validation;

import static com.gmail.vladbaransky.service.constant.validation.CityValidationRules.*;

public interface CityValidationMessages {

    String NAME_SIZE_MESSAGE = "Name length should be from " + MIN_NAME_SIZE + " to " + MAX_NAME_SIZE + ".";
    String NAME_PATTERN_MESSAGE = "Name can contain only letters.";
    String INFO_SIZE_MESSAGE = "Info length should be from " + MIN_INFO_SIZE + " to " + MAX_INFO_SIZE + ".";
}
