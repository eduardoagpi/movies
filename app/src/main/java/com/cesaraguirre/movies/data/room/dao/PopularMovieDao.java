package com.cesaraguirre.movies.data.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.cesaraguirre.movies.data.room.entity.RoomPopularMovie;
import com.cesaraguirre.movies.data.room.entity.RoomTopRatedMovie;
import com.cesaraguirre.movies.domain.entity.TopRatedMovie;

import java.util.Collection;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface PopularMovieDao {

    @Query("DELETE FROM popular_movies")
    Completable deleteAll();

    @Insert
    Completable addOrUpdate(Collection<RoomPopularMovie> entries);

    @Query("SELECT * FROM popular_movies ORDER BY position")
    Single<List<RoomPopularMovie>> getPopularEntriesOrdered();
}
