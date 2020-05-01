package com.kvitkov.gameofcities;

import com.kvitkov.gameofcities.contracts.Player;

public abstract class PlayerBase implements Player {
    private static int nextId = 0;


    private static int getNextId() {
        return ++nextId;
    }


    protected final int id;


    protected PlayerBase() {
        this(getNextId());
    }


    protected PlayerBase(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }
}
