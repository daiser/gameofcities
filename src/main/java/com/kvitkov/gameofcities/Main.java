package com.kvitkov.gameofcities;

import com.kvitkov.gameofcities.ai.Monkey;
import com.kvitkov.gameofcities.contracts.AllWords;
import com.kvitkov.gameofcities.contracts.Player;

import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rnd = new Random();
        try {
            AllWords allWords = new FileDictionary("s:\\tmp\\cities.txt");
//            Dictionary allWords = new DbDictionary();
            Game game = new Game(
                    new Monkey(rnd, allWords),
                    new Monkey(rnd, allWords),
                    new Monkey(rnd, allWords, 0.5),
                    new Monkey(rnd, allWords));

            Player winner = game.play();
            System.out.println("GAME OVER!");
            System.out.println("Winner: " + winner);
        } catch (IOException iox) {
            System.err.println("Не удалось прочитать словарь");
        }
    }
}
