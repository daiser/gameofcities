package com.kvitkov.gameofcities.contracts;

import com.kvitkov.gameofcities.GiveUpException;
import com.kvitkov.gameofcities.UsedWords;

public interface Player {
    int getId();
    UsedWords.LegitMove takeTurn(Character startsWith, final UsedWords usedWords, final int playersNumber) throws
            GiveUpException;
}
