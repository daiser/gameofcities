package com.kvitkov.gameofcities;

import com.kvitkov.gameofcities.contracts.Player;

public class GiveUpException extends Exception {
    public final Player player;


    public GiveUpException(Player player) {
        this.player = player;
    }
}
