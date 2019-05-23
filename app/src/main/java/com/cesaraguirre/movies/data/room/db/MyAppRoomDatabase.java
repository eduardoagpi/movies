package com.cesaraguirre.movies.data.room.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.cesaraguirre.movies.data.room.dao.MovieDao;
import com.cesaraguirre.movies.data.room.dao.MovieVideoDao;
import com.cesaraguirre.movies.data.room.dao.PopularMovieDao;
import com.cesaraguirre.movies.data.room.dao.TopRatedMovieDao;
import com.cesaraguirre.movies.data.room.dao.UpcomingMovieDao;
import com.cesaraguirre.movies.data.room.entity.RoomMovie;
import com.cesaraguirre.movies.data.room.entity.RoomMovieVideo;
import com.cesaraguirre.movies.data.room.entity.RoomPopularMovie;
import com.cesaraguirre.movies.data.room.entity.RoomTopRatedMovie;
import com.cesaraguirre.movies.data.room.entity.RoomUpcomingMovie;

@Database(
        entities = {
                RoomMovie.class,
                RoomPopularMovie.class,
                RoomTopRatedMovie.class,
                RoomUpcomingMovie.class,
                RoomMovieVideo.class
        },
        version = 3,
        exportSchema = false
)
public abstract class MyAppRoomDatabase extends RoomDatabase {
    public abstract MovieDao getItemDAO();
    public abstract TopRatedMovieDao getTopRatedDAO();
    public abstract PopularMovieDao getPopularMovieDAO();
    public abstract UpcomingMovieDao getUpcomingMovieDAO();
    public abstract MovieVideoDao getVideoDao();
}