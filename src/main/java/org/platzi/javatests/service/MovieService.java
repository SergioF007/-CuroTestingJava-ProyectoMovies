package org.platzi.javatests.service;

import org.platzi.javatests.data.MovieRepository;
import org.platzi.javatests.model.Genre;
import org.platzi.javatests.model.Movie;

import java.util.Collection;
import java.util.stream.Collectors;

public class MovieService {

    // mi services necesita del repositoiro de peliculas
    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    //peliculas por genero - por lo que me va a devolver, es una coleccion
    public Collection<Movie> findMoviesByGenre(Genre genre) {

        // aplicque refacto Inline
        return movieRepository.findAll().stream()
                .filter(movie -> movie.getGenre() == genre).collect(Collectors.toList());
    }

    // find Movies By Length - buscar películas por duración

    public Collection<Movie> findMoviesByLength(int length) {

        return movieRepository.findAll().stream()
                .filter(movie -> movie.getMinutes() <=  length).collect(Collectors.toList());

    }

}
