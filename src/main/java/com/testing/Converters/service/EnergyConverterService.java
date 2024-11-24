package com.testing.Converters.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class EnergyConverterService {

    public BigDecimal convertEnergy(String fromUnit, String toUnit, BigDecimal val) {

        System.out.println("Energy Convert Request " + " From: " + fromUnit + " To: " + toUnit + " Value: " + val+"\n");
        BigDecimal res;
        switch(fromUnit)
        {
            case "joule":
                res = val;
                break;
            case "kilojoule":
                res = val.multiply(BigDecimal.valueOf(1000));
                break;
            case "gram calorie":
                res = val.multiply(BigDecimal.valueOf(4.184));
                break;
            case "kilocalorie":
                res = val.multiply(BigDecimal.valueOf(4184));
                break;
            case "watt hour":
                res = val.multiply(BigDecimal.valueOf(3600));
                break;
            case "kilowatt hour":
                res = val.multiply(BigDecimal.valueOf(3.6e+6));
                break;
            case "electronvolt":
                res = val.divide(BigDecimal.valueOf(6.242e+18));
                break;
            case "therm":
                res = val.multiply(BigDecimal.valueOf(1.055e+8));
                break;
            case "foot pound":
                res = val.multiply(BigDecimal.valueOf(1.356));
                break;
            default:
                return BigDecimal.valueOf(-1);
        }
        return switch (toUnit) {
            case "joule" -> res;
            case "kilojoule" -> res.divide(BigDecimal.valueOf(1000));
            case "gram calorie" -> res.divide(BigDecimal.valueOf(4.184));
            case "kilocalorie" -> res.divide(BigDecimal.valueOf(4184));
            case "watt hour" -> res.divide(BigDecimal.valueOf(3600));
            case "kilowatt hour" -> res.divide(BigDecimal.valueOf(3.6e+6));
            case "electronvolt" -> res.multiply(BigDecimal.valueOf(6.242e+18));
            case "therm" -> res.divide(BigDecimal.valueOf(1.055e+8));
            case "foot pound" -> res.divide(BigDecimal.valueOf(1.356));
            default -> BigDecimal.valueOf(-1);
        };
    }
}