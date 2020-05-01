package com.kvitkov.gameofcities.ai;

import com.kvitkov.gameofcities.PlayerBase;
import com.kvitkov.gameofcities.contracts.Dictionary;
import com.kvitkov.gameofcities.contracts.GiveUpException;
import com.kvitkov.gameofcities.contracts.Player;

import java.util.ArrayList;
import java.util.Random;

public class Monkey extends PlayerBase implements Player {
    private final Random generator;


    public Monkey(Random generator) {
        this.generator = generator;
    }


    public String takeTurn(Character firstLetter, Dictionary allWords, Dictionary usedWords) throws GiveUpException {
        ArrayList<String> possibleMoves = new ArrayList<String>();

        for (String word : allWords) {
            if (word.charAt(0) == firstLetter && !usedWords.contains(word)) possibleMoves.add(word);
        }
        if (possibleMoves.size() == 0) throw new GiveUpException(this);
        return possibleMoves.get(generator.nextInt(possibleMoves.size()));
    }


    @Override
    public String toString() {
        return "Monkey#" + id; // Monkey#6
    }
}
