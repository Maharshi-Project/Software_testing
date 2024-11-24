package com.testing.Converters.service;

import org.springframework.stereotype.Service;

@Service
public class LengthConverterService {

    public double convertLength(String fromUnit, String toUnit, double val)
    {
        System.out.println("Length Convert Request " + " From: " + fromUnit + " To: " + toUnit + " Value: " + val+"\n");
        double res;
        switch(fromUnit)
        {
            case "meter":
                res = val;
                break;
            case "centimeter":
                res = val/100;
                break;
            case "millimeter":
                res = val/1000;
                break;
            case "kilometer":
                res = val*1000;
                break;
            case "mile":
                res = val*1609.34;
                break;
            case "yard":
                res = val*0.9144;
                break;
            case "foot":
                res = val*0.3048;
                break;
            case "inch":
                res = val*0.0254;
                break;
            case "nautical mile":
                res = val*1852;
                break;
            default:
                return -1;
        }
        return switch (toUnit) {
            case "meter" -> res;
            case "centimeter" -> res * 100;
            case "millimeter" -> res * 1000;
            case "kilometer" -> res * 0.001;
            case "mile" -> res * 0.000621371;
            case "yard" -> res * 1.09361;
            case "foot" -> res * 3.28084;
            case "inch" -> res * 39.3701;
            case "nautical mile" -> res * 0.000539957;
            default -> -1;
        };
    }
}
