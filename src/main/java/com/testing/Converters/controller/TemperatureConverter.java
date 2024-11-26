package com.testing.Converters.controller;

import com.testing.Converters.DTO.Req;
import com.testing.Converters.service.TemperatureConverterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class TemperatureConverter {

    private final TemperatureConverterService temperatureConverterService = new TemperatureConverterService();

    @PostMapping("/convertTemperature")
    public ResponseEntity<?> convertTemperature(@RequestBody Req req) {
        String fromUnit = req.getFromUnit();
        String toUnit = req.getToUnit();
        double val = req.getValue();
        try {
            BigDecimal res = temperatureConverterService.convertTemperature(fromUnit, toUnit, BigDecimal.valueOf(val));
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
