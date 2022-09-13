package com.example.languagetranslator.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.languagetranslator.db.entity.Model;

import java.util.List;

@Dao
public interface ModelDAO {

    @Insert
    public long insertWordMeaning(Model model);

    @Delete
    public void deleteWordMeaning(Model model);

    @Query(" SELECT * FROM HISTORY ORDER BY COLUMN_ID DESC ")
    public List<Model> getAllModel();

}
