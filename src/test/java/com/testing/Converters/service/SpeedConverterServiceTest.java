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

public class SpeedConverterServiceTest {

    private SpeedConverterService speedConverterService;

    @BeforeEach
    void setUp() {
        speedConverterService = new SpeedConverterService();
    }
    // Test each unit to meter per second (base unit) conversion
    @Test
    public void testConversionToBaseUnit() throws Exception {
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
    public void testConversionFromBaseUnit() throws Exception {
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
    public void testSameUnitConversion() throws Exception {
        BigDecimal testValue = new BigDecimal("100");
        assertEquals(testValue.setScale(3, RoundingMode.HALF_UP),
                speedConverterService.convertSpeed("meter per second", "meter per second", testValue));
    }

    // Test invalid unit combinations
    @Test
    public void testInvalidUnits() throws Exception {
        Exception exception = assertThrows(
                Exception.class, () -> speedConverterService.convertSpeed("invalid unit", "meter per second", BigDecimal.ONE).compareTo(BigDecimal.valueOf(-1))
                );
        Exception expectedException = assertThrows(
                Exception.class, () -> speedConverterService.convertSpeed("meter per second", "invalid unit", BigDecimal.ONE).compareTo(BigDecimal.valueOf(-1))
        );
//        assertEquals(0,
//                speedConverterService.convertSpeed("invalid unit", "meter per second", BigDecimal.ONE).compareTo(BigDecimal.valueOf(-1)));
//        assertEquals(0,
//                speedConverterService.convertSpeed("meter per second", "invalid unit", BigDecimal.ONE).compareTo(BigDecimal.valueOf(-1)));
    }

    // Test zero value conversion
    @Test
    public void testZeroConversion() throws Exception {
        assertEquals(new BigDecimal("0.000"),
                speedConverterService.convertSpeed("mile per hour", "kilometer per hour", BigDecimal.ZERO));
    }

    // Test large number conversion
    @Test
    public void testLargeNumberConversion() throws Exception {
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
    void testMultipleConversionScenarios(String fromUnit, String toUnit, BigDecimal input, BigDecimal expected) throws Exception {
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

    @Test
    public void testAllFromUnitsToAllToUnits() throws Exception {
        BigDecimal[] values = {
                BigDecimal.ONE,
                BigDecimal.ZERO,
                BigDecimal.valueOf(1000)
        };
        String[] fromUnits = {
                "mile per hour", "foot per second", "meter per second",
                "kilometer per hour", "knot"
        };
        String[] toUnits = {
                "mile per hour", "foot per second", "meter per second",
                "kilometer per hour", "knot"
        };

        for (BigDecimal value : values) {
            for (String fromUnit : fromUnits) {
                for (String toUnit : toUnits) {
                    BigDecimal result = speedConverterService.convertSpeed(fromUnit, toUnit, value);
                    assertNotEquals(BigDecimal.valueOf(-1), result);
                }
            }
        }
    }

    @Test
    public void testInvalidFromUnit() throws Exception {
        Exception actualException = assertThrows(
                Exception.class, () -> speedConverterService.convertSpeed("invalid", "meter per second", BigDecimal.ONE)
        );
//        BigDecimal result = speedConverterService.convertSpeed("invalid", "meter per second", BigDecimal.ONE);
//        assertEquals(BigDecimal.valueOf(-1), result);
    }

    @Test
    public void testInvalidToUnit() throws Exception {
        Exception actualException = assertThrows(
                Exception.class, () -> speedConverterService.convertSpeed("meter per second", "invalid", BigDecimal.ONE)
        );
//        BigDecimal result = speedConverterService.convertSpeed("meter per second", "invalid", BigDecimal.ONE);
//        assertEquals(0, result.compareTo(BigDecimal.valueOf(-1)));
    }

    @Test
    public void testBothInvalidUnits() throws Exception {
        Exception actualException = assertThrows(
              Exception.class, () -> speedConverterService.convertSpeed("invalid", "meter per second", BigDecimal.ONE)
        );
//        BigDecimal result = speedConverterService.convertSpeed("invalid", "invalid", BigDecimal.ONE);
//        assertEquals(BigDecimal.valueOf(-1), result);
    }

    @Test
    public void testSpecificConversions() throws Exception {
        assertEquals(BigDecimal.valueOf(0.447).setScale(3, RoundingMode.HALF_UP),
                speedConverterService.convertSpeed("mile per hour", "meter per second", BigDecimal.ONE));

        assertEquals(BigDecimal.valueOf(3.281).setScale(3, RoundingMode.HALF_UP),
                speedConverterService.convertSpeed("meter per second", "foot per second", BigDecimal.ONE));
    }

    @Test
    public void testEdgeCases() throws Exception {
        BigDecimal zeroValue = BigDecimal.ZERO;
        assertNotNull(speedConverterService.convertSpeed("meter per second", "mile per hour", zeroValue));

        BigDecimal largeValue = BigDecimal.valueOf(1000000);
        assertNotNull(speedConverterService.convertSpeed("kilometer per hour", "knot", largeValue));
    }


    // Test conversions from mile per hour
    @Test
    void testConvertSpeedFromMilePerHour() throws Exception {
        BigDecimal value = new BigDecimal("60"); // 60 miles per hour

        // Convert to different units from mile per hour
        assertEquals(new BigDecimal("26.822"),
                speedConverterService.convertSpeed("mile per hour", "meter per second", value));
        assertEquals(new BigDecimal("96.558"),
                speedConverterService.convertSpeed("mile per hour", "kilometer per hour", value));
        assertEquals(new BigDecimal("88.002"),
                speedConverterService.convertSpeed("mile per hour", "foot per second", value));
        assertEquals(new BigDecimal("52.141"),
                speedConverterService.convertSpeed("mile per hour", "knot", value));
        assertEquals(new BigDecimal("60.000"),
                speedConverterService.convertSpeed("mile per hour", "mile per hour", value));
    }

    // Test conversions from foot per second
    @Test
    void testConvertSpeedFromFootPerSecond() throws Exception {
        BigDecimal value = new BigDecimal("60"); // 60 foot per second

        // Convert to different units from foot per second
        assertEquals(new BigDecimal("18.287"),
                speedConverterService.convertSpeed("foot per second", "meter per second", value));
        assertEquals(new BigDecimal("65.834"),
                speedConverterService.convertSpeed("foot per second", "kilometer per hour", value));
        assertEquals(new BigDecimal("60.000"),
                speedConverterService.convertSpeed("foot per second", "foot per second", value));
        assertEquals(new BigDecimal("35.550"),
                speedConverterService.convertSpeed("foot per second", "knot", value));
        assertEquals(new BigDecimal("40.908"),
                speedConverterService.convertSpeed("foot per second", "mile per hour", value));
    }

    // Test conversions from meter per second
    @Test
    void testConvertSpeedFromMeterPerSecond() throws Exception {
        BigDecimal value = new BigDecimal("10"); // 10 meters per second

        // Convert to different units from meter per second
        assertEquals(new BigDecimal("10.000"),
                speedConverterService.convertSpeed("meter per second", "meter per second", value));
        assertEquals(new BigDecimal("22.370"),
                speedConverterService.convertSpeed("meter per second", "mile per hour", value));
        assertEquals(new BigDecimal("36.000"),
                speedConverterService.convertSpeed("meter per second", "kilometer per hour", value));
        assertEquals(new BigDecimal("32.810"),
                speedConverterService.convertSpeed("meter per second", "foot per second", value));
        assertEquals(new BigDecimal("19.440"),
                speedConverterService.convertSpeed("meter per second", "knot", value));
    }

    // Test conversions from kilometer per hour
    @Test
    void testConvertSpeedFromKilometerPerHour() throws Exception {
        BigDecimal value = new BigDecimal("60"); // 60 kilometers per hour

        // Convert to different units from kilometer per hour
        assertEquals(new BigDecimal("16.667"),
                speedConverterService.convertSpeed("kilometer per hour", "meter per second", value));
        assertEquals(new BigDecimal("37.283"),
                speedConverterService.convertSpeed("kilometer per hour", "mile per hour", value));
        assertEquals(new BigDecimal("60.000"),
                speedConverterService.convertSpeed("kilometer per hour", "kilometer per hour", value));
        assertEquals(new BigDecimal("54.683"),
                speedConverterService.convertSpeed("kilometer per hour", "foot per second", value));
        assertEquals(new BigDecimal("32.400"),
                speedConverterService.convertSpeed("kilometer per hour", "knot", value));
    }

    // Test conversions from knot
    @Test
    void testConvertSpeedFromKnot() throws Exception {
        BigDecimal value = new BigDecimal("30"); // 30 knots

        // Convert to different units from knot
        assertEquals(new BigDecimal("15.432"),
                speedConverterService.convertSpeed("knot", "meter per second", value));
        assertEquals(new BigDecimal("55.556"),
                speedConverterService.convertSpeed("knot", "kilometer per hour", value));
        assertEquals(new BigDecimal("34.522"),
                speedConverterService.convertSpeed("knot", "mile per hour", value));
        assertEquals(new BigDecimal("50.633"),
                speedConverterService.convertSpeed("knot", "foot per second", value));
        assertEquals(new BigDecimal("30.000"),
                speedConverterService.convertSpeed("knot", "knot", value));
    }

    // Exception Handling Tests
    @Test
    void testConvertSpeedInvalidFromUnit() {
        Exception exception = assertThrows(Exception.class, () -> {
            speedConverterService.convertSpeed("invalid unit", "meter per second", new BigDecimal("10"));
        });
        assertEquals("Invalid fromUnit: invalid unit", exception.getMessage());
    }

    @Test
    void testConvertSpeedInvalidToUnit() {
        Exception exception = assertThrows(Exception.class, () -> {
            speedConverterService.convertSpeed("meter per second", "invalid unit", new BigDecimal("10"));
        });
        assertEquals("Invalid toUnit: invalid unit", exception.getMessage());
    }

    // Edge case with zero
    @Test
    void testConvertSpeedZeroValue() throws Exception {
        BigDecimal zeroValue = BigDecimal.ZERO;

        // Test zero conversion for various units
        assertEquals(new BigDecimal("0.000"),
                speedConverterService.convertSpeed("mile per hour", "meter per second", zeroValue));
        assertEquals(new BigDecimal("0.000"),
                speedConverterService.convertSpeed("meter per second", "kilometer per hour", zeroValue));
    }

}