package uk.co.roman.converter;

import java.util.Comparator;

import static uk.co.roman.converter.RomanDictionary.*;

public class RomanLiteralConverter {

    public String toRomanNumber(int number) {
        //find num length
        return number > 0 && number <= 5000 ? buildRomanNumber(number, MOD_KEY.apply(number)) : "InvalidNumber or beyond range";
    }

    public Integer toInteger(String romanNumber){
        int retVal = 0;
        int index = 0;
        //find the length here and identify each character
        while(index < romanNumber.length()){
            //find a two digits first
            int val = RomanDictionary.lookUpDictionary(romanNumber.substring(index, Math.min(index + 2, romanNumber.length())));
            if(val == -1){
                retVal =  retVal + RomanDictionary.lookUpDictionary(romanNumber.charAt(index));
                index++;
            } else {
                retVal = retVal + val;
                index = index + 2;
            }
        }
    return retVal;
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
}
