package com.testing.Converters.controller;

import com.testing.Converters.DTO.Req;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AreaConverterTest {

    AreaConverter areaConverter = new AreaConverter();

    @Test
    public void convertArea_ValidRequest_ReturnsOk(){
        Req req = new Req("sq meter","sq foot",1.0);
        BigDecimal expected_1 = BigDecimal.valueOf(10.7639);

        ResponseEntity<?> response = areaConverter.convertArea(req);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, expected_1.compareTo(BigDecimal.valueOf((double) response.getBody())));
    }

    @Test
    public void convertEnergy_InvalidRequest_ReturnsBadRequest(){
        Req req = new Req("invalid","joule",1.0);
        BigDecimal expected_1 = BigDecimal.valueOf(-1.0);

        ResponseEntity<?> response = areaConverter.convertArea(req);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(0, expected_1.compareTo(BigDecimal.valueOf((double) response.getBody())));
    }


}