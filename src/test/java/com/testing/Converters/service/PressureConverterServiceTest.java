package com.testing.Converters.service;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class PressureConverterServiceTest {
    private PressureConverterService pressureConverterService = new PressureConverterService();;

    @Test
    public void testConvertPascalToBar() {
        BigDecimal result = pressureConverterService.convertPressure("pascal", "bar", BigDecimal.valueOf(100000));
        assertEquals(0, result.compareTo(BigDecimal.valueOf(1.000)));
    }

    @Test
    public void testConvertPascalToPoundPerSquareInch() {
        BigDecimal result = pressureConverterService.convertPressure("pascal", "pound per sq inch", BigDecimal.valueOf(101325));
        assertEquals(BigDecimal.valueOf(14.695), result);
    }

    @Test
    public void testConvertPascalToTorr() {
        BigDecimal result = pressureConverterService.convertPressure("pascal", "torr", BigDecimal.valueOf(101325));
        assertEquals(0, result.compareTo(BigDecimal.valueOf(760.128)));
    }

    @Test
    public void testConvertBarToPascal() {
        BigDecimal result = pressureConverterService.convertPressure("bar", "pascal", BigDecimal.valueOf(1));
        assertEquals(BigDecimal.valueOf(100000), result);
    }

    @Test
    public void testConvertPoundPerSquareInchToPascal() {
        BigDecimal result = pressureConverterService.convertPressure("pound per sq inch", "pascal", BigDecimal.valueOf(14.696));
        assertEquals(0, result.compareTo(BigDecimal.valueOf(101328.920)));
    }

    @Test
    public void testConvertStandardAtmosphereToTorr() {
        BigDecimal result = pressureConverterService.convertPressure("standard atmosphere", "torr", BigDecimal.valueOf(1));
        assertEquals(0, result.compareTo(BigDecimal.valueOf(759.940)));
    }

    @Test
    public void testConvertTorrToStandardAtmosphere() {
        BigDecimal result = pressureConverterService.convertPressure("torr", "standard atmosphere", BigDecimal.valueOf(760));
        assertEquals(0, result.compareTo(BigDecimal.valueOf(1.000)));
    }

    @Test
    public void testConvertTorrToPoundPerSquareInch() {
        BigDecimal result = pressureConverterService.convertPressure("torr", "pound per sq inch", BigDecimal.valueOf(760));
        assertEquals(BigDecimal.valueOf(14.693), result);
    }

    @Test
    public void testInvalidUnitConversion() {
        BigDecimal result = pressureConverterService.convertPressure("unknown", "pascal", BigDecimal.valueOf(100000));
        assertEquals(BigDecimal.valueOf(0), result);
    }

    @Test
    public void testAllFromUnitsToAllToUnits() {
        BigDecimal[] values = {
                BigDecimal.ONE,
                BigDecimal.ZERO,
                BigDecimal.valueOf(1000)
        };
        String[] fromUnits = {
                "bar", "pascal", "pound per sq inch", "standard atmosphere", "torr"
        };
        String[] toUnits = {
                "bar", "pascal", "pound per sq inch", "standard atmosphere", "torr"
        };

        for (BigDecimal value : values) {
            for (String fromUnit : fromUnits) {
                for (String toUnit : toUnits) {
                    BigDecimal result = pressureConverterService.convertPressure(fromUnit, toUnit, value);
                    assertNotEquals(BigDecimal.valueOf(-1), result);
                }
            }
        }
    }

    @Test
    public void testInvalidFromUnit() {
        BigDecimal result = pressureConverterService.convertPressure("invalid", "pascal", BigDecimal.ONE);
        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    public void testInvalidToUnit() {
        BigDecimal result = pressureConverterService.convertPressure("pascal", "invalid", BigDecimal.ONE);
        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    public void testBothInvalidUnits() {
        BigDecimal result = pressureConverterService.convertPressure("invalid", "invalid", BigDecimal.ONE);
        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    public void testSpecificConversions() {
        assertEquals(0,
                pressureConverterService.convertPressure("bar", "pascal", BigDecimal.ONE).compareTo(BigDecimal.valueOf(100000)));

        assertEquals(0,
                pressureConverterService.convertPressure("pound per sq inch", "pascal", BigDecimal.ONE).compareTo(BigDecimal.valueOf(6895)));
    }

    @Test
    public void testEdgeCases() {
        // Zero value
        BigDecimal zeroValue = BigDecimal.ZERO;
        assertNotNull(pressureConverterService.convertPressure("pascal", "bar", zeroValue));

        // Large value
        BigDecimal largeValue = BigDecimal.valueOf(1000000);
        assertNotNull(pressureConverterService.convertPressure("bar", "pascal", largeValue));
    }

}