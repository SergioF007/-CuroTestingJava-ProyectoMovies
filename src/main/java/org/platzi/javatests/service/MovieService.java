package org.platzi.javatests.service;

import org.platzi.javatests.data.MovieRepository;
import org.platzi.javatests.model.Genre;
import org.platzi.javatests.model.Movie;

import java.util.Collection;

public class MovieService {

    // mi services necesita del repositoiro de peliculas
    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    //peliculas por genero - por lo que me va a devolver, es una coleccion
    public Collection<Movie> findMoviesByGenre(Genre genre) {

        Collection<Movie> allMovies = movieRepository.findAll();

        return allMovies;
    }
}
