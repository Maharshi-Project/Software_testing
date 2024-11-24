package com.testing.Converters.service;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MassConverterServiceTest {
    private final MassConverterService massConverterService = new MassConverterService();

    @Test
    void testConvertTonneToKilogram() {
        BigDecimal result = massConverterService.convertMass("tonne", "kilogram", BigDecimal.valueOf(1));
        assertEquals(0, result.compareTo(BigDecimal.valueOf(1000)));
    }

    @Test
    void testConvertKilogramToGram() {
        BigDecimal result = massConverterService.convertMass("kilogram", "gram", BigDecimal.valueOf(1));
        assertEquals(0, result.compareTo(BigDecimal.valueOf(1000)));
    }

    @Test
    void testConvertGramToMilligram() {
        BigDecimal result = massConverterService.convertMass("gram", "milligram", BigDecimal.valueOf(1));
        assertEquals(0, result.compareTo(BigDecimal.valueOf(1000)));
    }

    @Test
    void testConvertMilligramToMicrogram() {
        BigDecimal result = massConverterService.convertMass("milligram", "microgram", BigDecimal.valueOf(1));
        assertEquals(0,result.compareTo(BigDecimal.valueOf(1000)));
    }

    @Test
    void testConvertImperialTonToUSTon() {
        BigDecimal result = massConverterService.convertMass("imperial ton", "US ton", BigDecimal.valueOf(1));
        assertEquals(0, result.compareTo(BigDecimal.valueOf(1.12)));
//        assertEquals(BigDecimal.valueOf(1.12), result);
    }

    @Test
    void testConvertUSTonToStone() {
        BigDecimal result = massConverterService.convertMass("US ton", "stone", BigDecimal.valueOf(1));
        assertEquals(BigDecimal.valueOf(142.857), result.setScale(3, BigDecimal.ROUND_HALF_UP));  // Rounded to 2 decimals
    }

    @Test
    void testConvertPoundToOunce() {
        BigDecimal result = massConverterService.convertMass("pound", "ounce", BigDecimal.valueOf(1));
        assertEquals(0, result.compareTo(BigDecimal.valueOf(16)));
    }

    @Test
    void testConvertInvalidFromUnit() {
        BigDecimal result = massConverterService.convertMass("invalidUnit", "gram", BigDecimal.valueOf(1));
        assertEquals(BigDecimal.valueOf(-1), result);
    }

    @Test
    void testConvertInvalidToUnit() {
        BigDecimal result = massConverterService.convertMass("gram", "invalidUnit", BigDecimal.valueOf(1));
        assertEquals(0, result.compareTo(BigDecimal.valueOf(-1)));
    }

    @Test
    void testBoundaryCaseZeroValue() {
        BigDecimal result = massConverterService.convertMass("kilogram", "gram", BigDecimal.valueOf(0));
        assertEquals(0, result.compareTo(BigDecimal.valueOf(0)));
    }

    @Test
    void testNegativeValueConversion() {
        BigDecimal result = massConverterService.convertMass("kilogram", "pound", BigDecimal.valueOf(-1));
        assertEquals(BigDecimal.valueOf(-2.205), result.setScale(3, BigDecimal.ROUND_HALF_UP));  // Rounded to 5 decimals
    }
}