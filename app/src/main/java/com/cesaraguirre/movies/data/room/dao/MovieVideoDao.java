package com.cesaraguirre.movies.data.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cesaraguirre.movies.data.room.entity.RoomMovieVideo;

import java.util.Collection;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface MovieVideoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertVideos(Collection<RoomMovieVideo> videos);

    @Query("SELECT * FROM video WHERE movieId = :movieId")
    Single<List<RoomMovieVideo>> getVideosForMovie(Long movieId);
}
