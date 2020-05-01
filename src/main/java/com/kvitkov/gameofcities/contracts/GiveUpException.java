package com.kvitkov.gameofcities.contracts;

public class GiveUpException extends Exception {
    public final Player player;


    public GiveUpException(Player player) {
        this.player = player;
    }
}
