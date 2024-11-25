package com.testing.Converters.service;


import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

public class EnergyConverterServiceTest {
    private final EnergyConverterService energyConverterService = new EnergyConverterService();

    @Test
    public void testConvertJouleToKilojoule() {
        BigDecimal result = energyConverterService.convertEnergy("joule", "kilojoule", BigDecimal.valueOf(1000));
        assertEquals(0, result.compareTo(BigDecimal.valueOf(1)));
    }

    @Test
    public void testConvertKilojouleToJoule() {
        BigDecimal result = energyConverterService.convertEnergy("kilojoule", "joule", BigDecimal.valueOf(1));
        assertEquals(0, result.compareTo(BigDecimal.valueOf(1000)));
    }

    @Test
    public void testConvertGramCalorieToKilocalorie() {
        BigDecimal result = energyConverterService.convertEnergy("gram calorie", "kilocalorie", BigDecimal.valueOf(4184));
        assertEquals(BigDecimal.valueOf(4.184), result);
    }

    @Test
    public void testConvertKilowattHourToWattHour() {
        BigDecimal result = energyConverterService.convertEnergy("kilowatt hour", "watt hour", BigDecimal.valueOf(1));
        assertEquals(0, result.compareTo(BigDecimal.valueOf(1000.0)));
    }

    @Test
    public void testConvertElectronvoltToJoule() {
        BigDecimal result = energyConverterService.convertEnergy("electronvolt", "joule", BigDecimal.valueOf(6.242e+18));
        assertEquals(0, result.compareTo(BigDecimal.valueOf(1)));
    }

    @Test
    public void testBoundaryCaseZeroValue() {
        BigDecimal result = energyConverterService.convertEnergy("joule", "kilojoule", BigDecimal.valueOf(0));
        assertEquals(0, result.compareTo(BigDecimal.valueOf(0)));
    }

    @Test
    public void testNegativeValueConversion() {
        BigDecimal result = energyConverterService.convertEnergy("foot pound", "joule", BigDecimal.valueOf(-5));
        assertEquals(BigDecimal.valueOf(-5).multiply(BigDecimal.valueOf(1.356)), result);
    }

    @Test
    public void testConvertThermToKilojoule() {
        BigDecimal result = energyConverterService.convertEnergy("therm", "kilojoule", BigDecimal.valueOf(1));
        assertEquals(BigDecimal.valueOf(1.055e+8).divide(BigDecimal.valueOf(1000),3, RoundingMode.HALF_UP), result);
    }

    @Test
    public void testAllFromUnitsToAllToUnits() {
        BigDecimal[] values = {
                BigDecimal.ONE,
                BigDecimal.ZERO,
                BigDecimal.valueOf(1000)
        };
        String[] fromUnits = {
                "joule", "kilojoule", "gram calorie", "kilocalorie",
                "watt hour", "kilowatt hour", "electronvolt", "therm", "foot pound"
        };
        String[] toUnits = {
                "joule", "kilojoule", "gram calorie", "kilocalorie",
                "watt hour", "kilowatt hour", "electronvolt", "therm", "foot pound"
        };

        for (BigDecimal value : values) {
            for (String fromUnit : fromUnits) {
                for (String toUnit : toUnits) {
                    BigDecimal result = energyConverterService.convertEnergy(fromUnit, toUnit, value);
                    assertNotEquals(BigDecimal.valueOf(-1.0), result);
                }
            }
        }
    }

    @Test
    public void testInvalidFromUnit() {
        BigDecimal result = energyConverterService.convertEnergy("invalid", "joule", BigDecimal.ONE);
        assertEquals(BigDecimal.valueOf(-1.0), result);
    }

    @Test
    public void testInvalidToUnit() {
        BigDecimal result = energyConverterService.convertEnergy("joule", "invalid", BigDecimal.ONE);
        assertEquals(BigDecimal.valueOf(-1.0), result);
    }

    @Test
    public void testBothInvalidUnits() {
        BigDecimal result = energyConverterService.convertEnergy("invalid", "invalid", BigDecimal.ONE);
        assertEquals(BigDecimal.valueOf(-1.0), result);
    }

    @Test
    public void testSpecificConversions() {
        assertEquals(0,
                energyConverterService.convertEnergy("kilojoule", "joule", BigDecimal.ONE).compareTo(BigDecimal.valueOf(1000)));

        assertEquals(0,
                energyConverterService.convertEnergy("gram calorie", "joule", BigDecimal.ONE).compareTo(BigDecimal.valueOf(4.184)));
    }

    @Test
    public void testEdgeCases() {
        BigDecimal zeroValue = BigDecimal.ZERO;
        assertNotNull(energyConverterService.convertEnergy("joule", "kilojoule", zeroValue));

        BigDecimal largeValue = BigDecimal.valueOf(1000000);
        assertNotNull(energyConverterService.convertEnergy("therm", "joule", largeValue));
    }
}