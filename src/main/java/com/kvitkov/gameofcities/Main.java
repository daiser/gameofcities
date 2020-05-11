package com.kvitkov.gameofcities;

import com.kvitkov.gameofcities.ai.Casual;
import com.kvitkov.gameofcities.ai.God;
import com.kvitkov.gameofcities.ai.Monkey;
import com.kvitkov.gameofcities.contracts.GameWordsSet;
import com.kvitkov.gameofcities.contracts.Player;

import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rnd = new Random();
        long seed = rnd.nextLong();
        rnd.setSeed(seed);

        System.out.println("Seed #" + seed);

        try {
            GameWordsSet allWords = new FileDictionary("data/cities.txt");

            Player winner = new Game().play(new Monkey(rnd, allWords), new Casual(rnd, allWords));
            System.out.println("GAME OVER!");
            System.out.println("Winner: " + winner);
        } catch (IOException iox) {
            System.err.println("Не удалось прочитать словарь");
        }
    }
}
