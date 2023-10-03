package patterns.example;

import patterns.example.customer.Customer;
import patterns.example.customer.display.DisplayStrategy;
import patterns.example.database.DataParser;
import patterns.example.database.CustomerDatabaseHandler;
import patterns.example.database.MovieDatabaseHandler;
import patterns.example.movie.Childrens;
import patterns.example.movie.Movie;
import patterns.example.movie.NewRelease;
import patterns.example.movie.Regular;
import patterns.example.movie.factory.MovieFactory;
import patterns.example.rental.Rental;

import java.util.*;

public class RentalService {

    private final DataParser dataParser = new DataParser();
    private final CustomerDatabaseHandler customerDatabaseHandler = new CustomerDatabaseHandler("customer_database.json");
    private final MovieDatabaseHandler movieDatabaseHandler = new MovieDatabaseHandler("movie_database.json");
    private final MovieFactory movieFactory = new MovieFactory();

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
//
//        String statement = customerDatabaseHandler.loadAll().stream().findFirst().get().statement(DisplayStrategy.PLAINTEXT);
//        System.out.println(statement);
//
//        List<Movie> movies = new ArrayList<>();
//        movies.add(new Regular("Battle Royale"));
//
//        movieDatabaseHandler.writeAll(movies);

        try (Scanner scanner = new Scanner(System.in)) {

            boolean continueProgram = true;
            while (continueProgram) {
                System.out.println("""

                        To get list of movies, type: 'list movie'.
                        To get detailed description of a movie, type: 'movie details'.
                        To add movie, type: 'add movie'.
                        To exit the program, type: 'exit'.""");
                System.out.print(" Input: ");

                String command = scanner.nextLine();
                switch (command.toLowerCase()) {
                    case "exit" -> {
                        continueProgram = false;
                    }
                    case "list movie" -> {
                        listMovie();
                    }
                    case "movie details" -> {
                        movieDetails(scanner);
                    }
                    case "add movie" -> {
                        addMovie(scanner);
                    }
                    case "list customer" -> {
                        listCustomer();
                    }
                    case "customer details" -> {
                        customerDetails(scanner);
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listCustomer() {
        customerDatabaseHandler.loadAll().forEach(System.out::println);
    }

    private void customerDetails(Scanner scanner) {
        System.out.println("\nEnter customer name:");
        System.out.print(" Input: ");
        String name = scanner.nextLine();

        Optional<Customer> optionalCustomer = customerDatabaseHandler.loadAll().stream()
                .filter(customer -> customer.getName().equalsIgnoreCase(name)).findAny();

        if (optionalCustomer.isEmpty()) {
            System.out.println("Customer doesn't exist in database.");
            return;
        }

        Customer customer = optionalCustomer.get();

        System.out.println("\nEnter desired file format:");
        System.out.println("1. Plain Text");
        System.out.println("2. HTML");
        System.out.print(" Input: ");
        String typeString = scanner.nextLine();
        int type = 0;
        try {
            type = Integer.parseInt(typeString);
        } catch (Exception e) {
            System.out.println("Input is not an integer.");
            return;
        }
        DisplayStrategy displayStrategy = null;
        switch (type) {
            case 1 -> {
                displayStrategy = DisplayStrategy.PLAINTEXT;
            }
            case 2 -> {
                displayStrategy = DisplayStrategy.HTML;
            }
            default -> {
                System.out.println("File format doesn't exist.");
                return;
            }
        }

        String statement = customer.statement(displayStrategy);
        System.out.println(statement);
    }

    private void listMovie() {
        movieDatabaseHandler.loadAll().forEach(System.out::println);
    }

    private void movieDetails(Scanner scanner) {
        System.out.println("\nEnter movie title.");
        System.out.print(" Input: ");
        String command = scanner.nextLine();
        List<Movie> movies = movieDatabaseHandler.loadAll();
        Optional<Movie> movieOptional = movies.stream().filter(movie -> movie.title().equalsIgnoreCase(command)).findAny();
        if (movieOptional.isEmpty()) {
            System.out.println("Movie is not in the database.");
            return;
        }
        Movie movie = movieOptional.get();
        System.out.println("Title: " + movie.title());
        System.out.println("Type: " + movie.getClass().getSimpleName());
        System.out.println("Additional Info:\n" + movie.movieDescriptions());
    }

    private void addMovie(Scanner scanner) {
        System.out.println("\nEnter movie title:");
        System.out.print(" Input: ");
        String title = scanner.nextLine();
        System.out.println("\nEnter number near the type you wish to select:");
        System.out.println("1. Regular");
        System.out.println("2. New Release");
        System.out.println("3. Children's");
        System.out.print(" Input: ");
        String typeString = scanner.nextLine();
        int type = 0;
        try {
            type = Integer.parseInt(typeString);
        } catch (Exception e) {
            System.out.println("Input is not an integer.");
            return;
        }
        Movie movie = null;
        switch (type) {
            case 1 -> {
                movie = movieFactory.createMovie(title, Regular.class.getName());
            }
            case 2 -> {
                movie = movieFactory.createMovie(title, NewRelease.class.getName());
            }
            case 3 -> {
                movie = movieFactory.createMovie(title, Childrens.class.getName());
            }
            default -> {
                System.out.println("Type doesn't exist.");
                return;
            }
        }

        System.out.println("Do you wish to add Additional Info? (y/n)");
        System.out.print(" Input: ");
        if(!yesNoChecker(scanner.nextLine())) {
            movieDatabaseHandler.write(movie);
            return;
        }

        do {
            System.out.println("Enter description name: ");
            System.out.print("Input: ");
            String key = scanner.nextLine();
            System.out.println("Enter contents: ");
            System.out.print("Input: ");
            String value = scanner.nextLine();
            movie.addDescription(key, value);

            System.out.println("Add more info? (y/n)");
            System.out.print("Input: ");

        } while (yesNoChecker(scanner.nextLine()));

        movieDatabaseHandler.write(movie);
    }

    private boolean yesNoChecker(String input) {
        return input.equalsIgnoreCase("y")
                || input.equalsIgnoreCase("ye")
                || input.equalsIgnoreCase("yes")
                || input.equalsIgnoreCase("yea")
                || input.equalsIgnoreCase("yeah");
    }
}
