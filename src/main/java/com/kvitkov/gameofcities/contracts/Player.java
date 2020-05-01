package com.kvitkov.gameofcities.contracts;

public interface Player {
    int getId();

    String takeTurn(Character firstLetter, Dictionary allWords, Dictionary usedWords) throws GiveUpException;
}
