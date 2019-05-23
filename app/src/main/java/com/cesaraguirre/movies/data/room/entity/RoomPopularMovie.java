package com.cesaraguirre.movies.data.room.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(
        primaryKeys = {"position", "movieId"},
        tableName = "popular_movies"
)
public class RoomPopularMovie {

    public RoomPopularMovie() { }

    @ColumnInfo(name = "position")
    @NonNull
    public Integer position;

    @ColumnInfo(name = "movieId")
    @NonNull
    public Long movieId;

}
