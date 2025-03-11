package Product;

import Super.*;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductRepository extends Repository{

    private static final String URL = "jdbc:sqlite:webbutiken.db";
    public static Connection getConnection()throws SQLException{
        return DriverManager.getConnection(URL);
    }
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

    public void addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO products (name, description, price, stock_quantity) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getStockQuantity());

            //check how many rows were affected and executes
            int rowInserted = preparedStatement.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("Product added successfully.");


            } else {
                System.out.println("Failed to add product.");
            }
        }
    }



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
                    //rs.getInt("manufacturer_id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getInt("stock_quantity")
            );
        }
    }

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