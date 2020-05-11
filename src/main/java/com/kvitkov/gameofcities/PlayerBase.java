package com.kvitkov.gameofcities;

import com.kvitkov.gameofcities.contracts.GameWordsSet;
import com.kvitkov.gameofcities.contracts.Player;
import com.kvitkov.gameofcities.contracts.PlayersDictionary;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public abstract class PlayerBase implements Player {
    private static int nextId = 0;


    private static int getNextId() {
        return ++nextId;
    }


    protected final int id;
    protected final double ability;
    protected final PlayersDictionary dictionary;


    protected PlayerBase(final GameWordsSet allWords, final double ability, Random rnd) {
        this(getNextId(), allWords, ability, rnd);
    }


    protected PlayerBase(int id, final GameWordsSet allWords, final double ability, Random rnd) {
        this.id = id;
        this.ability = ability;

        ArrayList<Word> words = new ArrayList<>();
        for (Word word : allWords) words.add(word);
        Collections.shuffle(words, rnd);

        dictionary = new Dictionary(words.subList(0, (int) (words.size() * ability)));
    }


    public int getId() {
        return id;
    }


    protected static class Dictionary extends HashSet<Word> implements PlayersDictionary {
        private Dictionary(@NotNull Collection<Word> set) {
            super(set);
        }


        @Override
        public boolean contains(Word word) {
            return this.contains((Object) word);
        }
    }
}
