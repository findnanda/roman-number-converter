package uk.co.roman.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RomanLiteralConverterTest {

   private RomanLiteralConverter romanLiteralConverter;

   @BeforeEach
   void before(){
       romanLiteralConverter = new RomanLiteralConverter();
   }
    @Test
    void testRomanNumber(){
        final var result = romanLiteralConverter.romanNumber(20);
        assertEquals("XX", result);
    }
}
