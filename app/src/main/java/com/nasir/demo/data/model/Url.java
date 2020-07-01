package com.nasir.demo.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "urls")
public class Url {
    @ColumnInfo(name = "created_at", defaultValue = "CURRENT_TIMESTAMP")
    public String createdAt;

    @PrimaryKey(autoGenerate = true)
    public Long id;

    public String url;

    @ColumnInfo(name = "updated_at", defaultValue = "CURRENT_TIMESTAMP")
    public String updatedAt;

    public Url(String url) {
        this.url = url;
    }
}
