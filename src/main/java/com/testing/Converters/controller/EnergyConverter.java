package com.testing.Converters.controller;

import com.testing.Converters.DTO.Req;
import com.testing.Converters.service.EnergyConverterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Objects;

@RestController
public class EnergyConverter {

    private EnergyConverterService energyConverterService = new EnergyConverterService();

    @PostMapping("/energyConverter")
    public ResponseEntity<?> convertEnergy(@RequestBody Req req)
    {
        String fromUnit = req.getFromUnit();
        String toUnit = req.getToUnit();
        double val = req.getValue();
        BigDecimal res = energyConverterService.convertEnergy(fromUnit, toUnit, BigDecimal.valueOf(val));
        if (res.compareTo(BigDecimal.valueOf(-1.0)) != 0) {
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(-1.0,HttpStatus.BAD_REQUEST);
        }
    }

}
