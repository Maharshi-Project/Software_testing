package com.testing.Converters.controller;

import com.testing.Converters.DTO.Req;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnergyConverterTest {

    EnergyConverter energyConverter = new EnergyConverter();

    @Test
    public void convertEnergy_ValidRequest_ReturnsOk(){
        Req req = new Req("kilojoule","joule",1.0);
        BigDecimal expected_1 = BigDecimal.valueOf(1000.0);

        ResponseEntity<?> response = energyConverter.convertEnergy(req);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, expected_1.compareTo((BigDecimal) response.getBody()));
    }

    @Test
    public void convertEnergy_InvalidRequest_ReturnsBadRequest(){
        Req req = new Req("invalid","joule",1.0);
        BigDecimal expected_1 = BigDecimal.valueOf(-1.0);

        ResponseEntity<?> response = energyConverter.convertEnergy(req);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(0, expected_1.compareTo(BigDecimal.valueOf((double) response.getBody())));
    }
}