package com.testing.Converters.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class TemperatureConverterService {

    public BigDecimal celsiusToFahrenheit(BigDecimal celsius) {
        return celsius.multiply(BigDecimal.valueOf(9)).divide(BigDecimal.valueOf(5), RoundingMode.HALF_UP).add(BigDecimal.valueOf(32));
    }

    public BigDecimal celsiusToKelvin(BigDecimal celsius) {
        return celsius.add(BigDecimal.valueOf(273.15));
    }

    public BigDecimal fahrenheitToCelsius(BigDecimal fahrenheit) {
//        return (fahrenheit - 32) * 5/9;
        return fahrenheit.subtract(BigDecimal.valueOf(32))
                .multiply(BigDecimal.valueOf(5))
                .divide(BigDecimal.valueOf(9), 2, RoundingMode.HALF_UP);

    }

    public BigDecimal fahrenheitToKelvin(BigDecimal fahrenheit) {
//        return (fahrenheit - 32) * 5/9 + 273.15;
        return fahrenheit.subtract(BigDecimal.valueOf(32))
                .multiply(BigDecimal.valueOf(5))
                .divide(BigDecimal.valueOf(9), 2, RoundingMode.HALF_UP)
                .add(BigDecimal.valueOf(273.15));

    }

    public BigDecimal kelvinToCelsius(BigDecimal kelvin) {
//        return kelvin - 273.15;
        return kelvin.subtract(BigDecimal.valueOf(273.15));
    }

    public BigDecimal kelvinToFahrenheit(BigDecimal kelvin) {
//        return (kelvin - 273.15) * 9/5 +32;
        return kelvin.subtract(BigDecimal.valueOf(273.15))
                .multiply(BigDecimal.valueOf(9).divide(BigDecimal.valueOf(5), 2, RoundingMode.HALF_UP))
                .add(BigDecimal.valueOf(32));

    }

    public BigDecimal convertTemperature(String fromUnit, String toUnit, BigDecimal val) throws Exception {
        System.out.println("Temperature Convert Request " + " From: " + fromUnit + " To: " + toUnit + " Value: " + val+"\n");
        BigDecimal res;
        switch (fromUnit)
        {
            case "C":
                return switch (toUnit) {
                    case "F" -> celsiusToFahrenheit(val);
                    case "K" -> celsiusToKelvin(val);
                    default -> throw new Exception("Invalid toUnit: " + toUnit);
                };
            case "F":
                return switch (toUnit) {
                    case "C" -> fahrenheitToCelsius(val);
                    case "K" -> fahrenheitToKelvin(val);
                    default -> throw new Exception("Invalid toUnit: " + toUnit);
                };
            case "K":
                return switch (toUnit) {
                    case "C" -> kelvinToCelsius(val);
                    case "F" -> kelvinToFahrenheit(val);
                    default -> throw new Exception("Invalid toUnit: " + toUnit);
                };
            default:
                throw new Exception("Invalid fromUnit: " + fromUnit);
        }
    }

}
