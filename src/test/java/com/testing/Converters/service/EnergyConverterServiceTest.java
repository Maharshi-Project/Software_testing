package com.testing.Converters.service;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class EnergyConverterServiceTest {
    private final EnergyConverterService energyConverterService = new EnergyConverterService();

    @Test
    void testConvertJouleToKilojoule() {
        BigDecimal result = energyConverterService.convertEnergy("joule", "kilojoule", BigDecimal.valueOf(1000));
        assertEquals(BigDecimal.valueOf(1), result);
    }

    @Test
    void testConvertKilojouleToJoule() {
        BigDecimal result = energyConverterService.convertEnergy("kilojoule", "joule", BigDecimal.valueOf(1));
        assertEquals(BigDecimal.valueOf(1000), result);
    }

    @Test
    void testConvertGramCalorieToKilocalorie() {
        BigDecimal result = energyConverterService.convertEnergy("gram calorie", "kilocalorie", BigDecimal.valueOf(4184));
        assertEquals(BigDecimal.valueOf(4.184), result);
    }

    @Test
    void testConvertKilowattHourToWattHour() {
        BigDecimal result = energyConverterService.convertEnergy("kilowatt hour", "watt hour", BigDecimal.valueOf(1));
        assertEquals(BigDecimal.valueOf(1000.0), result);
    }

    @Test
    void testConvertElectronvoltToJoule() {
        BigDecimal result = energyConverterService.convertEnergy("electronvolt", "joule", BigDecimal.valueOf(6.242e+18));
        assertEquals(BigDecimal.valueOf(1), result);
    }

    @Test
    void testInvalidFromUnit() {
        BigDecimal result = energyConverterService.convertEnergy("invalidUnit", "joule", BigDecimal.valueOf(100));
        assertEquals(BigDecimal.valueOf(-1), result);
    }

    @Test
    void testInvalidToUnit() {
        BigDecimal result = energyConverterService.convertEnergy("joule", "invalidUnit", BigDecimal.valueOf(100));
        assertEquals(BigDecimal.valueOf(-1), result);
    }

    @Test
    void testBoundaryCaseZeroValue() {
        BigDecimal result = energyConverterService.convertEnergy("joule", "kilojoule", BigDecimal.valueOf(0));
        assertEquals(BigDecimal.valueOf(0), result);
    }

    @Test
    void testNegativeValueConversion() {
        BigDecimal result = energyConverterService.convertEnergy("foot pound", "joule", BigDecimal.valueOf(-5));
        assertEquals(BigDecimal.valueOf(-5).multiply(BigDecimal.valueOf(1.356)), result);
    }

    @Test
    void testConvertThermToKilojoule() {
        BigDecimal result = energyConverterService.convertEnergy("therm", "kilojoule", BigDecimal.valueOf(1));
        assertEquals(BigDecimal.valueOf(1.055e+8).divide(BigDecimal.valueOf(1000)), result);
    }
}