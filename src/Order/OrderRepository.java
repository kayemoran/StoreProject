package Order;
import Customer.Customer;
import Product.Product;
import Super.Repository;

import java.sql.*;
import java.util.ArrayList;


public class OrderRepository extends Repository {
    public int addOrder(Order order) throws SQLException {
        String sql = "INSERT INTO orders (customer_id, order_date) " +
                "VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, order.getCustomerId());
            preparedStatement.setDate(2, order.getOrderDate());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
        }
        return -1; //if failed
    }

    public ArrayList<Order> getOrdersByCustomer(int customerId) {
        ArrayList<Order> orders = new ArrayList<>();

        String sql = "SELECT * FROM orders WHERE customer_id = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1,customerId);

                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    Order order = new Order (
                            rs.getInt("order_id"),
                            rs.getInt("customer_id"),
                            rs.getDate("order_date")

                    );
                    orders.add(order);
                }

        } catch (SQLException e) {
            System.out.println("Failed to fetch Orders by Customer: " + e.getMessage());
        }
        return orders;
    }


    public void addOrderProduct(OrderProduct orderProduct) throws SQLException {
        String sql = "INSERT INTO orders_products (order_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, orderProduct.getOrder().getOrderId());
            preparedStatement.setInt(2, orderProduct.getProduct().getProductId());
            preparedStatement.setInt(3, orderProduct.getQuantity());
            preparedStatement.setDouble(4, orderProduct.getUnitPrice());

            preparedStatement.executeUpdate();
        }
    }

}
