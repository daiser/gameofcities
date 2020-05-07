package com.kvitkov.gameofcities;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

public class UsedWords {
    private final HashSet<String> usedWords = new HashSet<>();


    public void clear() {
        usedWords.clear();
    }


    @SuppressWarnings("UnusedReturnValue")
    public boolean add(@NotNull final LegitMove move) {
        return usedWords.add(move.value);
    }


    public boolean contains(final String word) {
        return usedWords.contains(word);
    }


    public final LegitMove check(final String word) throws IllegalMoveException {
        if (contains(word)) throw new WordAlreadyTakenException();
        try {
            return new LegitMove(word);
        } catch (IllegalStateException isx) {
            throw new IllegalSymbolsException();
        }
    }


    public static class LegitMove {
        public final String value;
        public final char nextLetter;


        private LegitMove(final String value) {
            this.value = value;
            this.nextLetter = Utility.getLastLetter(value);
        }
    }
}
