package com.testing.Converters.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class FrequencyConverterService {

    public BigDecimal convertFrequency(String fromUnit, String toUnit, BigDecimal val) throws Exception {
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
                throw new Exception("Invalid fromUnit " + fromUnit);
        }
        return switch (toUnit) {
            case "hertz" -> res;
            case "kilohertz" -> res.divide(BigDecimal.valueOf(1000), 2, RoundingMode.HALF_UP);
            case "megahertz" -> res.divide(BigDecimal.valueOf(1e+6),2, RoundingMode.HALF_UP);
            case "gigahertz" -> res.divide(BigDecimal.valueOf(1e+9),2, RoundingMode.HALF_UP);
            default -> throw new Exception("Invalid fromUnit " + fromUnit);
        };
    }
}
