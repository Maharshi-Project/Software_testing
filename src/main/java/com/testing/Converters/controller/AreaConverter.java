package com.testing.Converters.controller;

import com.testing.Converters.DTO.Req;
import com.testing.Converters.service.AreaConverterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AreaConverter {

    private final AreaConverterService areaConverterService = new AreaConverterService();


    @PostMapping("/areaConverter")
    public ResponseEntity<?> convertArea(@RequestBody Req req)
    {
        String fromUnit = req.getFromUnit();
        String toUnit = req.getToUnit();
        double val = req.getValue();
        double res = areaConverterService.convertArea(fromUnit, toUnit, val);
        if(res == -1)
        {
            return new ResponseEntity<>(-1.0,HttpStatus.BAD_REQUEST);
        }
        else
        {
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }
}
