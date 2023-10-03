package patterns.example.movie;

import java.util.HashMap;
import java.util.Map;

public class Childrens extends Movie {

    public Childrens(String title) {
        super(title);
    }

    @Override
    public double calculateAmount(int daysRented) {
        double thisAmount = 1.5;
        if (daysRented > 3)
            thisAmount += (daysRented - 3) * 1.5;
        return thisAmount;
    }

    @Override
    public int calculateFrequentRenterPoints(int daysRented) {
        return 1;
    }
}
