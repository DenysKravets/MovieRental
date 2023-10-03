package patterns.example.movie;

import java.util.Map;

public class Regular extends Movie {

    public Regular(String title) {
        super(title);
    }

    @Override
    public double calculateAmount(int daysRented) {
        double thisAmount = 2;
        if (daysRented > 2)
            thisAmount += (daysRented - 2) * 1.5;
        return thisAmount;
    }

    @Override
    public int calculateFrequentRenterPoints(int daysRented) {
        return 1;
    }

}
