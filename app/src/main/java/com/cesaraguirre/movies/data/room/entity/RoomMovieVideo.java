package com.cesaraguirre.movies.data.room.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "video",
        primaryKeys = {"movieId", "key"})
public class RoomMovieVideo {

    public RoomMovieVideo() { }

    @ColumnInfo(name = "movieId")
    @NonNull
    public Long movieId;

    @ColumnInfo(name = "key")
    @NonNull
    public String key;
}
