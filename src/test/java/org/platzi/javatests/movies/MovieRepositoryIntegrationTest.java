package org.platzi.javatests.movies;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.platzi.javatests.model.Genre;
import org.platzi.javatests.model.Movie;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

public class MovieRepositoryIntegrationTest {

    private MovieRepositoryJdbc movieRepositoryJdbc;
    // cargar todas las peliculas
    @Test
    public void load_all_movies() throws SQLException {

        //Creando una conexion a la Base de datos en memoria de pruebas con el formato de MySQL
        DataSource dataSource =
                new DriverManagerDataSource("jdbc:h2:mem:test;MODE=MYSQL", "sa", "sa");
        // Para que me carge el codigo de la creacion de la Table en BD
        // sus datos de prueba, ejecuto el siguiente script
        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("sql-scripts/test-data.sql"));

        // creo el objeto jdbcTemplate que necesita movieRepositoryJdbc
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        // Crear el objeto de la clase a testiar
        movieRepositoryJdbc =  new MovieRepositoryJdbc(jdbcTemplate);
        //Crea los objetos pelicular con las filas de la BD
        Collection<Movie> movies = movieRepositoryJdbc.findAll();
        // Compruebo que las peliculas obtenidas son las correctas
        assertThat(movies, CoreMatchers.is(Arrays.asList(
                new Movie(1,"Dark Knight", 152, Genre.ACTION ),
                new Movie(2,"Memento", 113, Genre.THRILLER ),
                new Movie(3,"Matrix", 136, Genre.ACTION)
        )));
    }
}