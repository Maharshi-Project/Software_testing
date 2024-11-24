package com.testing.Converters.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AreaConverterServiceTest {
    private final AreaConverterService areaConverterService = new AreaConverterService();

    @Test
    void testConvertSqMeterToSqFoot() {
        double result = areaConverterService.convertArea("sq meter", "sq foot", 1);
        assertEquals(10.7639, result, 0.0001);
    }

    @Test
    void testConvertSqKilometerToSqMile() {
        double result = areaConverterService.convertArea("sq kilometer", "sq mile", 1);
        assertEquals(1.076e+7 * 3.587e-8, result, 0.0001);
    }

    @Test
    void testConvertAcreToHectare() {
        double result = areaConverterService.convertArea("acre", "hectare", 1);
        assertEquals(43560 * 9.2903e-6, result, 0.0001);
    }

    @Test
    void testConvertSqInchToSqYard() {
        double result = areaConverterService.convertArea("sq inch", "sq yard", 144);
        assertEquals(0.00694444 * 144 * 0.111111, result, 0.0001);
    }

    @Test
    void testInvalidFromUnit() {
        double result = areaConverterService.convertArea("invalidUnit", "sq meter", 100);
        assertEquals(-1, result);
    }

    @Test
    void testInvalidToUnit() {
        double result = areaConverterService.convertArea("sq meter", "invalidUnit", 100);
        assertEquals(-1, result);
    }

    @Test
    void testBoundaryCaseZeroValue() {
        double result = areaConverterService.convertArea("sq meter", "sq foot", 0);
        assertEquals(0, result);
    }

    @Test
    void testNegativeValueConversion() {
        double result = areaConverterService.convertArea("sq foot", "sq inch", -5);
        assertEquals(-5 * 144, result);
    }

    @Test
    void testConvertHectareToSqKilometer() {
        double result = areaConverterService.convertArea("hectare", "sq kilometer", 1);
        assertEquals(107639 * 9.2903e-8, result, 0.0001);
    }
}