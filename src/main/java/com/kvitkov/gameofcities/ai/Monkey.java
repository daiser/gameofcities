package com.kvitkov.gameofcities.ai;

import com.kvitkov.gameofcities.*;
import com.kvitkov.gameofcities.contracts.GameWordsSet;
import com.kvitkov.gameofcities.contracts.Player;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class Monkey extends PlayerBase implements Player {
    protected final Random generator;


    public Monkey(Random generator, GameWordsSet words, final double ability) {
        super(words, ability, generator);
        this.generator = generator;
    }


    public Monkey(Random generator, GameWordsSet words) {
        this(generator, words, .2);
    }


    @Override
    public UsedWords.LegitMove takeTurn(Character firstLetter, UsedWords usedWords, final int ignored) throws
            GiveUpException {
        List<Word> possibleMoves = dictionary.selectExcluding(firstLetter, usedWords);
        if (possibleMoves.size() == 0) throw new GiveUpException(this);
        try {
            return usedWords.check(possibleMoves.get(generator.nextInt(possibleMoves.size())));
        } catch (IllegalMoveException imx) {
            throw new DeveloperException(imx);
        }
    }


    @Override
    public String toString() {
        return "Monkey#" + id;
    }
}
