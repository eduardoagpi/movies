package com.cesaraguirre.movies.data.room.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(
        primaryKeys = {"position", "movieId"},
        tableName = "top_rated_movies"
)
public class RoomTopRatedMovie {

    public RoomTopRatedMovie() { }

    @ColumnInfo(name = "position")
    @NonNull
    public Integer position;

    @ColumnInfo(name = "movieId")
    @NonNull
    public Long movieId;

}
