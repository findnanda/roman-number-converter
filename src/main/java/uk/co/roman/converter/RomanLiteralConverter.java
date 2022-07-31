package uk.co.roman.converter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class RomanLiteralConverter {
    private static final Map<Integer, String> ROMAN_PATTERN;
    private static final List<String> ROMAN_NUMERALS;
    private static final Map<String, Integer> INTEGER_ROMAN_PATTERN;

    public String romanNumber(int number){

        return "";
    }

    private String getSingleDigitRomanNumber(int index) {

        return index == 0 ? "" : ROMAN_NUMERALS.get(index - 1);
    }


    static {
        ROMAN_PATTERN = new HashMap<>();
        ROMAN_PATTERN.put(1, "I");
        ROMAN_PATTERN.put(4, "IV");
        ROMAN_PATTERN.put(5, "V");
        ROMAN_PATTERN.put(9, "IX");
        ROMAN_PATTERN.put(10, "X");
        ROMAN_PATTERN.put(40, "XL");
        ROMAN_PATTERN.put(50, "L");
        ROMAN_PATTERN.put(90, "XC");
        ROMAN_PATTERN.put(100, "C");
        ROMAN_PATTERN.put(400, "CD");
        ROMAN_PATTERN.put(500, "D");
        ROMAN_PATTERN.put(900, "CM");
        ROMAN_PATTERN.put(1000, "M");
        ROMAN_PATTERN.put(4000, "MV̅");
        ROMAN_PATTERN.put(5000, "V̅");
        ROMAN_NUMERALS = Arrays.asList("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X");
        INTEGER_ROMAN_PATTERN = ROMAN_PATTERN.entrySet().stream().collect(toMap(Map.Entry::getValue, Map.Entry::getKey));
    }

}
