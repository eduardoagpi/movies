package com.cesaraguirre.movies.data;


import com.cesaraguirre.movies.data.room.entity.RoomMovie;
import com.cesaraguirre.movies.data.room.mapper.RoomMovieMapper;
import com.cesaraguirre.movies.data.room.util.LocalDateTimeUtils;
import com.cesaraguirre.movies.domain.entity.Movie;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

public class RoomMovieTest {


    RoomMovieMapper mapper = new RoomMovieMapper();

    @Test
    public void test_transformToRoomEntity() {
        final Long id = 4L;
        final String title = "title";
        final String overview = "Overview";
        final LocalDate releaseDate = LocalDate.now();
        final String posterResource = "dfdkfdfok";
        final Float voteAverage = 7.1f;

        final Movie domainMovie = new Movie(
                id, title, overview, releaseDate, posterResource, voteAverage
        );

        final RoomMovie roomMapedMovie = mapper.mapToRoom(domainMovie);
        Assert.assertEquals(id, roomMapedMovie.movieId);
        Assert.assertEquals(title, roomMapedMovie.title);
        Assert.assertEquals(overview, roomMapedMovie.overview);
        Assert.assertEquals(LocalDateTimeUtils.convertLocalDateToString(releaseDate), roomMapedMovie.releaseDate);
        Assert.assertEquals(posterResource, roomMapedMovie.posterResource);
        Assert.assertEquals(voteAverage, roomMapedMovie.voteAverage);
    }

    @Test
    public void test_transformToDomainEntity() {
        final Long id = 4L;
        final String title = "title";
        final String overview = "Overview";
        final LocalDate releaseDate = LocalDate.now();
        final String posterResource = "dfdkfdfok";
        final Float voteAverage = 7.1f;

        final RoomMovie roomMovie = new RoomMovie();
        roomMovie.title = title;
        roomMovie.overview = overview;
        roomMovie.releaseDate = LocalDateTimeUtils.convertLocalDateToString(releaseDate);
        roomMovie.movieId = id;
        roomMovie.posterResource = posterResource;
        roomMovie.voteAverage = voteAverage;

        final Movie domainMappedMovie = mapper.mapToDomain(roomMovie);
        Assert.assertEquals((long)id, domainMappedMovie.getId());
        Assert.assertEquals(title, domainMappedMovie.getTitle());
        Assert.assertEquals(overview, domainMappedMovie.getOverview());
        Assert.assertEquals(releaseDate, domainMappedMovie.getReleaseDate());
        Assert.assertEquals(posterResource, domainMappedMovie.getPosterResource());
        Assert.assertEquals(voteAverage, domainMappedMovie.getVoteAverage(), 0f);
    }

}
