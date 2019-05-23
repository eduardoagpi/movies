package com.cesaraguirre.movies.data.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.cesaraguirre.movies.data.room.entity.RoomTopRatedMovie;
import com.cesaraguirre.movies.data.room.entity.RoomUpcomingMovie;
import com.cesaraguirre.movies.domain.entity.TopRatedMovie;

import java.util.Collection;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface UpcomingMovieDao {

    @Query("DELETE FROM upcoming_movies")
    Completable deleteAll();

    @Insert
    Completable addOrUpdate(Collection<RoomUpcomingMovie> entries);

    @Query("SELECT * FROM upcoming_movies ORDER BY position")
    Single<List<RoomUpcomingMovie>> getUpcomingEntriesOrdered();
}
