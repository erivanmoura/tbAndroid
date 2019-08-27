package com.example.tbandroid.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.tbandroid.model.Pais;

@Database(entities = {Pais.class}, version = 1)
public abstract class PaisDatabase extends RoomDatabase {

    private static final String TAG = "PaisDatabase";
    private static final String DB_NAME = "Pais.db";
    private static volatile PaisDatabase instance;

    public static PaisDatabase getInstance(Context context) {
        if (instance == null)
            instance = create(context);
        return instance;
    }
    private static PaisDatabase create(Context context){
        return Room.databaseBuilder(context, PaisDatabase.class, DB_NAME).build();
    }
    public abstract PaisDAO getDao();
}
