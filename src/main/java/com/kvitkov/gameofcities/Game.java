package com.kvitkov.gameofcities;

import com.kvitkov.gameofcities.contracts.*;

import java.util.ArrayList;

public class Game implements com.kvitkov.gameofcities.contracts.Game {
    private final UsedWords usedWords;
    private final Player[] players;


    public Game(Player... players) {
        this.players = players;
        this.usedWords = new TeDi();
    }


    public Player play() {
        int turn = 0;
        Character lastLetter = null;
        ArrayList<Player> losers = new ArrayList<>();

        while (true) {
            int activePlayerIndex = turn % players.length;
            Player activePlayer = players[activePlayerIndex];

            if (!losers.contains(activePlayer)) {
                if (losers.size() == players.length - 1) {
                    System.out.println("Moves:" + turn);
                    return activePlayer;
                }
                try {
                    while (true) {
                        String word;
                        if (activePlayer instanceof Human) {
                            Human humanPlayer = (Human) activePlayer;
                            word = humanPlayer.takeTurn(lastLetter);
                        } else {
                            Ai aiPlayer = (Ai) activePlayer;
                            word = aiPlayer.takeTurn(lastLetter, usedWords);
                        }
                        String cleanWord = word.trim().toUpperCase();
                        if (lastLetter != null && cleanWord.charAt(0) != lastLetter) {
                            if (!(activePlayer instanceof Human)) {
                                throw new IllegalStateException("Ai made incorrect move");
                            } else {
                                continue;
                            }
                        }
                        if (!usedWords.add(cleanWord)) {
                            if (!(activePlayer instanceof Human)) {
                                throw new IllegalStateException("Ai made incorrect move");
                            } else {
                                continue;
                            }
                        }
                        System.out.println(activePlayer + ": " + cleanWord);
                        try {
                            lastLetter = Utility.getLastLetter(cleanWord);
                        } catch (IllegalStateException isx) {
                            if (!(activePlayer instanceof Human)) {
                                throw new IllegalStateException("Ai told invalid word");
                            } else {
                                continue;
                            }
                        }
                        break;
                    }
                } catch (GiveUpException gux) {
                    System.out.println(gux.player + ": gg");
                    losers.add(gux.player);
                }
            }

            turn++;
        }
    }


    public void restart() {
        usedWords.clear();
    }
}
