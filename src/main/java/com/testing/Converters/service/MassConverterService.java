package com.testing.Converters.service;

import com.fasterxml.jackson.databind.BeanProperty;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class MassConverterService {

    public BigDecimal convertMass(String fromUnit, String toUnit, BigDecimal val) throws Exception{
        System.out.println("Mass Convert Request " + " From: " + fromUnit + " To: " + toUnit + " Value: " + val + "\n");
        BigDecimal res;
        // base unit : gram
        switch (fromUnit) {
            case "tonne":
                res = val.multiply(BigDecimal.valueOf(1e+6));
                break;
            case "kilogram":
                res = val.multiply(BigDecimal.valueOf(1000));
                break;
            case "gram":
                res = val;
                break;
            case "milligram":
                res = val.multiply(BigDecimal.valueOf(0.001));
                break;
            case "microgram":
                res = val.multiply(BigDecimal.valueOf(1e-6));
                break;
            case "imperial ton":
                res = val.multiply(BigDecimal.valueOf(1.016e+6));
                break;
            case "US ton":
                res = val.multiply(BigDecimal.valueOf(907185));
                break;
            case "stone":
                res = val.multiply(BigDecimal.valueOf(6350.29));
                break;
            case "pound":
                res = val.multiply(BigDecimal.valueOf(453.592));
                break;
            case "ounce":
                res = val.multiply(BigDecimal.valueOf(28.3495));
                break;
            default:
                throw new Exception("Invalid fromUnit: " + fromUnit);
        }
        BigDecimal result =  switch (toUnit) {
            case "tonne" -> res.multiply(BigDecimal.valueOf(1e-6));
            case "kilogram" -> res.multiply(BigDecimal.valueOf(0.001));
            case "gram" -> res;
            case "milligram" -> res.multiply(BigDecimal.valueOf(1000));
            case "microgram" -> res.multiply(BigDecimal.valueOf(1e+6));
            case "imperial ton" -> res.multiply(BigDecimal.valueOf(9.8421e-7));
            case "US ton" -> res.multiply(BigDecimal.valueOf(1.1023e-6));
            case "stone" -> res.multiply(BigDecimal.valueOf(0.000157473));
            case "pound" -> res.multiply(BigDecimal.valueOf(0.00220462));
            case "ounce" -> res.multiply(BigDecimal.valueOf(0.035274));
            default -> throw new Exception("Invalid toUnit: " + toUnit);
        };
        return result.setScale(3, RoundingMode.HALF_UP);
    }
}
