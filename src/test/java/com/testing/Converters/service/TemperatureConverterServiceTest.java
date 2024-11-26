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

public class TemperatureConverterServiceTest {
    private TemperatureConverterService temperatureConverterService;
    private static final int SCALE = 2;

    @BeforeEach
    void setUp() {
        temperatureConverterService = new TemperatureConverterService();
    }

    // Celsius to Fahrenheit tests
    @Test
    public void testCelsiusToFahrenheit1() {
        assertEquals(new BigDecimal("32.00"),
                temperatureConverterService.celsiusToFahrenheit(BigDecimal.ZERO).setScale(SCALE, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal("212.00"),
                temperatureConverterService.celsiusToFahrenheit(new BigDecimal("100")).setScale(SCALE, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal("-40.00"),
                temperatureConverterService.celsiusToFahrenheit(new BigDecimal("-40")).setScale(SCALE, RoundingMode.HALF_UP));
    }

    // Celsius to Kelvin tests
    @Test
    public void testCelsiusToKelvin1() {
        assertEquals(new BigDecimal("273.15"),
                temperatureConverterService.celsiusToKelvin(BigDecimal.ZERO));
        assertEquals(new BigDecimal("373.15"),
                temperatureConverterService.celsiusToKelvin(new BigDecimal("100")));
        assertEquals(new BigDecimal("233.15"),
                temperatureConverterService.celsiusToKelvin(new BigDecimal("-40")));
    }

    // Fahrenheit to Celsius tests
    @Test
    public void testFahrenheitToCelsius1() {
        assertEquals(new BigDecimal("0.00"),
                temperatureConverterService.fahrenheitToCelsius(new BigDecimal("32")).setScale(SCALE, RoundingMode.HALF_UP));
        assertEquals(0,
                temperatureConverterService.fahrenheitToCelsius(new BigDecimal("212")).setScale(SCALE, RoundingMode.HALF_UP).compareTo(BigDecimal.valueOf(100.0)));
        assertEquals(new BigDecimal("-40.00"),
                temperatureConverterService.fahrenheitToCelsius(new BigDecimal("-40")).setScale(SCALE, RoundingMode.HALF_UP));
    }

    // Fahrenheit to Kelvin tests
    @Test
    public void testFahrenheitToKelvin1() {
        assertEquals(new BigDecimal("273.15"),
                temperatureConverterService.fahrenheitToKelvin(new BigDecimal("32")).setScale(SCALE, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal("373.15"),
                temperatureConverterService.fahrenheitToKelvin(new BigDecimal("212")).setScale(SCALE, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal("233.15"),
                temperatureConverterService.fahrenheitToKelvin(new BigDecimal("-40")).setScale(SCALE, RoundingMode.HALF_UP));
    }

    // Kelvin to Celsius tests
    @Test
    public void testKelvinToCelsius1() {
        assertEquals(new BigDecimal("0.00"),
                temperatureConverterService.kelvinToCelsius(new BigDecimal("273.15")).setScale(SCALE, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal("100.00"),
                temperatureConverterService.kelvinToCelsius(new BigDecimal("373.15")).setScale(SCALE, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal("-40.00"),
                temperatureConverterService.kelvinToCelsius(new BigDecimal("233.15")).setScale(SCALE, RoundingMode.HALF_UP));
    }

    // Kelvin to Fahrenheit tests
    @Test
    public void testKelvinToFahrenheit1() {
        assertEquals(new BigDecimal("32.00"),
                temperatureConverterService.kelvinToFahrenheit(new BigDecimal("273.15")).setScale(SCALE, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal("212.00"),
                temperatureConverterService.kelvinToFahrenheit(new BigDecimal("373.15")).setScale(SCALE, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal("-40.00"),
                temperatureConverterService.kelvinToFahrenheit(new BigDecimal("233.15")).setScale(SCALE, RoundingMode.HALF_UP));
    }

    // Test extreme temperatures
    @Test
    public void testExtremeTemperatures() {
        // Absolute Zero in Kelvin (0K = -273.15°C = -459.67°F)
        BigDecimal absoluteZeroKelvin = BigDecimal.ZERO;
        BigDecimal absoluteZeroCelsius = new BigDecimal("-273.15");
        BigDecimal absoluteZeroFahrenheit = new BigDecimal("-459.67");

        assertEquals(absoluteZeroCelsius.setScale(SCALE, RoundingMode.HALF_UP),
                temperatureConverterService.kelvinToCelsius(absoluteZeroKelvin).setScale(SCALE, RoundingMode.HALF_UP));
        assertEquals(absoluteZeroFahrenheit.setScale(SCALE, RoundingMode.HALF_UP),
                temperatureConverterService.kelvinToFahrenheit(absoluteZeroKelvin).setScale(SCALE, RoundingMode.HALF_UP));
    }

    // Parameterized test for multiple scenarios
    @ParameterizedTest
    @MethodSource("provideTemperatureTestCases")
    void testTemperatureConversions(String conversionType, BigDecimal input, BigDecimal expected) {
        BigDecimal result;
        switch (conversionType) {
            case "C->F" -> result = temperatureConverterService.celsiusToFahrenheit(input);
            case "C->K" -> result = temperatureConverterService.celsiusToKelvin(input);
            case "F->C" -> result = temperatureConverterService.fahrenheitToCelsius(input);
            case "F->K" -> result = temperatureConverterService.fahrenheitToKelvin(input);
            case "K->C" -> result = temperatureConverterService.kelvinToCelsius(input);
            case "K->F" -> result = temperatureConverterService.kelvinToFahrenheit(input);
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
                Arguments.of("F->C", new BigDecimal("68"), new BigDecimal("20.00")),
                Arguments.of("F->K", new BigDecimal("68"), new BigDecimal("293.15")),
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

    // Individual Conversion Method Tests
    @Test
    void testCelsiusToFahrenheit() {
        BigDecimal celsius = new BigDecimal("0");
        BigDecimal expected = new BigDecimal("32");
        assertEquals(expected, temperatureConverterService.celsiusToFahrenheit(celsius));

        celsius = new BigDecimal("100");
        expected = new BigDecimal("212");
        assertEquals(expected, temperatureConverterService.celsiusToFahrenheit(celsius));
    }

    @Test
    void testCelsiusToKelvin() {
        BigDecimal celsius = new BigDecimal("0");
        BigDecimal expected = new BigDecimal("273.15");
        assertEquals(expected, temperatureConverterService.celsiusToKelvin(celsius));

        celsius = new BigDecimal("100");
        expected = new BigDecimal("373.15");
        assertEquals(expected, temperatureConverterService.celsiusToKelvin(celsius));
    }

    @Test
    void testFahrenheitToCelsius() {
        BigDecimal fahrenheit = new BigDecimal("32");
        BigDecimal expected = new BigDecimal("0");
        assertEquals(0, temperatureConverterService.fahrenheitToCelsius(fahrenheit).compareTo(expected));

        fahrenheit = new BigDecimal("212");
        expected = new BigDecimal("100");
        assertEquals(0, temperatureConverterService.fahrenheitToCelsius(fahrenheit).compareTo(expected));
    }

    @Test
    void testFahrenheitToKelvin() {
        BigDecimal fahrenheit = new BigDecimal("32");
        BigDecimal expected = new BigDecimal("273.15");
        assertEquals(expected, temperatureConverterService.fahrenheitToKelvin(fahrenheit));

        fahrenheit = new BigDecimal("212");
        expected = new BigDecimal("373.15");
        assertEquals(expected, temperatureConverterService.fahrenheitToKelvin(fahrenheit));
    }

    @Test
    void testKelvinToCelsius() {
        BigDecimal kelvin = new BigDecimal("273.15");
        BigDecimal expected = new BigDecimal("0");
        assertEquals(0, temperatureConverterService.kelvinToCelsius(kelvin).compareTo(expected));

        kelvin = new BigDecimal("373.15");
        expected = new BigDecimal("100");
        assertEquals(0, temperatureConverterService.kelvinToCelsius(kelvin).compareTo(expected));
    }

    @Test
    void testKelvinToFahrenheit() {
        BigDecimal kelvin = new BigDecimal("273.15");
        BigDecimal expected = new BigDecimal("32");
        assertEquals(0, temperatureConverterService.kelvinToFahrenheit(kelvin).compareTo(expected));

        kelvin = new BigDecimal("373.15");
        expected = new BigDecimal("212");
        assertEquals(0, temperatureConverterService.kelvinToFahrenheit(kelvin).compareTo(expected));
    }

    // Conversion Method Tests with Different Units
    @Test
    void testConvertTemperature() throws Exception {
        // Celsius to Fahrenheit
        BigDecimal result = temperatureConverterService.convertTemperature("C", "F", new BigDecimal("0"));
        assertEquals(new BigDecimal("32"), result);

        // Celsius to Kelvin
        result = temperatureConverterService.convertTemperature("C", "K", new BigDecimal("0"));
        assertEquals(new BigDecimal("273.15"), result);

        // Fahrenheit to Celsius
        result = temperatureConverterService.convertTemperature("F", "C", new BigDecimal("32"));
        assertEquals(0, result.compareTo(BigDecimal.ZERO));

        // Fahrenheit to Kelvin
        result = temperatureConverterService.convertTemperature("F", "K", new BigDecimal("32"));
        assertEquals(new BigDecimal("273.15"), result);

        // Kelvin to Celsius
        result = temperatureConverterService.convertTemperature("K", "C", new BigDecimal("273.15"));
        assertEquals(0, result.compareTo(BigDecimal.ZERO));

        // Kelvin to Fahrenheit
        result = temperatureConverterService.convertTemperature("K", "F", new BigDecimal("273.15"));
        assertEquals(0, result.compareTo(BigDecimal.valueOf(32)));
    }

    // Exception Handling Tests
    @Test
    void testConvertTemperatureInvalidFromUnit() {
        Exception exception = assertThrows(Exception.class, () -> temperatureConverterService.convertTemperature("X", "F", new BigDecimal("0")));
        assertEquals("Invalid fromUnit: X", exception.getMessage());
    }

    @Test
    void testConvertTemperatureInvalidToUnit() {
        Exception exception = assertThrows(Exception.class, () -> temperatureConverterService.convertTemperature("C", "X", new BigDecimal("0")));
        assertEquals("Invalid toUnit: X", exception.getMessage());
    }

}