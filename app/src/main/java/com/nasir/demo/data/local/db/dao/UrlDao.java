package com.nasir.demo.data.local.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.nasir.demo.data.model.Url;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface UrlDao {
    @Delete
    void delete(Url url);

    @Query("SELECT * FROM urls WHERE url LIKE :givenUrl LIMIT 1")
    Single<Url> findByUrl(String givenUrl);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Url url);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Url> urls);

    @Query("SELECT * FROM urls")
    Single<List<Url>> loadAll();

    @Query("SELECT * FROM urls WHERE id IN (:urlIds)")
    List<Url> loadAllByIds(List<Integer> urlIds);
}
