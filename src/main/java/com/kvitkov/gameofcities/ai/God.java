package com.kvitkov.gameofcities.ai;

import com.kvitkov.gameofcities.*;
import com.kvitkov.gameofcities.contracts.GameWordsSet;
import com.kvitkov.gameofcities.contracts.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Don't try this player if you're not immortal.
 */
public class God extends Monkey implements Player {
    private UsedWords usedWords;


    public God(Random generator, GameWordsSet words) {
        super(generator, words, 1);
    }


    @Override
    public UsedWords.LegitMove takeTurn(Character firstLetter, UsedWords usedWords, final int numberOfPlayers) throws
            GiveUpException {
        this.usedWords = usedWords;
        List<Word> possibleMoves = dictionary.selectExcluding(firstLetter, usedWords);
        if (possibleMoves.size() == 0) throw new GiveUpException(this);

        ArrayList<Word> bestMoves = new ArrayList<>();
        double bestChance = .0;

        for (Word word : possibleMoves) {
            Node root = new Node(word, numberOfPlayers);
            root.climb();
            double chance = root.getChance();
            if (chance < bestChance) continue;
            if (chance > bestChance) {
                bestMoves.clear();
                bestChance = chance;
            }
            bestMoves.add(word);
        }
        try {
            return usedWords.check(bestMoves.get(generator.nextInt(bestMoves.size())));
        } catch (IllegalMoveException imx) {
            throw new DeveloperException(imx);
        }
    }


    @Override
    public String toString() {
        return "God#" + id;
    }


    private class Node {
        private final Node parent;
        @NotNull
        private final Node root;
        @NotNull
        private final Word word;
        private final int level;
        private final int numberOfPlayers;
        private int leafs = 0;
        private int wins = 0;


        Node(@NotNull final Word word, final int numberOfPlayers) {
            this.word = word;
            this.parent = null;
            this.root = this;
            this.level = 0;
            this.numberOfPlayers = numberOfPlayers;
        }


        Node(@NotNull final Word word, @NotNull final Node parentNode) {
            this.word = word;
            this.parent = parentNode;
            this.root = parentNode.root;
            this.level = parentNode.level + 1;
            this.numberOfPlayers = parentNode.numberOfPlayers;
        }


        void leaf(final int leafLevel) {
            leafs++;
            if (leafLevel % numberOfPlayers == 0) wins++;
        }


        boolean contains(@NotNull final Word word) {
            if (this.word.equals(word)) return true;
            if (parent == null) return false;
            return parent.contains(word);
        }


        void climb() {
            List<Word> moves = dictionary.selectExcluding(word.last, usedWords)
                                         .stream()
                                         .filter(word -> !contains(word))
                                         .collect(Collectors.toList());
            if (moves.size() == 0) {
                root.leaf(level);
            } else {
                for (Word word : moves) {
                    new Node(word, this).climb();
                }
            }
        }


        double getChance() {
            if (wins == 0) return .0;
            return (double) wins / leafs;
        }
    }
}
