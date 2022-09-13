package com.example.languagetranslator.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.languagetranslator.db.entity.Model;

@Database(entities = {Model.class}, version = 1)
public abstract class HistoryDatabase extends RoomDatabase {

    public abstract ModelDAO getModelDao();
}
