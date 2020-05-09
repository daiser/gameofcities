package com.kvitkov.gameofcities;

import com.kvitkov.gameofcities.contracts.AllWords;
import com.kvitkov.gameofcities.contracts.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class PlayerBase implements Player {
    private static int nextId = 0;


    private static int getNextId() {
        return ++nextId;
    }


    protected final int id;
    protected final double ability;
    protected final List<Word> dictionary;


    protected PlayerBase(final AllWords allWords, final double ability, Random rnd) {
        this(getNextId(), allWords, ability, rnd);
    }


    protected PlayerBase(int id, final AllWords allWords, final double ability, Random rnd) {
        this.id = id;
        this.ability = ability;

        ArrayList<Word> words = new ArrayList<>();
        for (Word word : allWords) words.add(word);
        Collections.shuffle(words, rnd);

        int n = (int) (words.size() * ability);
        dictionary = Collections.unmodifiableList(words.subList(0, n));
    }


    public int getId() {
        return id;
    }
}
