package com.testing.Converters.service;


import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

public class MassConverterServiceTest {
    private final MassConverterService massConverterService = new MassConverterService();

    @Test
    public void testConvertTonneToKilogram() throws Exception {
        BigDecimal result = massConverterService.convertMass("tonne", "kilogram", BigDecimal.valueOf(1));
        assertEquals(0, result.compareTo(BigDecimal.valueOf(1000)));
    }

    @Test
    public void testConvertKilogramToGram() throws Exception {
        BigDecimal result = massConverterService.convertMass("kilogram", "gram", BigDecimal.valueOf(1));
        assertEquals(0, result.compareTo(BigDecimal.valueOf(1000)));
    }

    @Test
    public void testConvertGramToMilligram() throws Exception {
        BigDecimal result = massConverterService.convertMass("gram", "milligram", BigDecimal.valueOf(1));
        assertEquals(0, result.compareTo(BigDecimal.valueOf(1000)));
    }

    @Test
    public void testConvertMilligramToMicrogram() throws Exception {
        BigDecimal result = massConverterService.convertMass("milligram", "microgram", BigDecimal.valueOf(1));
        assertEquals(0,result.compareTo(BigDecimal.valueOf(1000)));
    }

    @Test
    public void testConvertImperialTonToUSTon() throws Exception {
        BigDecimal result = massConverterService.convertMass("imperial ton", "US ton", BigDecimal.valueOf(1));
        assertEquals(0, result.compareTo(BigDecimal.valueOf(1.12)));
    }

    @Test
    public void testConvertUSTonToStone() throws Exception {
        BigDecimal result = massConverterService.convertMass("US ton", "stone", BigDecimal.valueOf(1));
        assertEquals(BigDecimal.valueOf(142.857), result.setScale(3, RoundingMode.HALF_UP));  // Rounded to 2 decimals
    }

    @Test
    public void testConvertPoundToOunce() throws Exception {
        BigDecimal result = massConverterService.convertMass("pound", "ounce", BigDecimal.valueOf(1));
        assertEquals(0, result.compareTo(BigDecimal.valueOf(16)));
    }

    @Test
    public void testConvertInvalidFromUnit() {
        assertThrows(Exception.class, () ->
            massConverterService.convertMass("invalid", "gram", BigDecimal.valueOf(1))
        );
    }

    @Test
    public void testConvertInvalidToUnit() {
        assertThrows(Exception.class, () ->
                massConverterService.convertMass("gram", "invalid", BigDecimal.valueOf(1))
                );
    }

    @Test
    public void testBoundaryCaseZeroValue() throws Exception {
        BigDecimal result = massConverterService.convertMass("kilogram", "gram", BigDecimal.valueOf(0));
        assertEquals(0, result.compareTo(BigDecimal.valueOf(0)));
    }

    @Test
    public void testNegativeValueConversion() throws Exception {
        BigDecimal result = massConverterService.convertMass("kilogram", "pound", BigDecimal.valueOf(-1));
        assertEquals(BigDecimal.valueOf(-2.205), result.setScale(3, RoundingMode.HALF_UP));  // Rounded to 5 decimals
    }

    @Test
    public void testAllFromUnitsToAllToUnits() throws Exception {
        BigDecimal[] values = {
                BigDecimal.valueOf(1),
                BigDecimal.valueOf(0),
                BigDecimal.valueOf(1000)
        };

        String[] fromUnits = {
                "tonne", "kilogram", "gram", "milligram", "microgram",
                "imperial ton", "US ton", "stone", "pound", "ounce"
        };

        String[] toUnits = {
                "tonne", "kilogram", "gram", "milligram", "microgram",
                "imperial ton", "US ton", "stone", "pound", "ounce"
        };

        for (BigDecimal value : values) {
            for (String fromUnit : fromUnits) {
                for (String toUnit : toUnits) {
                    BigDecimal result = massConverterService.convertMass(fromUnit, toUnit, value);
                    assertNotEquals(BigDecimal.valueOf(-1), result);
                }
            }
        }
    }

    @Test
    public void testInvalidFromUnit() {
        assertThrows(Exception.class, () ->
                massConverterService.convertMass("invalid", "gram", BigDecimal.valueOf(1))
        );
    }

    @Test
    public void testInvalidToUnit() {
        assertThrows(Exception.class, () ->
                massConverterService.convertMass("kilogram", "invalid", BigDecimal.valueOf(1))
        );
    }

    @Test
    public void testBothInvalidUnits() {
        assertThrows(Exception.class, () ->
                massConverterService.convertMass("invalid", "invalid", BigDecimal.valueOf(1))
        );
    }

    @Test
    public void testSpecificConversions() throws Exception {
        // Test specific conversions with known expected results
        assertEquals(BigDecimal.valueOf(1000).setScale(3, RoundingMode.HALF_UP),
                massConverterService.convertMass("kilogram", "gram", BigDecimal.ONE));

        assertEquals(BigDecimal.valueOf(0.001).setScale(3, RoundingMode.HALF_UP),
                massConverterService.convertMass("gram", "kilogram", BigDecimal.ONE));
    }

    @Test
    public void testEdgeCases() throws Exception {
        // Zero value
        BigDecimal zeroValue = BigDecimal.ZERO;
        assertNotNull(massConverterService.convertMass("gram", "kilogram", zeroValue));

        // Large value
        BigDecimal largeValue = BigDecimal.valueOf(1000000);
        assertNotNull(massConverterService.convertMass("tonne", "microgram", largeValue));
    }

}