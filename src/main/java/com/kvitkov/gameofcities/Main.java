package com.kvitkov.gameofcities;

import com.kvitkov.gameofcities.ai.Monkey;
import com.kvitkov.gameofcities.contracts.Player;

import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rnd = new Random();
        try {
            FileDictionary fileDictionary = new FileDictionary("s:\\tmp\\cities.txt");
            Game game = new Game(fileDictionary, new Monkey(rnd), new Monkey(rnd), new Monkey(rnd), new Monkey(rnd));

            Player winner = game.play();
            System.out.println("GAME OVER!");
            System.out.println("Winner: " + winner);
        } catch (IOException iox) {
            System.err.println("Не удалось прочитать словарь");
        }
    }
}
