package org.platzi.javatests.service;

import junit.framework.TestCase;
import org.junit.Test;
import org.mockito.Mockito;
import org.platzi.javatests.data.MovieRepository;
import org.platzi.javatests.model.Genre;

public class MovieServiceTest extends TestCase {

    @Test
    public void return_movies_by_genre() {

        //Vamos a simular la respuesta de la clase MovieRepository
        // por lo que usamos Mockito
        MovieRepository movieRepository = Mockito.mock(MovieRepository.class);

        MovieService movieService = new MovieService(movieRepository);

        movieService.findMoviesByGenre(Genre.COMEDY);
    }


}