package com.testing.Converters.service;

import org.springframework.stereotype.Service;

@Service
public class AreaConverterService {

    public double convertArea(String fromUnit, String toUnit, double val)
    {
        System.out.println("Area Convert Request " + " From: " + fromUnit + " To: " + toUnit + " Value: " + val+"\n");
        double res;
        // default = sq foot
        switch(fromUnit)
        {
            case "sq meter":
                res = val*10.7639;
                break;
            case "sq kilometer":
                res = val*1.076e+7;
                break;
            case "sq mile":
                res = val*2.788e+7;
                break;
            case "sq yard":
                res = val*9;
                break;
            case "sq foot":
                res = val;
                break;
            case "sq inch":
                res = val*0.00694444;
                break;
            case "hectare":
                res = val*107639;
                break;
            case "acre":
                res = val*43560;
                break;
            default:
                return -1;
        }
        return switch (toUnit) {
            case "sq meter" -> res * 0.092903;
            case "sq kilometer" -> res * 9.2903e-8;
            case "sq mile" -> res * 3.587e-8;
            case "sq yard" -> res * 0.111111;
            case "sq foot" -> res;
            case "sq inch" -> res * 144;
            case "hectare" -> res * 9.2903e-6;
            case "acre" -> res * 2.2957e-5;
            default -> -1;
        };
    }
}
