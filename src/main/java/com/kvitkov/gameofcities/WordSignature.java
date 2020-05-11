package com.kvitkov.gameofcities;

public class WordSignature {
    public final char first;
    public final char last;
    public final int code;


    public WordSignature(char firstLetter, char lastLetter) {
        this.first = subst(firstLetter);
        this.last = subst(lastLetter);
        this.code = (first << 16) + last;
    }


    private static char subst(char in) {
        switch (in) {
            case 'Й':
                return 'И';
            case 'Ё':
                return 'Е';
            default:
                return in;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordSignature that = (WordSignature) o;
        return code == that.code;
    }


    @Override
    public int hashCode() {
        return code;
    }
}
