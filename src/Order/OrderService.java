package Order;

import Customer.Customer;
import Product.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Service class that handles the logic linked to orders.
 */
public class OrderService {
    OrderRepository orderRepository;
    OrderProduct orderProduct;
    Scanner scanner;


    /**
     * Constructor that initializes OrderService.
     */
    public OrderService() {
        this.scanner = new Scanner(System.in);
        this.orderRepository = new OrderRepository();
    }

    /**
     * Adds a new order to the system by calling the order repo.
     * @param order the order that is being added
     * @throws SQLException if there's a database error
     */
    public void addOrder(Order order) throws SQLException {
        int orderId = orderRepository.addOrder(order);
        if (orderId != -1) {
            order.setOrderId(orderId);
            System.out.println("Order ID: " + orderId);
        } else {
            System.out.println("Failed to place the order.");
        }
    }

    /**
     * Adds a product to an order by calling the method handling this in the order repo.
     * @param orderProduct the orderProduct to be added
     * @throws SQLException if there's a database error
     */
    public void addOrderProduct(OrderProduct orderProduct) throws SQLException {
        orderRepository.addOrderProduct(orderProduct);
    }

    /**
     * Shows the order history of one customer.
     * @param customerId the ID of the customer the orders belong to
     * @return a list of orders made by the customer
     */
    public ArrayList<Order> showOrderHistory(int customerId) {
        ArrayList<Order> orders = orderRepository.getOrdersByCustomer(customerId);

        System.out.println();

        if (!orders.isEmpty()) {
            System.out.println("\n=== Order History ===");
            for (Order order : orders) {
                System.out.println("Order ID: " + order.getOrderId());
                System.out.println("Date: " + order.getOrderDate());
                System.out.println("Customer ID: " + order.getCustomerId());
                System.out.println();
            }

        } else {
            System.out.println("No orders have been made yet.");
        }

        return orders;
    }

    /**
     * Handles the payment process.
     * @param order the order that is being processed for payment
     */
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

    /**
     * A simulation for payment process.
     * @param order the order to be paid for
     * @return true if successful, false if unsuccessful
     */
    public boolean processPayment(Order order) {
        System.out.println("Enter card number:");
        int number = scanner.nextInt();

        System.out.println();
        System.out.println("Controlling card " + number + " ...");
        System.out.println("Card is valid!");
        System.out.println();
        return true;
    }

    /**
     * Generates a receipt with details for a processed order.
     * @param order the order that receipt is being generated for
     */
    public void generateReceipt(Order order) {
        System.out.println("----- Receipt -----");
        System.out.println();
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Customer ID: " + order.getCustomerId());
    }


}

