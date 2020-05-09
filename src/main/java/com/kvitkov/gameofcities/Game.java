package com.kvitkov.gameofcities;

import com.kvitkov.gameofcities.contracts.*;

import java.util.ArrayList;

public class Game implements com.kvitkov.gameofcities.contracts.Game {
    private final UsedWords usedWords;


    public Game() {
        this.usedWords = new UsedWords();
    }


    public Player play(Player... players) {
        usedWords.clear();
        int turn = 0;
        Character lastLetter = null;
        ArrayList<Player> losers = new ArrayList<>();

        while (true) {
            int activePlayerIndex = turn % players.length;
            Player activePlayer = players[activePlayerIndex];

            if (!losers.contains(activePlayer)) {
                if (losers.size() == players.length - 1) {
                    System.out.println("Moves:" + usedWords.size());
                    return activePlayer;
                }
                try {
                    UsedWords.LegitMove legitMove = activePlayer.takeTurn(lastLetter,
                                                                          usedWords,
                                                                          players.length - losers.size());
                    System.out.println(activePlayer + ": " + legitMove.value);
                    usedWords.add(legitMove);
                    lastLetter = legitMove.nextLetter;
                } catch (GiveUpException gux) {
                    System.out.println(gux.player + ": gg");
                    losers.add(gux.player);
                }
            }
            turn++;
        }
    }
}
