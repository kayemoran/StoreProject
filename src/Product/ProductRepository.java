package Product;

import Super.*;

import java.sql.*;
import java.util.ArrayList;

public class ProductRepository extends Repository{

    public ArrayList<Product> getAll() throws SQLException {
        ArrayList<Product> products = new ArrayList<>();

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


    public void deleteAProduct(Product product) throws SQLException {
        String sqlDelete = ("DELETE from products WHERE product_id = ?");
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement preparedStatement = connection.prepareStatement(sqlDelete)) {
            preparedStatement.setInt(1, product.getProductId());

            int delete = preparedStatement.executeUpdate();

            if (delete > 0) {
                System.out.println("Product deleted successfully.");
            } else {
                System.out.println("The inserted ID could not be found.");
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



}