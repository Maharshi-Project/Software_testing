package com.testing.Converters.service;

import org.junit.Test;

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
}