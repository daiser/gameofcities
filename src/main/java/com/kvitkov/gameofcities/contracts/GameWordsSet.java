package com.kvitkov.gameofcities.contracts;

import com.kvitkov.gameofcities.Word;
import org.jetbrains.annotations.NotNull;

public interface GameWordsSet extends Dictionary {
    void save(@NotNull Word newWord);
}
