package uk.co.roman.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RomanLiteralConverterTest {

   private RomanLiteralConverter romanLiteralConverter;

   @BeforeEach
   void before(){
       romanLiteralConverter = new RomanLiteralConverter();
   }
    @ParameterizedTest(name = "#{index} - Run test with args={0}")
    @CsvSource({"20, XX", "50, L", "45, XLV", "100, C", "150, CL"})
    void testToRomanNumberValidInteger(int value, String romanNumber){
        final var result = romanLiteralConverter.toRomanNumber(value);
        assertEquals(romanNumber, result);
    }

    @ParameterizedTest(name = "#{index} - Run test with args={0}")
    @CsvSource({"-1, InvalidNumber or beyond range", "0, InvalidNumber or beyond range", "5001, InvalidNumber or beyond range"})
    void testToRomanNumberForInvalidNumber(int value, String romanNumber){
        final var result = romanLiteralConverter.toRomanNumber(value);
        assertEquals(romanNumber, result);
    }

    @ParameterizedTest(name = "#{index} - Run test with args={0}")
    @CsvSource({"XX, 20", "L, 50", "XLV, 45", "CL, 150"})
    void testToInteger(String romanNumber, int value){
        final var result = romanLiteralConverter.toInteger(romanNumber);
        assertEquals(value, result);
    }

    @ParameterizedTest(name = "#{index} - Run test with args={0}")
    @CsvSource({"ABC, -1", ", -1"})
    void testToIntegerForInvalidNumber(String romanNumber, int value){
        final var result = romanLiteralConverter.toInteger(romanNumber);
        assertEquals(value, result);
    }
}
