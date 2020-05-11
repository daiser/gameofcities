package com.kvitkov.gameofcities.ai;

import com.kvitkov.gameofcities.*;
import com.kvitkov.gameofcities.contracts.GameWordsSet;
import com.kvitkov.gameofcities.contracts.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Casual extends Monkey implements Player {
    public Casual(Random generator, GameWordsSet words, final double ability) {
        super(generator, words, ability);
    }


    public Casual(Random generator, GameWordsSet words) {
        this(generator, words, .6);
    }


    @Override
    public UsedWords.LegitMove takeTurn(Character firstLetter, UsedWords usedWords, final int ignored) throws
            GiveUpException {
        List<Word> possibleMoves = dictionary.selectExcluding(firstLetter, usedWords);
        if (possibleMoves.size() == 0) throw new GiveUpException(this);

        int minScore = Integer.MAX_VALUE;
        ArrayList<Word> bestMoves = new ArrayList<>();
        for (Word possibleMove : possibleMoves) {
            int score = dictionary.selectExcluding(possibleMove.last, usedWords).size() -
                        (firstLetter != null && firstLetter.equals(possibleMove.last) ? 1 : 0);
            if (score > minScore) continue;
            if (score < minScore) {
                minScore = score;
                bestMoves.clear();
            }
            bestMoves.add(possibleMove);
        }

        try {
            return usedWords.check(bestMoves.get(generator.nextInt(bestMoves.size())));
        } catch (IllegalMoveException imx) {
            throw new DeveloperException(imx);
        }
    }


    @Override
    public String toString() {
        return "Casual#" + id;
    }
}
