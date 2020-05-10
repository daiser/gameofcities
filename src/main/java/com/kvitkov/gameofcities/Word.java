package com.kvitkov.gameofcities;

import org.jetbrains.annotations.NotNull;

public class Word extends WordSignature {
    @NotNull
    public final String value;
    public final int hash;


    public Word(@NotNull final String value) {
        super(value.charAt(0), Utility.getLastLetter(value));
        this.value = value;
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
