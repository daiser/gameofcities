package com.kvitkov.gameofcities.contracts;

public interface Ai extends Player {
    String takeTurn(Character startsWith, final UsedWords usedWords) throws GiveUpException;
}
