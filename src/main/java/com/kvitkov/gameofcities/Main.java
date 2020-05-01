package com.kvitkov.gameofcities;

import com.kvitkov.gameofcities.ai.Monkey;
import com.kvitkov.gameofcities.contracts.Player;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rnd = new Random();
        Game game = new Game(new EmptyDictionary(), new Monkey(rnd), new Monkey(rnd), new Monkey(rnd), new Monkey(rnd));

        Player winner = game.play();
        System.out.println("GAME OVER!");
        System.out.println("Winner: " + winner);
    }
}
