package com.testing.Converters.controller;

import com.testing.Converters.DTO.Req;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PressureConverterTest {

    private final PressureConverter pressureConverter = new PressureConverter();

    @Test
    public void convertPressure_ValidRequest_ReturnsOk() {

        Req req = new Req("bar","pascal",1.0);
        BigDecimal expected_1 = new BigDecimal("100000.0");
        ResponseEntity<?> response = pressureConverter.convertPressure(req);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected_1, response.getBody());
    }

    @Test
    public void convertPressure_InvalidRequest_ReturnsBadRequest() {

        Req req = new Req("invalid","gram",1.0);

        ResponseEntity<?> response = pressureConverter.convertPressure(req);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid fromUnit: "+req.getFromUnit(), response.getBody());
    }

}