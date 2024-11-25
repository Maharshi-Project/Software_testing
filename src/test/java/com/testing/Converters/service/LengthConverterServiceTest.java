package com.testing.Converters.service;


import org.junit.jupiter.api.Test;

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


    @Test
    void testConvertLengthValidConversions() {
        // Test conversions from meter to other units
        assertEquals(100.0, lengthConverterService.convertLength("meter", "centimeter", 1.0), 0.001);
        assertEquals(1000.0, lengthConverterService.convertLength("meter", "millimeter", 1.0), 0.001);
        assertEquals(0.001, lengthConverterService.convertLength("meter", "kilometer", 1.0), 0.001);
        assertEquals(0.000621371, lengthConverterService.convertLength("meter", "mile", 1.0), 0.000001);
        assertEquals(1.09361, lengthConverterService.convertLength("meter", "yard", 1.0), 0.001);
        assertEquals(3.28084, lengthConverterService.convertLength("meter", "foot", 1.0), 0.001);
        assertEquals(39.3701, lengthConverterService.convertLength("meter", "inch", 1.0), 0.001);
        assertEquals(0.000539957, lengthConverterService.convertLength("meter", "nautical mile", 1.0), 0.000001);
    }

    @Test
    void testConvertLengthFromOtherUnits() {
        // Test conversions from other units to meter
        assertEquals(0.01, lengthConverterService.convertLength("centimeter", "meter", 1.0), 0.001);
        assertEquals(0.001, lengthConverterService.convertLength("millimeter", "meter", 1.0), 0.001);
        assertEquals(1000.0, lengthConverterService.convertLength("kilometer", "meter", 1.0), 0.001);
        assertEquals(1609.34, lengthConverterService.convertLength("mile", "meter", 1.0), 0.000001);
        assertEquals(0.9144, lengthConverterService.convertLength("yard", "meter", 1.0), 0.001);
        assertEquals(0.3048, lengthConverterService.convertLength("foot", "meter", 1.0), 0.001);
        assertEquals(0.0254, lengthConverterService.convertLength("inch", "meter", 1.0), 0.001);
        assertEquals(1852.0, lengthConverterService.convertLength("nautical mile", "meter", 1.0), 0.001);
    }

    @Test
    void testConvertLengthInvalidUnits() {
        // Test invalid input units
        assertEquals(-1.0, lengthConverterService.convertLength("invalid", "meter", 1.0), 0.001);
        assertEquals(-1.0, lengthConverterService.convertLength("meter", "invalid", 1.0), 0.001);
        assertEquals(-1.0, lengthConverterService.convertLength("invalid", "invalid", 1.0), 0.001);
    }

    @Test
    void testConvertLengthEdgeCases() {
        // Test zero and negative values
        assertEquals(0.0, lengthConverterService.convertLength("meter", "kilometer", 0.0), 0.001);
        assertEquals(-0.001, lengthConverterService.convertLength("meter", "kilometer", -1.0), 0.001);
    }
}