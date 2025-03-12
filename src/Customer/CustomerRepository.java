package Customer;
import Super.*;

import java.sql.*;
import java.util.ArrayList;

/**
 * The class handles database operations for customers.
 */
public class CustomerRepository extends Repository {

    /**
     * Gets all customers from the database.
     * @return a list of customers
     * @throws SQLException if there's a database error
     */
    public ArrayList<Customer> getAll() throws SQLException {
        ArrayList<Customer> customers = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM customers")) {

            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("password")
                );
                customers.add(customer);
            }
        }
        return customers;
    }

    /**
     * Gets one customer by their ID.
     * @param customerId the customer's ID
     * @return the customer with the ID, null if not found
     * @throws SQLException if there's a database error
     */
    public Customer getCustomerById(int customerId) throws SQLException {

        String sql = "SELECT * FROM customers WHERE customer_id = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, customerId);

            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                return null;
            } //skapa och returnera customer-object
            return new Customer(customerId, rs.getString("name"), rs.getString("email"), rs.getString("phone"), rs.getString("address"), rs.getString("password") );
        }
    }

    /**
     * Gets a customer by email.
     * @param email the customer's email
     * @return the customer with the email, null if not found
     * @throws SQLException if there's a database error
     */
    public Customer getCustomerByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM customers WHERE email = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();
            //om ingen kund hittas, returnera null
            if (!rs.next()) {
                return null;
            }//skapa och returnera customer-objek
            return new Customer(rs.getInt("customer_id"), rs.getString("name"), rs.getString("email"), rs.getString("phone"), rs.getString("address"), rs.getString("password") );
        }
    }

    /**
     * Adds a new customer to the database.
     * @param name the customer's name
     * @param phone the customer's phone number
     * @param email the customer's email
     * @param address the customer's address
     * @param password the customer's password
     * @throws SQLException if there's a database error
     */
    public void addCustomer(String name, String phone, String email, String address, String password) throws SQLException {

        String sql = "INSERT INTO customers (name, email, phone, address, password) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            //sätter parametrrar för det nya kundkontot
            pstmt.setString(1, name);
            pstmt.setString(2, phone);
            pstmt.setString(3, email);
            pstmt.setString(4, address);
            pstmt.setString(5, password);

            int rowsUpdated = pstmt.executeUpdate(); //krö sql frågan & kontrollera om registeringen lyckades

            if (rowsUpdated > 0) {
                System.out.println("Registration successful!");
            } else {
                System.out.println("Failed to register new customer. Please try again.");
            }
        }
    }

    /**
     * Deletes a customer from the database.
     * @param customerId the customer's ID
     * @throws SQLException if there's a database error
     */
    public void deleteCustomer (int customerId) throws SQLException{
            String sql = "DELETE FROM customers WHERE customer_id = ?";
            try (Connection conn = DriverManager.getConnection(URL);
            PreparedStatement pstmt = conn.prepareStatement(sql)){
        pstmt.setInt(1, customerId);
        pstmt.executeUpdate();
        }
    }

    /**
     * Updates a customer's information in the database.
     * @param customer the customer with the updated info
     * @throws SQLException if there's a database error
     */
    public void updateCustomer (Customer customer) throws SQLException{
            String sql = "UPDATE customer SET name = ?, email = ?, phone = ?, adress = ?, password = ? WHERE customer_id = ?";
            try (Connection conn = DriverManager.getConnection(URL);
                 PreparedStatement pstmt = conn.prepareStatement(sql)){
                     pstmt.setString(1, customer.getName());
                     pstmt.setString(2, customer.getEmail());
                     pstmt.setString(3, customer.getPhone());
                     pstmt.setString(4, customer.getAddress());
                     pstmt.setString(5, customer.getPassword());
                     pstmt.setInt(6, customer.getId());

                     pstmt.executeUpdate();
            }
    }


}