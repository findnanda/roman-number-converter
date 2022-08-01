package uk.co.roman.converter;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;

public class RomanLiteralConverter {
    private static final Map<Integer, String> ROMAN_PATTERN;
    private static final List<String> ROMAN_NUMERALS;
    private static final Map<String, Integer> INTEGER_ROMAN_PATTERN;
    private static final Function<Integer, Integer> MOD_KEY = index -> IntStream.range(1, String.valueOf(index).length())
            .map(i -> 10 * 1).reduce(1, (v1, v2) -> v1 * v2);
    private static final BiFunction<Integer, Integer, String> APPENDER;

    public String toRomanNumber(int number) {
        //find num length
        return number > 0 && number <= 5000 ? buildRomanNumber(number, MOD_KEY.apply(number)) : "InvalidNumber or beyond range";
    }

    public Integer toInteger(String romanNumber){
    return 0;
    }

    private String buildRomanNumber(int number, int modKey) {
        String result = "";
        if (ROMAN_PATTERN.containsKey(number)) {
            return ROMAN_PATTERN.get(number);
        }
        //find the modulus using the modkey, e.g 101/100 = 1
        final int modulus = number % modKey;
        //get the units, tens, hundreds etc, 101 - modulus = 100
        final int digits = number - modulus;
        //find the matching or nearest possible value
        final var lastLowestRomanKey = ROMAN_PATTERN.keySet().stream()
                .filter(key -> key <= digits).max(Comparator.comparingInt(k -> k));
        //subtract remaining value and count number of items to append
        final int appendCount = (digits - lastLowestRomanKey.get()) / modKey;

        if (String.valueOf(modulus).length() > 1) {
            result = buildRomanNumber(modulus, MOD_KEY.apply(modulus));
        } else {
            if (String.valueOf(modulus).length() == 1) {
                result = getSingleDigitRomanNumber(modulus);
            }
        }
        return ROMAN_PATTERN.get(lastLowestRomanKey.get()) + APPENDER.apply(appendCount, modKey) + result;
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
        APPENDER = (appendCount, modKey) -> IntStream.rangeClosed(1, appendCount)
                .mapToObj(index -> ROMAN_PATTERN.get(modKey))
                .reduce("", (v1, v2) -> v1 + v2);
    }

}
