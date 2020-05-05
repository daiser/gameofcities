package com.kvitkov.gameofcities.contracts;

public interface Human extends Player {
    String takeTurn(Character startsWith) throws GiveUpException;
}

