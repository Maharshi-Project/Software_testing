package com.testing.Converters.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PressureConverterService {

    public BigDecimal convertPressure(String fromUnit, String toUnit, BigDecimal val) {
        System.out.println("Pressure Convert Request " + " From: " + fromUnit + " To: " + toUnit + " Value: " + val+"\n");
        BigDecimal res;
        switch(fromUnit)
        {
            case "bar":
                res = val.multiply(BigDecimal.valueOf(100000));
                break;
            case "pascal":
                res = val;
                break;
            case "pound per sq inch":
                res = val.multiply(BigDecimal.valueOf(6895));
                break;
            case "standard atmosphere":
                res = val.multiply(BigDecimal.valueOf(101300));
                break;
            case "torr":
                res = val.multiply(BigDecimal.valueOf(133.3));
                break;
            default:
                return BigDecimal.valueOf(0);
        }
        return switch (toUnit) {
            case "bar" -> res.divide(BigDecimal.valueOf(100000),3,BigDecimal.ROUND_HALF_UP);
            case "pascal" -> res;
            case "pound per sq inch" -> res.divide(BigDecimal.valueOf(6895),3,BigDecimal.ROUND_HALF_UP);
            case "standard atmosphere" -> res.divide(BigDecimal.valueOf(101300),3,BigDecimal.ROUND_HALF_UP);
            case "torr" -> res.divide(BigDecimal.valueOf(133.3),3,BigDecimal.ROUND_HALF_UP);
            default -> BigDecimal.valueOf(0);
        };
    }
}
