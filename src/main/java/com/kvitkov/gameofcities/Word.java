package com.kvitkov.gameofcities;

import org.jetbrains.annotations.NotNull;

public class Word extends WordSignature {
    @NotNull
    public final String value;
    public final int hash;


    public Word(@NotNull final String value) {
        super(value.charAt(0), getLastLetter(value));
        this.value = value;
        this.hash = this.value.hashCode();
    }


    public static Character getLastLetter(String word) {
        if (word.length() == 0) throw new IllegalStateException();
        char lastChar = word.charAt(word.length() - 1);
        if (lastChar == 'Ь' || lastChar == 'Ъ') return getLastLetter(word.substring(0, word.length() - 1));
        return lastChar;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return hash == word.hash && value.equals(word.value);
    }


    @Override
    public int hashCode() {
        return hash;
    }


    @Override
    public String toString() {
        return String.format("%s   {%c%c,%d}", value, first, last, code);
    }
}
