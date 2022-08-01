package uk.co.roman.converter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;

public class RomanDictionary {

    static final Map<Integer, String> ROMAN_PATTERN;

    static final List<String> ROMAN_NUMERALS;

    static final Map<String, Integer> INTEGER_ROMAN_PATTERN;

    static final BiFunction<Integer, Integer, String> APPENDER;

    static final Function<Integer, Integer> MOD_KEY = index -> IntStream.range(1, String.valueOf(index).length())
            .map(i -> 10 * 1).reduce(1, (v1, v2) -> v1 * v2);


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

    static int lookUpDictionary(char romanLiteral) {
        return INTEGER_ROMAN_PATTERN.containsKey(String.valueOf(romanLiteral)) ?
                INTEGER_ROMAN_PATTERN.get(String.valueOf(romanLiteral)) : -1;
    }

    static int lookUpDictionary(String multiDigitLiteral) {
        return INTEGER_ROMAN_PATTERN.containsKey(multiDigitLiteral) ?
                INTEGER_ROMAN_PATTERN.get(multiDigitLiteral) : -1;
    }
}
