package com.testing.Converters.controller;

import com.testing.Converters.DTO.Req;
import com.testing.Converters.service.EnergyConverterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class EnergyConverter {

    private final EnergyConverterService energyConverterService;

    public EnergyConverter(EnergyConverterService energyConverterService) {
        this.energyConverterService = energyConverterService;
    }

    @PostMapping("/energyConverter")
    public ResponseEntity<?> convertEnergy(@RequestBody Req req)
    {
        String fromUnit = req.getFromUnit();
        String toUnit = req.getToUnit();
        double val = req.getValue();
        BigDecimal res = energyConverterService.convertEnergy(fromUnit, toUnit, BigDecimal.valueOf(val));
        if(res.equals(-1))
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else
        {
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }

}
