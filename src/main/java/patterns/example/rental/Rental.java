package patterns.example.rental;

import patterns.example.movie.Movie;

import java.util.HashMap;
import java.util.Map;

public class Rental {
    private final Movie movie;
    private final int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public double calculateAmount() {
        return movie.calculateAmount(daysRented);
    }

    public int calculateFrequentRenterPoints() {
        return movie.calculateFrequentRenterPoints(daysRented);
    }

    public String getMovieTitle() {
        return movie.title();
    }

    public int getDaysRented() {
        return daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public Map<String, String> getMovieDescriptions() {
        return new HashMap<>(movie.movieDescriptions());
    }
}
