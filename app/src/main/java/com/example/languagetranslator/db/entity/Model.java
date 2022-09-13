package com.example.languagetranslator.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "History")
public class Model {

    @ColumnInfo(name = "COLUMN_WORD")
    private String word;

    @ColumnInfo(name = "COLUMN_MEANING")
    private String meaning;

    @ColumnInfo(name = "COLUMN_ID")
    @PrimaryKey(autoGenerate = true)
    private int id;

    //Constructor
    public Model(String word, String meaning, int id) {
        this.word = word;
        this.meaning = meaning;
        this.id = id;
    }

    //Getters
    public String getWord() {
        return word;
    }

    public String getMeaning() {
        return meaning;
    }

    public int getId() {
        return id;
    }
}
