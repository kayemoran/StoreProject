package Product;

import Super.*;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Repository class that handles the interaction with the database when it comes to product.
 *
 */
public class ProductRepository extends Repository{

    private static final String URL = "jdbc:sqlite:webbutiken.db";

    /**
     * Creates a connection with the database.
     * @return a Connection object to the database
     * @throws SQLException if there's a database error
     */
    public static Connection getConnection()throws SQLException{
        return DriverManager.getConnection(URL);
    }

    /**
     * Gets all products from the database.
     * @return a list of all products in the database
     * @throws SQLException if there's a database error
     */
    public ArrayList<Product> getAll() throws SQLException {
        ArrayList<Product> products = new ArrayList<>();
        String sql = "SELECT id, namn price FROM products";

        // try-with-resources stänger automatiskt Connection, Statement och ResultSet
        try (Connection conn = DriverManager.getConnection(URL); //anslunting till databas
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM products")) {

            // Loopa igenom alla rader från databasen
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity")
                );
                products.add(product);
                //System.out.println(product.toString());
            }
        }
        return products;
    }


    /**
     * Gets a product from the database by ID
     * @param productId the product to get
     * @return the product, null if not found
     * @throws SQLException if there's a database error
     */
    public Product getProductById(int productId) throws SQLException {

        String sql = "SELECT * FROM products WHERE product_id = ?";

        // try-with-resources stänger automatiskt Connection, Statement och ResultSet
        try (Connection conn = DriverManager.getConnection(URL); //anslunting till databas
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, productId);

                ResultSet rs = preparedStatement.executeQuery();
                //Loopa igenom alla rader från databasen

                if (!rs.next()) {
                    return null;
                }

                return new Product(
                    rs.getInt("product_id"),
                    //rs.getInt("manufacturer_id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getInt("stock_quantity")
                );
                //System.out.println(product.toString());

        }
    }

    /**
     * Gets a product by name
     * @param productName
     * @return the product, null if not found
     * @throws SQLException if there's a database error
     */
    public Product getProductByName(String productName) throws SQLException {
        String sql = "SELECT * FROM products WHERE name = ?";

        try (Connection connection = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, productName);

            ResultSet rs = preparedStatement.executeQuery();

            if (!rs.next()) {
                return null;
            }

            return new Product(
                    rs.getInt("product_id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getInt("stock_quantity")
            );
        }
    }

    /**
     * Gets all categories in the database.
     * @return a list of category name
     * @throws SQLException if there's a database connection
     */
    public ArrayList<String> getAllCategories() throws SQLException {
        ArrayList<String> categories = new ArrayList<>();

        String sql = "SELECT name FROM categories";

        try (Connection conn = DriverManager.getConnection(URL); //anslunting till databas
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            ResultSet rs = preparedStatement.executeQuery();
            //Loopa igenom alla rader från databasen

            while (rs.next()) {
                categories.add(rs.getString("name"));
            }

        }
        return categories;
    }

    /**
     * Gets product from the database based on category name.
     * @param categoryName the name of the category to filter products by
     * @return a list of products that belong to the category
     * @throws SQLException if there's a database error
     */
    public ArrayList<Product> getProductByCategory(String categoryName) throws SQLException {
        ArrayList<Product> products = new ArrayList<>();
        String sql = "SELECT p.* FROM products p " +
                "JOIN products_categories pc ON p.product_id = pc.product_id " +
                "JOIN categories c ON c.category_id = pc.category_id " +
                "WHERE c.name = ?";

        try (Connection connection = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, categoryName);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity")
                );
                products.add(product);
            }
        }
        return products;
    }

    /**
     * Updates the stock quantity of a product in the database.
     * @param productId the product ID
     * @param quantityOrdered the quantity to subtract from the stock
     * @throws SQLException if there's a database error
     */
    public void updateStockQuantity(int productId, int quantityOrdered) throws SQLException {
        String sql = "UPDATE products SET stock_quantity = stock_quantity - ? WHERE product_id = ? AND stock_quantity >= ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, quantityOrdered); //The quantity substracted
            preparedStatement.setInt(2, productId);
            preparedStatement.setInt(3, quantityOrdered); //Checks if there's enough

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Stock updated successfully.");

            }else {
                System.out.println("Failed to update stock. Product not found or not enough stock.");
            }
        }
    }

    /**
     * Updates the price of a product in the database.
     * @param product the product with the updated price
     * @throws SQLException if there's a database error
     */
    public void updateProduct (Product product) throws SQLException {
        String sql = "UPDATE products SET price = ? WHERE product_id = ?";

        try (Connection conn = ProductRepository.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, product.getPrice());
            pstmt.setInt(2, product.getProductId());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                System.out.println("Ingen produkt uppdaterades. Kontrollera att produkt ID är rätt.");
            } else {
                System.out.println("Produktens pris har uppdaterats i databasen");
            }
        }catch (SQLException e){
            System.out.println("Fel vid uppdatering av produkt: "+ e.getMessage());

        }
    }

}