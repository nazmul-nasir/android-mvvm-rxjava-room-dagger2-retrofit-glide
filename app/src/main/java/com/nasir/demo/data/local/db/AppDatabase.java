package com.nasir.demo.data.local.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.nasir.demo.data.local.db.dao.UrlDao;
import com.nasir.demo.data.model.Url;

@Database(entities = {Url.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UrlDao urlDao();
}
