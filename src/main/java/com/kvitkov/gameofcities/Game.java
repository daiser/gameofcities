package com.kvitkov.gameofcities;

import com.kvitkov.gameofcities.contracts.Dictionary;
import com.kvitkov.gameofcities.contracts.Player;
import com.kvitkov.gameofcities.contracts.TemporaryDictionary;

public class Game implements com.kvitkov.gameofcities.contracts.Game {
    private final Dictionary allWords;
    private final TemporaryDictionary usedWords;
    private final Player[] players;


    public Game(Dictionary allWords, Player... players) {
        this.allWords = allWords;
        this.players = players;
        this.usedWords = new TeDi();
    }


    public Player play() {
        return null;
    }


    public void restart() {
        usedWords.clear();
    }
}
