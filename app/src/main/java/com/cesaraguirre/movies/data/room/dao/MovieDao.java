package com.cesaraguirre.movies.data.room.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cesaraguirre.movies.data.room.entity.RoomMovie;

import java.util.Collection;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
public interface MovieDao {
    @Insert
    Completable insertSingleMovie(RoomMovie roomMovie);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertMoviesOnConflictReplace(Collection<RoomMovie> movies);

    @Query("SELECT * FROM movie WHERE movieId= :id")
    Maybe<RoomMovie> getMovieById(Long id);

    @Query("SELECT * FROM movie WHERE movieId IN (:ids)")
    Single<List<RoomMovie>> getMoviesById(List<Long> ids);

    @Query("DELETE FROM movie")
    Completable deleteAll();

    @Query("SELECT * FROM movie WHERE title LIKE :pattern LIMIT :limit")
    Single<List<RoomMovie>> findMoviesWithPattern(String pattern, Integer limit);
}
