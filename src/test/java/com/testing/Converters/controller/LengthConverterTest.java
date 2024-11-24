package com.testing.Converters.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import com.testing.Converters.DTO.Req;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LengthConverterTest {

    LengthConverter lengthConverter = new LengthConverter();

    @Test
    public void convertLength_ValidRequest_ReturnsOk() {

        Req req = new Req("kilometer","meter",1.0);
        double expected_1 = 1000.0;

        ResponseEntity<?> response = lengthConverter.convertLength(req);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected_1, response.getBody());
    }

    @Test
    public void convertLength_InvalidRequest_ReturnsBadRequest() {

        Req req = new Req("invalid","meter",1.0);

        double expected_1 = -1.0;

        ResponseEntity<?> response = lengthConverter.convertLength(req);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expected_1, response.getBody());
    }
}