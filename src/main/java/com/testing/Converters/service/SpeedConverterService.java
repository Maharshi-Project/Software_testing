package com.testing.Converters.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class SpeedConverterService {
    public BigDecimal convertSpeed(String fromUnit, String toUnit, BigDecimal val) throws Exception {
        System.out.println("Speed Convert Request " + " From: " + fromUnit + " To: " + toUnit + " Value: " + val+"\n");
        BigDecimal res;
        // Default : Meter per second
        switch(fromUnit)
        {
            case "mile per hour":
                res = val.divide(BigDecimal.valueOf(2.237), 10, RoundingMode.HALF_UP);
                break;
            case "foot per second":
                res = val.divide(BigDecimal.valueOf(3.281), 10, RoundingMode.HALF_UP);
                break;
            case "meter per second":
                res = val;
                break;
            case "kilometer per hour":
                res = val.divide(BigDecimal.valueOf(3.6), 10, RoundingMode.HALF_UP);
                break;
            case "knot":
                res = val.divide(BigDecimal.valueOf(1.944), 10, RoundingMode.HALF_UP);
                break;
            default:
                throw new Exception("Invalid fromUnit: " + fromUnit);
        }
        BigDecimal result = switch (toUnit) {
            case "mile per hour" -> res.multiply(BigDecimal.valueOf(2.237));
            case "foot per second" -> res.multiply(BigDecimal.valueOf(3.281));
            case "meter per second" -> res;
            case "kilometer per hour" -> res.multiply(BigDecimal.valueOf(3.6));
            case "knot" -> res.multiply(BigDecimal.valueOf(1.944));
            default -> throw new Exception("Invalid toUnit: " + toUnit);
        };
        return result.setScale(3, RoundingMode.HALF_UP);
    }
}
