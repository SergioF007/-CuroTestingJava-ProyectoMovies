package org.platzi.javatests.service;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.platzi.javatests.data.MovieRepository;
import org.platzi.javatests.model.Genre;
import org.platzi.javatests.model.Movie;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class MovieServiceTest {

    private MovieService movieService;
    @Before
    public void setUp() throws Exception {

        //Vamos a simular la respuesta de la clase MovieRepository
        // por lo que usamos Mockito
        MovieRepository movieRepository = Mockito.mock(MovieRepository.class);

        Mockito.when(movieRepository.findAll()).thenReturn(
                Arrays.asList(
                        new Movie(1, "Dark Knight", 152, "Director1", Genre.ACTION),
                        new Movie(2, "Mamento", 113, "Director1", Genre.THRILLER),
                        new Movie(3, "Super 8", 112,"Director2", Genre.THRILLER),
                        new Movie(4, "Scream", 111, "Director3", Genre.HORROR),
                        new Movie(5, "Home Alone", 103, "Director3", Genre.COMEDY)

                )

        );

        movieService = new MovieService(movieRepository);
    }

    @Test
    public void return_movies_by_genre() {

        Collection<Movie> movies =  movieService.findMoviesByGenre(Genre.THRILLER);
        assertThat(getMovieIds(movies), CoreMatchers.is(Arrays.asList(2, 3)) );

    }

    @Test
    public void return_movies_by_legth() {

        Collection<Movie> movies = movieService.findMoviesByLength(113);
        assertThat(getMovieIds(movies), CoreMatchers.is(Arrays.asList(2, 3, 4, 5)) );

    }


    // encontrar un a pelicula por minutos y genero
    // probando el metodo de la clase movieServise: encontrar películas por plantilla
    @Test
    public void return_movie_by_minutes_and_genre() {

        // lo comparo con lo que espero que me devuleva
        assertThat(getMovieIds(movieService.findMoviesByTemplate(new Movie(null, 120, null, Genre.THRILLER))), CoreMatchers.is(Arrays.asList(2,3)));

    }


    private static List<Integer> getMovieIds(Collection<Movie> movies) {
        List<Integer> movieIds = movies.stream().map(Movie::getId).collect(Collectors.toList());
        return movieIds;
    }

}