package com.kvitkov.gameofcities;

import org.jetbrains.annotations.NotNull;

public class Word {
    @NotNull
    public final String value;
    public final char first;
    public final char last;
    @NotNull
    public final String meaning;
    public final int signature;
    public final int hash;


    public Word(@NotNull final String value) {
        this.value = value;
        this.first = value.charAt(0);
        this.last = Utility.getLastLetter(value);
        this.meaning = "" + first + last;
        this.signature = last << 16 + first;
        this.hash = this.value.hashCode();
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
        return "Word{" + value + "}";
    }
}
