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

    @Test
    void testToInteger(){
        final var result = romanLiteralConverter.toInteger("XI");
        assertEquals(11, result);
    }
}
