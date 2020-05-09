package com.kvitkov.gameofcities.contracts;

import com.kvitkov.gameofcities.Word;
import org.jetbrains.annotations.NotNull;

public interface AllWords extends Dictionary {
    void save(@NotNull Word newWord);
}
