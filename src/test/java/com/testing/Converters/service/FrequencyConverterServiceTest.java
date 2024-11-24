package com.testing.Converters.service;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class FrequencyConverterServiceTest {
    private final FrequencyConverterService frequencyConverterService = new FrequencyConverterService();

    @Test
    void testConvertHertzToKilohertz() {
        BigDecimal result = frequencyConverterService.convertFrequency("hertz", "kilohertz", BigDecimal.valueOf(1000));
        assertEquals(BigDecimal.valueOf(1), result);
    }

    @Test
    void testConvertKilohertzToHertz() {
        BigDecimal result = frequencyConverterService.convertFrequency("kilohertz", "hertz", BigDecimal.valueOf(1));
        assertEquals(BigDecimal.valueOf(1000), result);
    }

    @Test
    void testConvertMegahertzToGigahertz() {
        BigDecimal result = frequencyConverterService.convertFrequency("megahertz", "gigahertz", BigDecimal.valueOf(1000));
        assertEquals(0, result.compareTo(BigDecimal.valueOf(1)));
    }

    @Test
    void testConvertGigahertzToHertz() {
        BigDecimal result = frequencyConverterService.convertFrequency("gigahertz", "hertz", BigDecimal.valueOf(1));
        assertEquals(BigDecimal.valueOf(1e+9), result);
    }

    @Test
    void testInvalidFromUnit() {
        BigDecimal result = frequencyConverterService.convertFrequency("invalidUnit", "hertz", BigDecimal.valueOf(100));
        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    void testInvalidToUnit() {
        BigDecimal result = frequencyConverterService.convertFrequency("hertz", "invalidUnit", BigDecimal.valueOf(100));
        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    void testBoundaryCaseZeroValue() {
        BigDecimal result = frequencyConverterService.convertFrequency("hertz", "kilohertz", BigDecimal.ZERO);
        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    void testNegativeValueConversion() {
        BigDecimal result = frequencyConverterService.convertFrequency("hertz", "megahertz", BigDecimal.valueOf(-1000000));
        assertEquals(BigDecimal.valueOf(-1), result);
    }
}