package com.testing.Converters.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

public class FrequencyConverterServiceTest {
    private final FrequencyConverterService frequencyConverterService = new FrequencyConverterService();

    // Test same unit conversions
    @Test
    @DisplayName("Test conversion within same unit should return same value")
    public void testSameUnitConversion() {
        BigDecimal input = BigDecimal.valueOf(100);
        BigDecimal result = frequencyConverterService.convertFrequency("hertz", "hertz", input);
        assertEquals(input, result, "Same unit conversion should return input value");
    }

    // Test all valid conversion combinations
    @ParameterizedTest
    @DisplayName("Test valid frequency conversions")
    @CsvSource({
            "hertz,kilohertz,1000,1",
            "hertz,megahertz,1000000,1",
            "hertz,gigahertz,1000000000,1",
            "kilohertz,hertz,1,1000",
            "kilohertz,megahertz,1000,1",
            "kilohertz,gigahertz,1000000,1",
            "megahertz,hertz,1,1000000",
            "megahertz,kilohertz,1,1000",
            "megahertz,gigahertz,1000,1",
            "gigahertz,hertz,1,1000000000",
            "gigahertz,kilohertz,1,1000000",
            "gigahertz,megahertz,1,1000"
    })
    public void testValidConversions(String fromUnit, String toUnit, double input, double expected) {
        BigDecimal result = frequencyConverterService.convertFrequency(
                fromUnit,
                toUnit,
                BigDecimal.valueOf(input)
        );
        assertEquals(
                BigDecimal.valueOf(expected).setScale(6, RoundingMode.HALF_UP),
                result.setScale(6, RoundingMode.HALF_UP),
                String.format("Converting %s to %s failed", fromUnit, toUnit)
        );
    }

    // Test invalid input unit
    @Test
    @DisplayName("Test invalid input unit should return zero")
    public void testInvalidInputUnit() {
        BigDecimal result = frequencyConverterService.convertFrequency(
                "invalidUnit",
                "hertz",
                BigDecimal.ONE
        );
        assertEquals(BigDecimal.ZERO, result, "Invalid input unit should return zero");
    }

    // Test invalid output unit
    @Test
    @DisplayName("Test invalid output unit should return zero")
    public void testInvalidOutputUnit() {
        BigDecimal result = frequencyConverterService.convertFrequency(
                "hertz",
                "invalidUnit",
                BigDecimal.ONE
        );
        assertEquals(BigDecimal.ZERO, result, "Invalid output unit should return zero");
    }

    // Test zero value conversion
    @Test
    @DisplayName("Test zero value conversion")
    public void testZeroValueConversion() {
        BigDecimal result = frequencyConverterService.convertFrequency(
                "hertz",
                "kilohertz",
                BigDecimal.ZERO
        );
        assertEquals(BigDecimal.ZERO, result, "Zero value conversion failed");
    }

    // Test large number conversion
    @Test
    @DisplayName("Test large number conversion")
    public void testLargeNumberConversion() {
        BigDecimal input = BigDecimal.valueOf(1e+20);
        BigDecimal result = frequencyConverterService.convertFrequency(
                "hertz",
                "gigahertz",
                input
        );
        assertEquals(
                input.divide(BigDecimal.valueOf(1e+9), 10, RoundingMode.HALF_UP),
                result.setScale(10, RoundingMode.HALF_UP),
                "Large number conversion failed"
        );
    }

    // Test null handling
    @Test
    @DisplayName("Test null value handling")
    public void testNullValueHandling() {
        assertThrows(NullPointerException.class, () -> {
            frequencyConverterService.convertFrequency("hertz", "kilohertz", null);
        }, "Should throw NullPointerException for null value");
    }

    // Test precision
    @Test
    @DisplayName("Test conversion precision")
    public void testConversionPrecision() {
        BigDecimal input = BigDecimal.valueOf(1.23456789);
        BigDecimal result = frequencyConverterService.convertFrequency(
                "gigahertz",
                "hertz",
                input
        );
        assertEquals(
                input.multiply(BigDecimal.valueOf(1e+9)).setScale(8, RoundingMode.HALF_UP),
                result.setScale(8, RoundingMode.HALF_UP),
                "Precision test failed"
        );
    }
}