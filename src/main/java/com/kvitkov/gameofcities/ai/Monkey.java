package com.kvitkov.gameofcities.ai;

import com.kvitkov.gameofcities.*;
import com.kvitkov.gameofcities.contracts.AllWords;
import com.kvitkov.gameofcities.contracts.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
    public UsedWords.LegitMove takeTurn(Character startsWith, UsedWords usedWords, final int ignored) throws
            GiveUpException {
        List<Word> possibleMoves = getPossibleMoves(startsWith, usedWords);
        if (possibleMoves.size() == 0) throw new GiveUpException(this);
        try {
            return usedWords.check(possibleMoves.get(generator.nextInt(possibleMoves.size())));
        } catch (IllegalMoveException imx) {
            throw new RuntimeException("Код писала обезъяна");
        }
    }


    protected List<Word> getPossibleMoves(Character startsWith, UsedWords usedWords) {
        return dictionary.stream()
                         .filter(word -> (startsWith == null || word.first == startsWith) && !usedWords.contains(word))
                         .collect(Collectors.toList());
//
//        ArrayList<Word> possibleMoves = new ArrayList<>();
//
//        for (Word word : dictionary) {
//            if ((startsWith == null || word.first == startsWith) && !usedWords.contains(word)) possibleMoves.add(word);
//        }
//        return possibleMoves;
    }


    @Override
    public String toString() {
        return "Monkey#" + id; // Monkey#6
    }
}
