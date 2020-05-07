package com.kvitkov.gameofcities.ai;

import com.kvitkov.gameofcities.IllegalMoveException;
import com.kvitkov.gameofcities.PlayerBase;
import com.kvitkov.gameofcities.UsedWords;
import com.kvitkov.gameofcities.contracts.AllWords;
import com.kvitkov.gameofcities.GiveUpException;
import com.kvitkov.gameofcities.contracts.Player;

import java.util.ArrayList;
import java.util.Random;

public class Monkey extends PlayerBase implements Player {
    protected final Random generator;


    public Monkey(Random generator, AllWords words, final double ability) {
        super(words, ability, generator);
        this.generator = generator;
    }


    public Monkey(Random generator, AllWords words) {
        this(generator, words, .2);
    }


    @Override
    public UsedWords.LegitMove takeTurn(Character startsWith, UsedWords usedWords) throws GiveUpException {
        ArrayList<String> possibleMoves = getPossibleMoves(startsWith, usedWords);
        if (possibleMoves.size() == 0) throw new GiveUpException(this);
        try {
            return usedWords.check(possibleMoves.get(generator.nextInt(possibleMoves.size())));
        } catch (IllegalMoveException imx) {
            throw new RuntimeException("Код писала обезъяна");
        }
    }


    protected ArrayList<String> getPossibleMoves(Character startsWith, UsedWords usedWords) {
        ArrayList<String> possibleMoves = new ArrayList<>();

        for (String word : dictionary) {
            if ((startsWith == null || word.charAt(0) == startsWith) && !usedWords.contains(word))
                possibleMoves.add(word);
        }
        return possibleMoves;
    }


    @Override
    public String toString() {
        return "Monkey#" + id; // Monkey#6
    }
}
