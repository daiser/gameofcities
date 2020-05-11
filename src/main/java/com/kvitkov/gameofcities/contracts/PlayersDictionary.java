package com.kvitkov.gameofcities.contracts;

import com.kvitkov.gameofcities.Word;
import com.kvitkov.gameofcities.WordSignature;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface PlayersDictionary extends Dictionary, Set<Word> {
    default List<Word> exclude(@NotNull Dictionary dictionary) {
        return Collections.unmodifiableList(this.stream()
                                                .filter(word -> !dictionary.contains(word))
                                                .collect(Collectors.toList()));
    }
    default List<Word> select(Character firstLetter) {
        return Collections.unmodifiableList(this.stream()
                                                .filter(word -> firstLetter == null || firstLetter.equals(word.first))
                                                .collect(Collectors.toList()));
    }
    default List<Word> select(@NotNull WordSignature signature) {
        return Collections.unmodifiableList(this.stream()
                                                .filter(word -> signature.equals(word))
                                                .collect(Collectors.toList()));
    }
    default List<Word> selectExcluding(@NotNull WordSignature signature, @NotNull Dictionary dictionary) {
        return Collections.unmodifiableList(this.stream()
                                                .filter(word -> signature.equals(word) && !dictionary.contains(word))
                                                .collect(Collectors.toList()));
    }
    default List<Word> selectExcluding(Character firstLetter, @NotNull Dictionary dictionary) {
        return Collections.unmodifiableList(this.stream()
                                                .filter(word -> ((firstLetter == null) ||
                                                                 firstLetter.equals(word.first)) &&
                                                                !dictionary.contains(word))
                                                .collect(Collectors.toList()));
    }
}
