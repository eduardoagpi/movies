package com.cesaraguirre.movies.domain;

import com.cesaraguirre.movies.domain.entity.Movie;
import com.cesaraguirre.movies.domain.interactor.GetMovieUseCase;
import com.cesaraguirre.movies.domain.interactor.GetMovieUseCaseResult;
import com.cesaraguirre.movies.domain.repository.MovieRepository;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Maybe;
import io.reactivex.Single;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

public class GetMovieUseCaseTest {

    @Mock MovieRepository movieRepository;
    GetMovieUseCase getMovieUseCase;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        getMovieUseCase = new GetMovieUseCase(movieRepository);
    }

    @Test
    public void testFindMovie_withNoMovieInRepo() {
        when(movieRepository.findById(anyLong())).thenReturn(Maybe.<Movie>empty());

        GetMovieUseCaseResult useCaseResult = getMovieUseCase.execute(2).blockingGet();
        Assert.assertTrue(useCaseResult instanceof GetMovieUseCaseResult.MovieNotFound);

    }

    @Test
    public void testFindMovie_withResults() {
        long movieId = 4L;
        Movie movie = new Movie(
                movieId, "title", "overview", LocalDate.now(), "poster", 6f
        );
        when(movieRepository.findById(movieId)).thenReturn(Maybe.fromSingle(Single.just(movie)));

        GetMovieUseCaseResult useCaseResult = getMovieUseCase.execute(movieId).blockingGet();
        Assert.assertTrue(useCaseResult instanceof GetMovieUseCaseResult.MovieFound);
        Assert.assertEquals(
                ((GetMovieUseCaseResult.MovieFound)useCaseResult).getMovie()
                , movie);
    }

}
