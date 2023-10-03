package patterns.example.customer;


import patterns.example.customer.display.DisplayStrategy;
import patterns.example.rental.Rental;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final String name;
    private final List<Rental> rentals;

    public Customer(String name) {
        this.name = name;
        rentals = new ArrayList<>();
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public void addRentals(List<Rental> rentals) {
        this.rentals.addAll(rentals);
    }

    public String getName() {
        return name;
    }

    public String statement(DisplayStrategy displayStrategy) {
        return displayStrategy.display(rentals, getName());
    }

    public List<Rental> getRentals() {
        return new ArrayList<>(rentals);
    }
}
