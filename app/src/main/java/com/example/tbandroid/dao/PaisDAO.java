package com.example.tbandroid.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.tbandroid.model.Pais;

import java.util.List;

@Dao
public interface PaisDAO {

    @Query("SELECT * FROM pais")
    List<Pais> getAllPaises();

    @Query("SELECT * FROM pais WHERE name = :name")
    List<Pais> getPais(String name);

    @Insert
    void insert(Pais... pais);

    @Delete
    void delete(Pais... pais);

    @Update
    void update(Pais... pais);

}
