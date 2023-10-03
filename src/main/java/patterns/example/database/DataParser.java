package patterns.example.database;

import org.json.JSONArray;
import org.json.JSONObject;
import patterns.example.customer.Customer;
import patterns.example.movie.Movie;
import patterns.example.movie.factory.MovieFactory;
import patterns.example.rental.Rental;

import java.util.*;

public class DataParser {

    private final MovieFactory movieFactory = new MovieFactory();

    public List<Customer> parseStringCustomer(String jsonString) {
        List<Customer> customers = new ArrayList<>();

        JSONArray jsonData = new JSONArray(jsonString);
        for (Object jsonDatum : jsonData) {
            JSONObject jsonObject = (JSONObject) jsonDatum;
            String name = jsonObject.getString("name");
            Customer customer = new Customer(name);
            JSONArray jsonRentals = jsonObject.getJSONArray("rentals");
            for (Object jsonRentalObj: jsonRentals) {
                JSONObject jsonRental = (JSONObject) jsonRentalObj;
                customer.addRental(parseRentalJSON(jsonRental));
            }
            customers.add(customer);
        }

        return customers;
    }

    private Rental parseRentalJSON(JSONObject rentalJSON) {
        int daysRented = rentalJSON.getInt("daysRented");
        Movie movie = parseMovieJSON(rentalJSON.getJSONObject("movie"));
        return new Rental(movie, daysRented);
    }

    public List<Movie> parseStringMovie(String jsonString) {
        List<Movie> movies = new ArrayList<>();

        JSONArray jsonData = new JSONArray(jsonString);
        for (Object jsonDatum : jsonData) {
            JSONObject jsonObject = (JSONObject) jsonDatum;
            movies.add(parseMovieJSON(jsonObject));
        }

        return movies;
    }

    private Movie parseMovieJSON(JSONObject movieJSON) {
        String title = movieJSON.getString("title");
        String type = movieJSON.getString("type");
        Movie movie = movieFactory.createMovie(title, type);
        Map<String, String> descriptions = parseDescriptions(movieJSON.getJSONObject("descriptions"));
        movie.addDescriptions(descriptions);
        return movie;
    }

    private Map<String, String> parseDescriptions(JSONObject descriptionsJSON) {
        Map<String, String> descriptions = new HashMap<>();
        for (String key : descriptionsJSON.keySet()) {
            String value = descriptionsJSON.getString(key);
            descriptions.put(key, value);
        }
        return descriptions;
    }

    public String parseCustomer(List<Customer> customers) {

        JSONArray customersJSON = new JSONArray();
        for (Customer customer: customers) {
            JSONObject customerJSON = new JSONObject();
            customerJSON.put("name", customer.getName());
            JSONArray rentalsJSON = new JSONArray();
            for (Rental rental: customer.getRentals()) {
                rentalsJSON.put(parseRental(rental));
            }
            customerJSON.put("rentals", rentalsJSON);
            customersJSON.put(customerJSON);
        }
        return customersJSON.toString();
    }

    private JSONObject parseRental(Rental rental) {
        JSONObject rentalJSON = new JSONObject();
        rentalJSON.put("daysRented", rental.getDaysRented());
        rentalJSON.put("movie", parseMovie(rental.getMovie()));
        return rentalJSON;
    }

    public String parseMovie(List<Movie> movies) {
        JSONArray jsonArray = new JSONArray();
        for (Movie movie :
                movies) {
            jsonArray.put(parseMovie(movie));
        }
        return jsonArray.toString();
    }

    private JSONObject parseMovie(Movie movie) {
        JSONObject movieJSON = new JSONObject();
        movieJSON.put("title", movie.title());
        movieJSON.put("descriptions", new JSONObject(movie.movieDescriptions()));
        movieJSON.put("type", movie.getClass().getName());
        return movieJSON;
    }
}
