package com.testing.Converters.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LengthConverterServiceTest {
    private final LengthConverterService lengthConverterService = new LengthConverterService();

    @Test
    void testConvertMeterToKilometer() {
        double result = lengthConverterService.convertLength("meter", "kilometer", 1500);
        assertEquals(1.5, result);
    }

    @Test
    void testConvertKilometerToMeter() {
        double result = lengthConverterService.convertLength("kilometer", "meter", 1.5);
        assertEquals(1500, result);
    }

    @Test
    void testInvalidFromUnit() {
        double result = lengthConverterService.convertLength("invalidUnit", "meter", 100);
        assertEquals(-1, result);
    }

    @Test
    void testInvalidToUnit() {
        double result = lengthConverterService.convertLength("meter", "invalidUnit", 100);
        assertEquals(-1, result);
    }

    @Test
    void testBoundaryCase() {
        double result = lengthConverterService.convertLength("meter", "kilometer", 0);
        assertEquals(0, result);
    }

    @Test
    void testNegativeValue() {
        double result = lengthConverterService.convertLength("yard", "meter", -1);
        assertEquals(-0.9144, result);
    }
}