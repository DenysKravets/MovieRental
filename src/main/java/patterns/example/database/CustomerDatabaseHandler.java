package patterns.example.database;

import patterns.example.customer.Customer;

import java.util.List;

public class CustomerDatabaseHandler extends DatabaseHandler {
    private final DataParser dataParser = new DataParser();

    public CustomerDatabaseHandler(String filename) {
        super(filename);
    }

    public List<Customer> loadAll() {
        return dataParser.parseStringCustomer(loadFile());
    }

    private void overwriteAll(List<Customer> customers) {
        writeFile(dataParser.parseCustomer(customers));
    }

    public void write(Customer customer) {
        List<Customer> customers = loadAll();
        customers.add(customer);
        overwriteAll(customers);
    }

    public void writeAll(List<Customer> customers) {
        List<Customer> customersFromDatabase = loadAll();
        customersFromDatabase.addAll(customers);
        overwriteAll(customersFromDatabase);
    }
}
