package Order;

import java.sql.SQLException;

public class OrderService {
    OrderRepository orderRepository;

    public OrderService() {
        this.orderRepository = new OrderRepository();
    }

    public void addOrder(Order order) throws SQLException {
        orderRepository.addOrder(order);
    }

    public void addOrderProduct(OrderProduct orderProduct) throws SQLException {
        orderRepository.addOrderProduct(orderProduct);
    }
}

