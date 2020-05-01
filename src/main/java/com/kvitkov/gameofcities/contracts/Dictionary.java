package com.kvitkov.gameofcities.contracts;

public interface Dictionary extends Iterable<String> {
    boolean contains(final String word);
}
