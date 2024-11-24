package com.testing.Converters.service;


import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LengthConverterServiceTest {
    private final LengthConverterService lengthConverterService = new LengthConverterService();

    @Test
    public void testConvertMeterToKilometer() {
        double result = lengthConverterService.convertLength("meter", "kilometer", 1500);
        assertEquals(1.5, result);
    }

    @Test
    public void testConvertKilometerToMeter() {
        double result = lengthConverterService.convertLength("kilometer", "meter", 1.5);
        assertEquals(1500, result);
    }

    @Test
    public void testInvalidFromUnit() {
        double result = lengthConverterService.convertLength("invalidUnit", "meter", 100);
        assertEquals(-1, result);
    }

    @Test
    public void testInvalidToUnit() {
        double result = lengthConverterService.convertLength("meter", "invalidUnit", 100);
        assertEquals(-1, result);
    }

    @Test
    public void testBoundaryCase() {
        double result = lengthConverterService.convertLength("meter", "kilometer", 0);
        assertEquals(0, result);
    }

    @Test
    public void testNegativeValue() {
        double result = lengthConverterService.convertLength("yard", "meter", -1);
        assertEquals(-0.9144, result);
    }
}