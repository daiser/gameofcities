package com.kvitkov.gameofcities;

public class WordSignature {
    public final char first;
    public final char last;
    public final String signature;
    public final int signatureCode;


    public WordSignature(char firstLetter, char lastLetter) {
        this.first = firstLetter;
        this.last = lastLetter;
        this.signature = "" + first + lastLetter;
        this.signatureCode = first << 16 + last;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordSignature that = (WordSignature) o;
        return signatureCode == that.signatureCode;
    }


    @Override
    public int hashCode() {
        return signatureCode;
    }
}
