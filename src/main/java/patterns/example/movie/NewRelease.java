package patterns.example.movie;

import java.util.HashMap;
import java.util.Map;

public class NewRelease extends Movie {

    public NewRelease(String title) {
        super(title);
    }

    @Override
    public double calculateAmount(int daysRented) {
        return daysRented * 3.0;
    }

    @Override
    public int calculateFrequentRenterPoints(int daysRented) {
        return daysRented > 1 ? 2 : 1;
    }
}
