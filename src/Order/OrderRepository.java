package Order;
import Customer.Customer;
import Super.Repository;

import java.sql.*;
import java.util.ArrayList;


public class OrderRepository extends Repository {
    public void addOrder(Order order) throws SQLException {
        String sql = "INSERT INTO orders (customer_id, order_date) " +
                "VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, order.getCustomerId());
            preparedStatement.setDate(2, order.getOrderDate());

            preparedStatement.executeUpdate();
        }
    }

    public ArrayList<Order> getAll() throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM orders")) {

            while (rs.next()) {

                java.sql.Date sqlDate = rs.getDate("order_date");

                Order order = new Order (
                        rs.getInt("order_id"),
                        rs.getInt("customer_id"),
                        sqlDate
                );
                orders.add(order);
            }
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
