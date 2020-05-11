package com.kvitkov.gameofcities;

import com.kvitkov.gameofcities.contracts.Dictionary;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Iterator;

public class UsedWords implements Dictionary {
    private final HashSet<Word> usedWords = new HashSet<>();


    public void clear() {
        usedWords.clear();
    }


    @SuppressWarnings("UnusedReturnValue")
    public boolean add(@NotNull final LegitMove move) {
        return usedWords.add(move.value);
    }


    public boolean contains(final Word word) {
        return usedWords.contains(word);
    }


    public final LegitMove check(@NotNull final Word word) throws IllegalMoveException {
        if (contains(word)) throw new WordAlreadyTakenException();
        try {
            return new LegitMove(word);
        } catch (IllegalStateException isx) {
            throw new IllegalSymbolsException();
        }
    }


    @NotNull
    @Override
    public Iterator<Word> iterator() {
        return usedWords.iterator();
    }


    public int size() {return usedWords.size();}


    public static class LegitMove {
        public final Word value;
        public final char nextLetter;


        private LegitMove(final Word value) {
            this.value = value;
            this.nextLetter = value.last;
        }
    }
}
