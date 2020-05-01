package com.kvitkov.gameofcities;

public class Utility {
    public static Character getLastLetter(String word) {
        if (word.length() == 0) throw new IllegalStateException();
        Character lastChar = word.charAt(word.length() - 1);
        if (lastChar == 'Ь' || lastChar == 'Ъ')
            return getLastLetter(word.substring(0, word.length() - 1));
        return lastChar;
    }
}