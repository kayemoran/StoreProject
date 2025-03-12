package Admin;

import Customer.Customer;
import Super.*;

import java.sql.*;
import java.util.ArrayList;

/**
 * The class is responsible for handling database operations about the admin objects.
 * Fetches admin data from the database.
 */
public class AdminRepository extends Repository {

    /**
     * Fetches all admins from the database.
     * @return a list of all admins
     * @throws SQLException if there's a database error
     */
    public ArrayList<Admin> getAll() throws SQLException {
        ArrayList<Admin> admins = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM admins")) {

            while (rs.next()) {
                Admin admin = new Admin(
                        rs.getInt("admin_id"),
                        rs.getString("username"),
                        rs.getString("password")
                        );
                admins.add(admin);
            }
        }
        return admins;
    }

    /**
     * Fetches one admin by their username.
     * @param userName the username of the admin
     * @return an admin, null if not found
     * @throws SQLException if there's a database error
     */
    public Admin getAdminByUserName(String userName) throws SQLException {
        String sql = "SELECT * FROM admins WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userName);

            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                return null;
            }
            return new Admin(rs.getInt("admin_id"),
                    rs.getString("username"),
                    rs.getString("password"));
        }
    }
}
