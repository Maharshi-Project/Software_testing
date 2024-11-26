package com.testing.Converters.controller;

import com.testing.Converters.DTO.Req;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MassConverterTest {

    private final MassConverter massConverter = new MassConverter();

    @Test
    public void convertMass_ValidRequest_ReturnsOk() {

        Req req = new Req("kilogram","gram",1.0);
        BigDecimal expected_1 = new BigDecimal("1000.0");
        ResponseEntity<?> response = massConverter.convertMass(req);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, new BigDecimal(String.valueOf(response.getBody())).compareTo(expected_1));
    }

    @Test
    public void convertMass_InvalidRequest_ReturnsBadRequest() {

        Req req = new Req("invalid","gram",1.0);

        ResponseEntity<?> response = massConverter.convertMass(req);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid fromUnit: "+req.getFromUnit(), response.getBody());
    }

}