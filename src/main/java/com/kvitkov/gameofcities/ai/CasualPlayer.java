package com.kvitkov.gameofcities.ai;

import com.kvitkov.gameofcities.*;
import com.kvitkov.gameofcities.contracts.AllWords;
import com.kvitkov.gameofcities.contracts.Player;

import java.util.*;

public class CasualPlayer extends Monkey implements Player {
    public CasualPlayer(Random generator, AllWords words, final double ability) {
        super(generator, words, ability);
    }


    public CasualPlayer(Random generator, AllWords words) {
        super(generator, words, .6);
    }


    @Override
    public UsedWords.LegitMove takeTurn(Character firstLetter, UsedWords usedWords, final int ignored) throws
            GiveUpException {
        List<Word> allPossibleMoves = getPossibleMoves(firstLetter, usedWords);
        if (allPossibleMoves.size() == 0) throw new GiveUpException(this);

        HashMap<Character, Integer> letterScores = new HashMap<>();
        for (Word possibleMove : allPossibleMoves) {
            char lastLetter = possibleMove.last;
            if (letterScores.containsKey(lastLetter)) continue;
            letterScores.put(lastLetter,
                             getPossibleMoves(lastLetter, usedWords).size() -
                             (firstLetter != null && firstLetter.equals(lastLetter) ? 1 : 0));
//            letterScores.put(lastLetter, getPossibleMoves(lastLetter, usedWords).size());
        }
        int bestScore = Collections.min(letterScores.values());

        ArrayList<Word> bestMoves = new ArrayList<>();
        for (Map.Entry<Character, Integer> letterScore : letterScores.entrySet()) {
            if (letterScore.getValue() != bestScore) continue;
            for (Word possibleMove : allPossibleMoves) {
                if (!letterScore.getKey().equals(possibleMove.last)) continue;
                bestMoves.add(possibleMove);
            }
        }

        try {
            return usedWords.check(bestMoves.get(generator.nextInt(bestMoves.size())));
        } catch (IllegalMoveException imx) {
            throw new RuntimeException("Код писала обезъяна");
        }
    }


    @Override
    public String toString() {
        return "Casual#" + id;
    }
}
