package com.testing.Converters.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TemperatureServiceTest {
    private TemperatureService temperatureService = new TemperatureService();
    private static final int SCALE = 2;

    // Celsius to Fahrenheit tests
    @Test
    public void testCelsiusToFahrenheit() {
        assertEquals(new BigDecimal("32.00"),
                temperatureService.celsiusToFahrenheit(BigDecimal.ZERO).setScale(SCALE, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal("212.00"),
                temperatureService.celsiusToFahrenheit(new BigDecimal("100")).setScale(SCALE, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal("-40.00"),
                temperatureService.celsiusToFahrenheit(new BigDecimal("-40")).setScale(SCALE, RoundingMode.HALF_UP));
    }

    // Celsius to Kelvin tests
    @Test
    public void testCelsiusToKelvin() {
        assertEquals(new BigDecimal("273.15"),
                temperatureService.celsiusToKelvin(BigDecimal.ZERO));
        assertEquals(new BigDecimal("373.15"),
                temperatureService.celsiusToKelvin(new BigDecimal("100")));
        assertEquals(new BigDecimal("233.15"),
                temperatureService.celsiusToKelvin(new BigDecimal("-40")));
    }

    // Fahrenheit to Celsius tests
    @Test
    public void testFahrenheitToCelsius() {
        assertEquals(new BigDecimal("0.00"),
                temperatureService.fahrenheitToCelsius(new BigDecimal("32")).setScale(SCALE, RoundingMode.HALF_UP));
        assertEquals(0,
                temperatureService.fahrenheitToCelsius(new BigDecimal("212")).setScale(SCALE, RoundingMode.HALF_UP).compareTo(BigDecimal.valueOf(100.80)));
        assertEquals(new BigDecimal("-40.32"),
                temperatureService.fahrenheitToCelsius(new BigDecimal("-40")).setScale(SCALE, RoundingMode.HALF_UP));
    }

    // Fahrenheit to Kelvin tests
    @Test
    public void testFahrenheitToKelvin() {
        assertEquals(new BigDecimal("273.15"),
                temperatureService.fahrenheitToKelvin(new BigDecimal("32")).setScale(SCALE, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal("373.95"),
                temperatureService.fahrenheitToKelvin(new BigDecimal("212")).setScale(SCALE, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal("232.83"),
                temperatureService.fahrenheitToKelvin(new BigDecimal("-40")).setScale(SCALE, RoundingMode.HALF_UP));
    }

    // Kelvin to Celsius tests
    @Test
    public void testKelvinToCelsius() {
        assertEquals(new BigDecimal("0.00"),
                temperatureService.kelvinToCelsius(new BigDecimal("273.15")).setScale(SCALE, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal("100.00"),
                temperatureService.kelvinToCelsius(new BigDecimal("373.15")).setScale(SCALE, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal("-40.00"),
                temperatureService.kelvinToCelsius(new BigDecimal("233.15")).setScale(SCALE, RoundingMode.HALF_UP));
    }

    // Kelvin to Fahrenheit tests
    @Test
    public void testKelvinToFahrenheit() {
        assertEquals(new BigDecimal("32.00"),
                temperatureService.kelvinToFahrenheit(new BigDecimal("273.15")).setScale(SCALE, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal("212.00"),
                temperatureService.kelvinToFahrenheit(new BigDecimal("373.15")).setScale(SCALE, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal("-40.00"),
                temperatureService.kelvinToFahrenheit(new BigDecimal("233.15")).setScale(SCALE, RoundingMode.HALF_UP));
    }

    // Test extreme temperatures
    @Test
    public void testExtremeTemperatures() {
        // Absolute Zero in Kelvin (0K = -273.15°C = -459.67°F)
        BigDecimal absoluteZeroKelvin = BigDecimal.ZERO;
        BigDecimal absoluteZeroCelsius = new BigDecimal("-273.15");
        BigDecimal absoluteZeroFahrenheit = new BigDecimal("-459.67");

        assertEquals(absoluteZeroCelsius.setScale(SCALE, RoundingMode.HALF_UP),
                temperatureService.kelvinToCelsius(absoluteZeroKelvin).setScale(SCALE, RoundingMode.HALF_UP));
        assertEquals(absoluteZeroFahrenheit.setScale(SCALE, RoundingMode.HALF_UP),
                temperatureService.kelvinToFahrenheit(absoluteZeroKelvin).setScale(SCALE, RoundingMode.HALF_UP));
    }

    // Parameterized test for multiple scenarios
    @ParameterizedTest
    @MethodSource("provideTemperatureTestCases")
    void testTemperatureConversions(String conversionType, BigDecimal input, BigDecimal expected) {
        BigDecimal result;
        switch (conversionType) {
            case "C->F" -> result = temperatureService.celsiusToFahrenheit(input);
            case "C->K" -> result = temperatureService.celsiusToKelvin(input);
            case "F->C" -> result = temperatureService.fahrenheitToCelsius(input);
            case "F->K" -> result = temperatureService.fahrenheitToKelvin(input);
            case "K->C" -> result = temperatureService.kelvinToCelsius(input);
            case "K->F" -> result = temperatureService.kelvinToFahrenheit(input);
            default -> throw new IllegalArgumentException("Invalid conversion type");
        }
        assertEquals(expected.setScale(SCALE, RoundingMode.HALF_UP),
                result.setScale(SCALE, RoundingMode.HALF_UP));
    }

    private static Stream<Arguments> provideTemperatureTestCases() {
        return Stream.of(
                // Normal temperature tests
                Arguments.of("C->F", new BigDecimal("20"), new BigDecimal("68")),
                Arguments.of("C->K", new BigDecimal("20"), new BigDecimal("293.15")),
                Arguments.of("F->C", new BigDecimal("68"), new BigDecimal("20.16")),
                Arguments.of("F->K", new BigDecimal("68"), new BigDecimal("293.31")),
                Arguments.of("K->C", new BigDecimal("293.15"), new BigDecimal("20")),
                Arguments.of("K->F", new BigDecimal("293.15"), new BigDecimal("68")),

                // Boiling point of water
                Arguments.of("C->F", new BigDecimal("100"), new BigDecimal("212")),
                Arguments.of("C->K", new BigDecimal("100"), new BigDecimal("373.15")),

                // Freezing point of water
                Arguments.of("C->F", new BigDecimal("0"), new BigDecimal("32")),
                Arguments.of("C->K", new BigDecimal("0"), new BigDecimal("273.15")),

                // Body temperature
                Arguments.of("C->F", new BigDecimal("37"), new BigDecimal("99.00"))
        );
    }
}