package org.platzi.javatests.model;

import java.util.Objects;

public class Movie {

    private Integer id;
    private  String name;
    private int minutes;

    private String director;
    private  Genre genre;

    public Movie(String name, int minutes, String director, Genre genre) {
        this(null, name, minutes, director, genre);
    }

    public Movie(Integer id, String name, int minutes, String director, Genre genre) {
        this.id = id;
        this.name = name;
        this.minutes = minutes;
        this.director = director;
        this.genre = genre;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMinutes() {
        return minutes;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return minutes == movie.minutes && Objects.equals(id, movie.id) &&
                Objects.equals(name, movie.name) &&
                Objects.equals(director, movie.director) &&
                genre == movie.genre;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, minutes, director, genre);
    }
}
