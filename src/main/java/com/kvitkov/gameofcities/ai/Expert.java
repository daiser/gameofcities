package com.kvitkov.gameofcities.ai;

import com.kvitkov.gameofcities.*;
import com.kvitkov.gameofcities.contracts.AllWords;
import com.kvitkov.gameofcities.contracts.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Expert extends Monkey implements Player {
    private UsedWords usedWords;


    public Expert(Random generator, AllWords words, final double ability) {
        super(generator, words, ability);
    }


    public Expert(Random generator, AllWords words) {
        super(generator, words, 0.9);
    }


    @Override
    public UsedWords.LegitMove takeTurn(Character startsWith, UsedWords usedWords, final int numberOfPlayers) throws
            GiveUpException {
        this.usedWords = usedWords;
        List<Word> possibleMoves = getPossibleMoves(startsWith, usedWords);
        if (possibleMoves.size() == 0) throw new GiveUpException(this);

        ArrayList<Word> bestMoves = new ArrayList<>();
        double bestChance = .0;

        float progress = 0;
        int total = possibleMoves.size();
        for (Word word : possibleMoves) {
            progress++;
            Node root = new Node(word, numberOfPlayers);
            System.out.println(String.format("\nCalculating... %.1f%%", progress * 100 / total));
            root.climb();
            double chance = root.getChance();
            if (chance < bestChance) continue;
            if (chance > bestChance) {
                bestMoves.clear();
                bestChance = chance;
            }
            bestMoves.add(word);
        }
        System.out.println(String.format("\nBest chance %.1f%%", bestChance * 100));
        try {
            return usedWords.check(bestMoves.get(generator.nextInt(bestMoves.size())));
        } catch (IllegalMoveException imx) {
            throw new DeveloperException(imx);
        }
    }


    @Override
    public String toString() {
        return "Expert#" + id;
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
        private int minWinLevel = Integer.MAX_VALUE;


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
            if (leafLevel % numberOfPlayers == 0) {
                wins++;
                if (leafLevel < minWinLevel) minWinLevel = leafLevel;
            }
            if (leafs % 10 == 0) System.out.print("\rWL:" + wins + "/" + leafs);
        }


        boolean contains(@NotNull final Word word) {
            if (this.word.equals(word)) return true;
            if (parent == null) return false;
            return parent.contains(word);
        }


        void climb() {
            List<Word> moves = getPossibleMoves(word.last, usedWords).stream()
                                                                     .filter(word -> !contains(word))
                                                                     .collect(Collectors.toList());
            if (moves.size() == 0) {
                root.leaf(level);
            } else {
                if (level == root.minWinLevel) return;
                for (Word word : moves) {
                    new Node(word, this).climb();
                }
            }
        }


        double getChance() {
            return (double) wins / leafs;
        }
    }
}
