package patterns.example.movie;

import java.util.HashMap;
import java.util.Map;

public abstract class Movie {

    private final String title;
    private final Map<String, String> movieDescriptions = new HashMap<>();

    protected Movie(String title) {
        this.title = title;
    }

    public void addDescription(String key, String value) {
        movieDescriptions.put(key, value);
    }

    public void addDescriptions(Map<String, String> descriptions) {
        movieDescriptions.putAll(descriptions);
    }

    public String title() {
        return title;
    }

    public abstract double calculateAmount(int daysRented);

    public abstract int calculateFrequentRenterPoints(int daysRented);

    public Map<String, String> movieDescriptions() {
        return movieDescriptions;
    }
}
