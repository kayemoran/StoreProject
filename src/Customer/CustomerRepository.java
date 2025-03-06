package Customer;
import Super.*;

import java.sql.*;
import java.util.ArrayList;

public class CustomerRepository extends Repository {

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

    public Customer getCustomerById(int customerId) throws SQLException {

        String sql = "SELECT * FROM customers WHERE customer_id = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, customerId);

            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                return null;
            }
            return new Customer(customerId, rs.getString("name"), rs.getString("email"), rs.getString("phone"), rs.getString("address"), rs.getString("password") );
        }
    }

    public Customer getCustomerByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM customers WHERE email = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                return null;
            }
            return new Customer(rs.getInt("customer_id"), rs.getString("name"), rs.getString("email"), rs.getString("phone"), rs.getString("address"), rs.getString("password") );
        }
    }



    public void addCustomer(String name, String phone, String email, String address, String password) throws SQLException {

        String sql = "INSERT INTO customers (name, email, phone, address, password) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, phone);
            pstmt.setString(3, email);
            pstmt.setString(4, address);
            pstmt.setString(5, password);

            pstmt.executeUpdate();
        }
    }
public void deleteCustomer (int customerId) throws SQLException{
        String sql = "DELETE FROM customers WHERE customer_id = ?";
        try (Connection conn = DriverManager.getConnection(URL);
        PreparedStatement pstmt = conn.prepareStatement(sql)){
    pstmt.setInt(1, customerId);
    pstmt.executeUpdate();
    }
}
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

    /**
     * Här kan fler metoder läggas till som t.ex:
     * - addCustomer
     * - getCustomerById
     * - updateCustomer
     * - deleteCustomer
     * - findCustomerByEmail
     *
     * Varje metod kommer följa samma mönster:
     * 1. Skapa Connection med DriverManager.getConnection(URL)
     * 2. Skapa Statement eller PreparedStatement
     * 3. Utför databasoperationen
     * 4. Hantera resultatet
     * 5. Låt try-with-resources stänga alla resurser
     */
}