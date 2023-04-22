DROP TABLE IF EXISTS movies;

CREATE TABLE IF NOT EXISTS movies(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    minutes INT NOT NULL,
    director VARCHAR(50) NOT NULL,
    genre VARCHAR(50) NOT NULL
);

INSERT INTO movies (name, minutes, director, genre) VALUES
    ('Dark Knight', 152,'Director1', 'ACTION'),
    ('Memento', 113,'Director1', 'THRILLER'),
    ('Matrix', 136,'Director2', 'ACTION')