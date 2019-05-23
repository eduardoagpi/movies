package com.cesaraguirre.movies.data.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie")
public class RoomMovie {

    public RoomMovie() { }

    @PrimaryKey
    @ColumnInfo(name = "movieId")
    public Long movieId;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "overview")
    public String overview;

    @ColumnInfo(name = "releaseDate")
    public String releaseDate;

    @ColumnInfo(name = "posterResource")
    public String posterResource;

    @ColumnInfo(name = "voteAverage")
    public Float voteAverage;
}
