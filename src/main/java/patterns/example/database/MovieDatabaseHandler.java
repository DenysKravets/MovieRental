package patterns.example.database;

import patterns.example.customer.Customer;
import patterns.example.movie.Movie;

import java.util.List;

public class MovieDatabaseHandler extends DatabaseHandler {

    private final DataParser dataParser = new DataParser();

    public MovieDatabaseHandler(String filename) {
        super(filename);
    }

    public List<Movie> loadAll() {
        return dataParser.parseStringMovie(loadFile());
    }

    private void overwriteAll(List<Movie> movies) {
        writeFile(dataParser.parseMovie(movies));
    }

    public void write(Movie movie) {
        List<Movie> movies = loadAll();
        movies.add(movie);
        overwriteAll(movies);
    }

    public void writeAll(List<Movie> movies) {
        List<Movie> customersFromDatabase = loadAll();
        customersFromDatabase.addAll(movies);
        overwriteAll(customersFromDatabase);
    }

}
