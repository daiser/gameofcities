package com.kvitkov.gameofcities.contracts;

import com.kvitkov.gameofcities.Word;

public interface Dictionary extends Iterable<Word> {
    boolean contains(final Word word);
}
