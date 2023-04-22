package org.platzi.javatests.movies;

import org.platzi.javatests.model.Movie;

import java.util.Collection;

public interface MovieRepository {

    Movie findById(long id);

    Collection<Movie> findByName(String name);

    Collection<Movie> findAll();
    void saveOrUpdate(Movie movie);
}
