package com.cesaraguirre.movies.data.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.cesaraguirre.movies.data.room.entity.RoomTopRatedMovie;

import java.util.Collection;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface TopRatedMovieDao {

    @Query("DELETE FROM top_rated_movies")
    Completable deleteAll();

    @Insert
    Completable addOrUpdate(Collection<RoomTopRatedMovie> entries);

    @Query("SELECT * FROM top_rated_movies ORDER BY position")
    Single<List<RoomTopRatedMovie>> getTopRatedEntriesOrdered();
}
