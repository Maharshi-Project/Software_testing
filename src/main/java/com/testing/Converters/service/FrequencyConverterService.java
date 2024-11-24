package com.testing.Converters.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FrequencyConverterService {

    public BigDecimal convertFrequency(String fromUnit, String toUnit, BigDecimal val) {
        System.out.println("Frequency Convert Request " + " From: " + fromUnit + " To: " + toUnit + " Value: " + val+"\n");
        BigDecimal res;
        // Default : hertz
        switch(fromUnit)
        {
            case "hertz":
                res = val;
                break;
            case "kilohertz":
                res = val.multiply(BigDecimal.valueOf(1000));
                break;
            case "megahertz":
                res = val.multiply(BigDecimal.valueOf(1e+6));
                break;
            case "gigahertz":
                res = val.multiply(BigDecimal.valueOf(1e+9));
                break;
            default:
                return BigDecimal.ZERO;
        }
        return switch (toUnit) {
            case "hertz" -> res;
            case "kilohertz" -> res.divide(BigDecimal.valueOf(1000));
            case "megahertz" -> res.divide(BigDecimal.valueOf(1e+6));
            case "gigahertz" -> res.divide(BigDecimal.valueOf(1e+9));
            default -> BigDecimal.ZERO;
        };
    }
}
