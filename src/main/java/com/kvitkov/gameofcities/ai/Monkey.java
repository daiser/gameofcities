package com.kvitkov.gameofcities.ai;

import com.kvitkov.gameofcities.PlayerBase;
import com.kvitkov.gameofcities.contracts.*;

import java.util.ArrayList;
import java.util.Random;

public class Monkey extends PlayerBase implements Ai {
    private final Random generator;


    public Monkey(Random generator, AllWords words, final double ability) {
        super(words, ability, generator);
        this.generator = generator;
    }


    public Monkey(Random generator, AllWords words) {
        this(generator, words, .25);
    }


    @Override
    public String takeTurn(Character startsWith, UsedWords usedWords) throws GiveUpException {
        ArrayList<String> possibleMoves = new ArrayList<>();

        for (String word : dictionary) {
            if ((startsWith == null || word.charAt(0) == startsWith) && !usedWords.contains(word))
                possibleMoves.add(word);
        }
        if (possibleMoves.size() == 0) throw new GiveUpException(this);
        return possibleMoves.get(generator.nextInt(possibleMoves.size()));
    }


    @Override
    public String toString() {
        return "Monkey#" + id; // Monkey#6
    }
}
