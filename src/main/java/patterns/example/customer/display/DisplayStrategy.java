package patterns.example.customer.display;

import patterns.example.rental.Rental;

import java.util.List;

public interface DisplayStrategy {
    String display(List<Rental> rentals, String name);

    DisplayStrategy PLAINTEXT = (rentals, name) -> {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        StringBuilder result = new StringBuilder("Rental Record for " + name + "\n");
        for (Rental rental : rentals) {
            double thisAmount = rental.calculateAmount();
            frequentRenterPoints += rental.calculateFrequentRenterPoints();
            //show figures for this rental
            result.append("\t").append(rental.getMovieTitle()).append("\t").append(thisAmount).append("\n");
            totalAmount += thisAmount;
        }
        //add footer lines
        result.append("Amount owed is ").append(totalAmount).append("\n");
        result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
        return result.toString();
    };

    DisplayStrategy HTML = (rentals, name) -> {
        return "HTML placeholder";
    };
}
