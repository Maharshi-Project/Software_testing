package com.testing.Converters.controller;

import com.testing.Converters.DTO.Req;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureConverterTest {

    private final TemperatureConverter temperatureConverter = new TemperatureConverter();

    @Test
    public void convertPressure_ValidRequest_ReturnsOk() {

        Req req = new Req("C","F",0);
        BigDecimal expected_1 = new BigDecimal("32.0");
        ResponseEntity<?> response = temperatureConverter.convertTemperature(req);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected_1, response.getBody());
    }

    @Test
    public void convertPressure_InvalidRequest_ReturnsBadRequest() {

        Req req = new Req("invalid","F",0);

        ResponseEntity<?> response = temperatureConverter.convertTemperature(req);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid fromUnit: "+req.getFromUnit(), response.getBody());
    }

}