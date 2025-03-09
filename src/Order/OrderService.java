package Order;

import Customer.Customer;
import Product.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderService {
    OrderRepository orderRepository;
    OrderProduct orderProduct;
    Scanner scanner;


    public OrderService() {
        this.scanner = new Scanner(System.in);
        this.orderRepository = new OrderRepository();
    }

    public void addOrder(Order order) throws SQLException {
        int orderId = orderRepository.addOrder(order);
        if (orderId != -1) {
            order.setOrderId(orderId);
            System.out.println("Order ID: " + orderId);
        } else {
            System.out.println("Failed to place the order.");
        }
    }

    public void addOrderProduct(OrderProduct orderProduct) throws SQLException {
        orderRepository.addOrderProduct(orderProduct);
    }

    public ArrayList<Order> showOrderHistory(int customerId) throws SQLException {
        ArrayList<Order> orders = orderRepository.getOrdersByCustomer(customerId);

        System.out.println();

        if (orders.isEmpty()) {
            System.out.println("No orders found.");
        }

        // Skriv ut alla kunder med tydlig formatering
        System.out.println("\n=== Order History ===");
        for (Order order : orders) {
            System.out.println("Order ID: " + order.getOrderId());
            System.out.println("Date: " + order.getOrderDate());
            System.out.println("Customer ID: " + order.getCustomerId());
            System.out.println();
        }
        return orders;
    }

    public void proceedToPayment(Order order) {
        boolean successful = processPayment(order);

        if (successful) {
            System.out.println("Your payment is successful!");
            generateReceipt(order);
            System.out.println();
            System.out.println("----- Welcome back ----- ");
        } else {
            System.out.println("Payment failed. Please try again.");
        }
    }

    public boolean processPayment(Order order) {
        System.out.println("Enter card number:");
        int number = scanner.nextInt();

        System.out.println();
        System.out.println("Controlling card " + number + " ...");
        System.out.println("Card is valid!");
        System.out.println();
        return true;
    }

    public void generateReceipt(Order order) {
        System.out.println("----- Receipt -----");
        System.out.println();
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Customer ID: " + order.getCustomerId());
    }


}

