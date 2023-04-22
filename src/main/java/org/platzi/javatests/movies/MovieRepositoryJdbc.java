package org.platzi.javatests.movies;

import org.platzi.javatests.model.Genre;
import org.platzi.javatests.model.Movie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Objects;

public class MovieRepositoryJdbc implements MovieRepository {

    private JdbcTemplate jdbcTemplate;

    public MovieRepositoryJdbc(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }
    // implemento queryForObject, por que solo necesito un objeto en especifico
    // y args va hacer el argumento en este caso es el id de la pelicula.
    // y luego movieMapper que es que el que transforma los datos de la BD en objetos Java.
    @Override
    public Movie findById(long id) {

        Object[] args = { id };

        return jdbcTemplate.queryForObject("SELECT * FROM movies WHERE id = ?", args, movieMapper);
    }
    // metodo para obtener los movies deacuerdo
    // query que vamos a implementar con la clase JDBC
    @Override
    public Collection<Movie> findAll() {

        // ejcuta la consilta en la base de datos y esta transformando cada fila en un objeto movie
        return jdbcTemplate.query("SELECT * FROM movies", movieMapper);
    }

    @Override
    public void saveOrUpdate(Movie movie) {

        jdbcTemplate.update("INSERT INTO movies (name, minutes, genre) VALUES(?, ?, ?)",
                movie.getName(), movie.getMinutes(), movie.getGenre().toString());

    }

    // Transformamos cada fila en un Objetos Movie
    // rs, representa la fila actual de la base de datos
    private static RowMapper<Movie> movieMapper = (rs, rowNum) ->
            new Movie(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("minutes"),
                Genre.valueOf(rs.getString("genre")));
}
