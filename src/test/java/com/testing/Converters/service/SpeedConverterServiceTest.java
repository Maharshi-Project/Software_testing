package com.testing.Converters.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SpeedConverterServiceTest {
    private SpeedConverterService speedConverterService;

    @BeforeEach
    void setUp() {
        speedConverterService = new SpeedConverterService();
    }

    // Test each unit to meter per second (base unit) conversion
    @Test
    void testConversionToBaseUnit() {
        assertEquals(new BigDecimal("0.447"),
                speedConverterService.convertSpeed("mile per hour", "meter per second", BigDecimal.ONE));

        assertEquals(new BigDecimal("0.305"),
                speedConverterService.convertSpeed("foot per second", "meter per second", BigDecimal.ONE));

        assertEquals(new BigDecimal("0.278"),
                speedConverterService.convertSpeed("kilometer per hour", "meter per second", BigDecimal.ONE));

        assertEquals(new BigDecimal("0.514"),
                speedConverterService.convertSpeed("knot", "meter per second", BigDecimal.ONE));
    }

    // Test conversion from meter per second (base unit) to other units
    @Test
    void testConversionFromBaseUnit() {
        assertEquals(new BigDecimal("2.237"),
                speedConverterService.convertSpeed("meter per second", "mile per hour", BigDecimal.ONE));

        assertEquals(new BigDecimal("3.281"),
                speedConverterService.convertSpeed("meter per second", "foot per second", BigDecimal.ONE));

        assertEquals(new BigDecimal("3.600"),
                speedConverterService.convertSpeed("meter per second", "kilometer per hour", BigDecimal.ONE));

        assertEquals(new BigDecimal("1.944"),
                speedConverterService.convertSpeed("meter per second", "knot", BigDecimal.ONE));
    }

    // Test same unit conversion
    @Test
    void testSameUnitConversion() {
        BigDecimal testValue = new BigDecimal("100");
        assertEquals(testValue.setScale(3, RoundingMode.HALF_UP),
                speedConverterService.convertSpeed("meter per second", "meter per second", testValue));
    }

    // Test invalid unit combinations
    @Test
    void testInvalidUnits() {
        assertEquals(0,
                speedConverterService.convertSpeed("invalid unit", "meter per second", BigDecimal.ONE).compareTo(BigDecimal.valueOf(-1)));
        assertEquals(0,
                speedConverterService.convertSpeed("meter per second", "invalid unit", BigDecimal.ONE).compareTo(BigDecimal.valueOf(-1)));
    }

    // Test zero value conversion
    @Test
    void testZeroConversion() {
        assertEquals(new BigDecimal("0.000"),
                speedConverterService.convertSpeed("mile per hour", "kilometer per hour", BigDecimal.ZERO));
    }

    // Test large number conversion
    @Test
    void testLargeNumberConversion() {
        BigDecimal largeNumber = new BigDecimal("1000000");
        BigDecimal result = speedConverterService.convertSpeed("mile per hour", "kilometer per hour", largeNumber);
        // First verify it's a positive number
        assertTrue(result.compareTo(BigDecimal.ZERO) > 0);
        // Then verify the exact value
        assertEquals(new BigDecimal("1609298.167"), result);
    }

    // Parameterized test for multiple conversion scenarios
    @ParameterizedTest
    @MethodSource("provideConversionTestCases")
    void testMultipleConversionScenarios(String fromUnit, String toUnit, BigDecimal input, BigDecimal expected) {
        assertEquals(expected,
                speedConverterService.convertSpeed(fromUnit, toUnit, input));
    }

    private static Stream<Arguments> provideConversionTestCases() {
        return Stream.of(
                Arguments.of("mile per hour", "kilometer per hour", new BigDecimal("60"),
                        new BigDecimal("96.558")),
                Arguments.of("mile per hour", "meter per second", new BigDecimal("60"),
                        new BigDecimal("26.822")),
                Arguments.of("kilometer per hour", "mile per hour", new BigDecimal("100"),
                        new BigDecimal("62.139")),
                Arguments.of("kilometer per hour", "foot per second", new BigDecimal("100"),
                        new BigDecimal("91.139")),
                Arguments.of("knot", "kilometer per hour", new BigDecimal("50"),
                        new BigDecimal("92.593")),
                Arguments.of("knot", "mile per hour", new BigDecimal("50"),
                        new BigDecimal("57.536"))
        );
    }
}