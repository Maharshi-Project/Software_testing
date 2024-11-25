package com.testing.Converters.service;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AreaConverterServiceTest {

    private final AreaConverterService areaConverterService = new AreaConverterService();

    @Test
    public void testConvertSqMeterToSqFoot() {
        double result = areaConverterService.convertArea("sq meter", "sq foot", 1);
        assertEquals(10.7639, result, 0.0001);
    }

    @Test
    public void testConvertSqKilometerToSqMile() {
        double result = areaConverterService.convertArea("sq kilometer", "sq mile", 1);
        assertEquals(1.076e+7 * 3.587e-8, result, 0.0001);
    }

    @Test
    public void testConvertAcreToHectare() {
        double result = areaConverterService.convertArea("acre", "hectare", 1);
        assertEquals(43560 * 9.2903e-6, result, 0.0001);
    }

    @Test
    public void testConvertSqInchToSqYard() {
        double result = areaConverterService.convertArea("sq inch", "sq yard", 144);
        assertEquals(0.00694444 * 144 * 0.111111, result, 0.0001);
    }

    @Test
    public void testBoundaryCaseZeroValue() {
        double result = areaConverterService.convertArea("sq meter", "sq foot", 0);
        assertEquals(0, result);
    }

    @Test
    public void testNegativeValueConversion() {
        double result = areaConverterService.convertArea("sq foot", "sq inch", -5);
        assertEquals(-5 * 144, result);
    }

    @Test
    public void testConvertHectareToSqKilometer() {
        double result = areaConverterService.convertArea("hectare", "sq kilometer", 1);
        assertEquals(107639 * 9.2903e-8, result, 0.0001);
    }

//     Valid conversions
    @Test
    public void testSquareMeterToSquareFoot() {
        double result = areaConverterService.convertArea("sq meter", "sq foot", 10);
        assertEquals(107.639, result, 0.001);
    }

    @Test
    public void testSquareKilometerToSquareFoot() {
        double result = areaConverterService.convertArea("sq kilometer", "sq foot", 1);
        assertEquals(10760000, result, 0.001);
    }

    @Test
    public void testSquareMileToSquareFoot() {
        double result = areaConverterService.convertArea("sq mile", "sq foot", 1);
        assertEquals(27880000, result, 0.001);
    }

    @Test
    public void testSquareYardToSquareFoot() {
        double result = areaConverterService.convertArea("sq yard", "sq foot", 10);
        assertEquals(90, result, 0.001);
    }

    @Test
    public void testSquareInchToSquareFoot() {
        double result = areaConverterService.convertArea("sq inch", "sq foot", 100);
        assertEquals(0.694444, result, 0.001);
    }

    @Test
    public void testHectareToSquareFoot() {
        double result = areaConverterService.convertArea("hectare", "sq foot", 1);
        assertEquals(107639, result, 0.001);
    }

    @Test
    public void testAcreToSquareFoot() {
        double result = areaConverterService.convertArea("acre", "sq foot", 1);
        assertEquals(43560, result, 0.001);
    }

    // Conversion to different output units test

    @Test
    public void testSquareFootToSquareMeter() {
        double result = areaConverterService.convertArea("sq foot", "sq meter", 100);
        assertEquals(9.2903, result, 0.001);
    }

    @Test
    public void testSquareFootToSquareKilometer() {
        double result = areaConverterService.convertArea("sq foot", "sq kilometer", 1000000);
        assertEquals(0.092903, result, 0.001);
    }

    @Test
    public void testSquareFootToSquareMile() {
        double result = areaConverterService.convertArea("sq foot", "sq mile", 1000000);
        assertEquals(0.0358, result, 0.001);
    }

    @Test
    public void testAllFromUnitsToAllToUnits() {
        double[] values = {1, 0, 1000};
        String[] fromUnits = {
                "sq meter", "sq kilometer", "sq mile", "sq yard",
                "sq foot", "sq inch", "hectare", "acre"
        };
        String[] toUnits = {
                "sq meter", "sq kilometer", "sq mile", "sq yard",
                "sq foot", "sq inch", "hectare", "acre"
        };

        for (double value : values) {
            for (String fromUnit : fromUnits) {
                for (String toUnit : toUnits) {
                    double result = areaConverterService.convertArea(fromUnit, toUnit, value);
                    assertNotEquals(-1, result);
                }
            }
        }
    }

    @Test
    public void testInvalidFromUnit() {
        double result = areaConverterService.convertArea("invalid", "sq foot", 10);
        assertEquals(-1, result);
    }

    @Test
    public void testInvalidToUnit() {
        double result = areaConverterService.convertArea("sq foot", "invalid", 10);
        assertEquals(-1, result);
    }

    @Test
    public void testBothInvalidUnits() {
        double result = areaConverterService.convertArea("invalid", "invalid", 10);
        assertEquals(-1, result);
    }

    @Test
    public void testSpecificConversions() {
        // Test specific conversions with known values
        assertEquals(10.7639, areaConverterService.convertArea("sq meter", "sq foot", 1), 0.0001);
        assertEquals(1.076e+7, areaConverterService.convertArea("sq kilometer", "sq foot", 1), 0.0001);
        assertEquals(9, areaConverterService.convertArea("sq yard", "sq foot", 1), 0.0001);
    }

    @Test
    public void testEdgeCases() {
        // Zero value
        assertEquals(0, areaConverterService.convertArea("sq foot", "sq meter", 0), 0.0001);

        // Large value
        assertNotEquals(-1, areaConverterService.convertArea("hectare", "sq mile", 1000));
    }

}