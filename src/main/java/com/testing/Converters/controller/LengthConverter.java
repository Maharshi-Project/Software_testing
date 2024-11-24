package com.testing.Converters.controller;

import com.testing.Converters.DTO.Req;
import com.testing.Converters.service.LengthConverterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LengthConverter {

    private final LengthConverterService lengthConverterService;

    public LengthConverter(LengthConverterService lengthConverterService) {
        this.lengthConverterService = lengthConverterService;
    }

    @PostMapping("/lengthConverter")
    public ResponseEntity<?> convertLength(@RequestBody Req req)
    {
            String fromUnit = req.getFromUnit();
            String toUnit = req.getToUnit();
            double val = req.getValue();
            double res = lengthConverterService.convertLength(fromUnit, toUnit, val);
            if(res == -1)
            {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            else
            {
                return new ResponseEntity<>(res, HttpStatus.OK);
            }
    }

}
