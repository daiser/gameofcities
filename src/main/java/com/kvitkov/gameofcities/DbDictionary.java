package com.kvitkov.gameofcities;

import com.kvitkov.gameofcities.contracts.AllWords;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.Iterator;

public class DbDictionary implements AllWords, AutoCloseable {
    private final Connection connection;
    private final PreparedStatement stContains;
    private final PreparedStatement stSave;


    public DbDictionary() {
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:cities.db");
            stContains = connection.prepareStatement("select exists(select item from words where item=?);");
            stSave = connection.prepareStatement("insert into words(item) values(?);");
        } catch (SQLException sx) {
            throw new RuntimeException("Не удалось открыть БД-словарь", sx);
        }
    }


    @Override
    public void close() throws Exception {
        stSave.close();
        stContains.close();
    }


    public boolean contains(Word word) {
        try {
            stContains.setString(1, word.value);
            try (ResultSet rs = stContains.executeQuery()) {
                rs.next();
                return rs.getBoolean(1);
            }
        } catch (SQLException sx) {
            throw new DeveloperException(sx);
        }
    }


    public @NotNull Iterator<Word> iterator() {
        try {
            Statement st = connection.createStatement();
            final ResultSet rs = st.executeQuery("select item from words order by item;");

            return new Iterator<Word>() {
                private Word lastString = null;
                private boolean nextFetched = false;


                public boolean hasNext() {
                    try {
                        if (nextFetched) return true;
                        if (rs.next()) {
                            nextFetched = true;
                            lastString = new Word(rs.getString(1));
                            return true;
                        } else {
                            lastString = null;
                            return false;
                        }
                    } catch (SQLException sx) {
                        throw new RuntimeException(sx);
                    }
                }


                public Word next() {
                    nextFetched = false;
                    return lastString;
                }


                public void remove() {

                }
            };
        } catch (SQLException sx) {
            throw new RuntimeException("Не удалось прочитать слова из словаря", sx);
        }
    }


    public void save(@NotNull Word newWord) {
        try {
            stSave.setString(1, newWord.value);
            stSave.executeUpdate();
        } catch (SQLException sx) {
            if (sx.getErrorCode() == 19) return;
            throw new RuntimeException("Не удалось сохранить слово в БД", sx);
        }
    }
}
