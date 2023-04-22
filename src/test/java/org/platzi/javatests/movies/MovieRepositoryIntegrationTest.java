package org.platzi.javatests.movies;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.platzi.javatests.model.Genre;
import org.platzi.javatests.model.Movie;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

public class MovieRepositoryIntegrationTest {

    private MovieRepositoryJdbc movieRepositoryJdbc;

    private DataSource dataSource;
    // cargar todas las peliculas


    @Before
    public void setUp() throws Exception {

        //Creando una conexion a la Base de datos en memoria de pruebas con el formato de MySQL
        dataSource = new DriverManagerDataSource("jdbc:h2:mem:test;MODE=MYSQL", "sa", "sa");
        // Para que me carge el codigo de la creacion de la Table en BD
        // sus datos de prueba, ejecuto el siguiente script
        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("sql-scripts/test-data.sql"));

        // creo el objeto jdbcTemplate que necesita movieRepositoryJdbc
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        // Crear el objeto de la clase a testiar
        movieRepositoryJdbc =  new MovieRepositoryJdbc(jdbcTemplate);

    }

    @Test
    public void load_all_movies() throws SQLException {

        //Crea los objetos pelicular con las filas de la BD
        Collection<Movie> movies = movieRepositoryJdbc.findAll();
        // Compruebo que las peliculas obtenidas son las correctas
        assertThat(movies, CoreMatchers.is(Arrays.asList(
                new Movie(1,"Dark Knight", 152, Genre.ACTION ),
                new Movie(2,"Memento", 113, Genre.THRILLER ),
                new Movie(3,"Matrix", 136, Genre.ACTION)
        )));
    }

    // encontrar un a pelicula por id
    @Test
    public void load_movie_by_id() {

        Movie movie = movieRepositoryJdbc.findById(2);

        // lo comparo con lo que espero que me devuleva
        assertThat(movie, CoreMatchers.is(new Movie(2, "Memento", 113, Genre.THRILLER)));

    }


    // no implementadomos este metodo devido a que el codigo que simula la BD
    // tiene incorporado el drop, por eso a nostros nos corrio el la prueba sin necesidad de esto
    /*
    @After
    public void tearDown() throws Exception {

        final  Statement s = dataSource.getConnection().createStatement();
        s.execute("DROP ALL objects DELETE files"); // "shutdown" si es un BD en memoria.
    }

     */
}