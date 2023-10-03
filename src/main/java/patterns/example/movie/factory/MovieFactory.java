package patterns.example.movie.factory;

import patterns.example.movie.Movie;

import java.lang.reflect.InvocationTargetException;

public class MovieFactory {
    public Movie createMovie(String title, String type) throws IllegalArgumentException {
        Movie movie = null;
        try {
            movie = (Movie) Class.forName(type).getConstructor(String.class).newInstance(title);
        } catch (InstantiationException
                 | ClassNotFoundException
                 | NoSuchMethodException
                 | InvocationTargetException
                 | IllegalAccessException e) {
            throw new IllegalArgumentException("Unknown type: " + type);
        }
        return movie;
    }
}
