package com.kvitkov.gameofcities;

import com.kvitkov.gameofcities.contracts.AvailableWords;

import javax.swing.*;
import java.sql.*;
import java.util.Iterator;

public class DbDictionary implements AvailableWords {
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


    public boolean contains(String word) {
        try {
            stContains.setString(1, word);
            ResultSet rs = stContains.executeQuery();
            rs.next();
            boolean exists = rs.getBoolean(1);
            rs.close();
            return exists;
        } catch (SQLException sx) {
            throw new RuntimeException("Не удалось выполнить запрос к БД", sx);
        }
    }


    public Iterator<String> iterator() {
        try {
            Statement st = connection.createStatement();
            final ResultSet rs = st.executeQuery("select item from words order by item;");

            return new Iterator<String>() {
                private String lastString = null;


                public boolean hasNext() {
                    try {
                        if (rs.next()) {
                            lastString = rs.getString(1);
                            return true;
                        } else {
                            lastString = null;
                            return false;
                        }
                    } catch (SQLException sx) {
                        throw new RuntimeException(sx);
                    }
                }


                public String next() {
                    return lastString;
                }


                public void remove() {

                }
            };
        } catch (SQLException sx) {
            throw new RuntimeException("Не удалось прочитать слова из словаря", sx);
        }
    }


    public void save(String newWord) {
        try {
            stSave.setString(1, newWord);
            stSave.executeUpdate();
        } catch (SQLException sx) {
            if (sx.getErrorCode() == 19) return;
            throw new RuntimeException("Не удалось сохранить слово в БД", sx);
        }
    }
}
