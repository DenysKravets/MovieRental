package patterns.example;

import patterns.example.customer.display.DisplayStrategy;
import patterns.example.database.DataParser;
import patterns.example.database.CustomerDatabaseHandler;
import patterns.example.database.MovieDatabaseHandler;
import patterns.example.movie.Childrens;
import patterns.example.movie.Movie;
import patterns.example.movie.NewRelease;
import patterns.example.movie.Regular;

import java.util.ArrayList;
import java.util.List;

public class RentalService {

    private final DataParser dataParser = new DataParser();
    private final CustomerDatabaseHandler customerDatabaseHandler = new CustomerDatabaseHandler("customer_database.json");
    private final MovieDatabaseHandler movieDatabaseHandler = new MovieDatabaseHandler("movie_database.json");

    public void start() throws Exception {
//        List<Customer> customerList = new ArrayList<>();
//        List<Rental> rentals = List.of(new Rental(new Regular("Rambo"), 1),
//                new Rental(new NewRelease("Lord of the Rings"), 4),
//                new Rental(new Childrens("Harry Potter"), 5));
//
//        Customer customer = new Customer("John Doe");
//        customer.addRentals(rentals);
//        customerList.add(customer);
//        customer = new Customer("Mark Twain");
//        customer.addRentals(rentals);
//        customerList.add(customer);
//
//        String statement = customer.statement(DisplayStrategy.PLAINTEXT);
//
//        System.out.println(statement);
//
//        System.out.println();
//        System.out.println();
//        System.out.println();
//
//        databaseHandler.writeAll(customerList);

        String statement = customerDatabaseHandler.loadAll().stream().findFirst().get().statement(DisplayStrategy.PLAINTEXT);
        System.out.println(statement);

        List<Movie> movies = new ArrayList<>();
        movies.add(new Regular("Battle Royale"));

        movieDatabaseHandler.writeAll(movies);


        operationLoop();
    }

    private void operationLoop() throws Exception {
        while (true) {
            break;
        }
    }
}
