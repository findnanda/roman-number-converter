package uk.co.roman.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RomanLiteralConverterTest {

   private RomanLiteralConverter romanLiteralConverter;

   @BeforeEach
   void before(){
       romanLiteralConverter = new RomanLiteralConverter();
   }
    @ParameterizedTest(name = "#{index} - Run test with args={0}")
    @CsvSource({"20, XX", "50, L", "45, XLIV", "100, C", "150, CL"})
    void testToRomanNumber(int value, String romanNumber){
        final var result = romanLiteralConverter.toRomanNumber(value);
        assertEquals(romanNumber, result);
    }

}
