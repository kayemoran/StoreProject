package Order;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 * Class representation of an order in the system.
 */
public class Order {
    private int orderId;
    private int customerId;
    private Date orderDate;

    /**
     * Constructor to create an order object.
     * @param orderId the order ID
     * @param customerId ID of the customer who placed the order
     * @param orderDate the DATE of when the order was made
     */
    public Order(int orderId, int customerId, Date orderDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
    }

    /**
     * Sets the order ID.
     * @param orderId the new order ID
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * Gets the order ID.
     * @return the order ID
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Gets the customer ID.
     * @return the customer ID
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Gets the order Date.
     * @return the order date
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * Sets the order date.
     * @param orderDate the new order date
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * String representation of the order with details.
     * @return formatted string representing the order
     */
    public String toString() {
        return String.format("Order ID: %-5d | Customer ID: %-5d | Order Date: %-20s",
                this.orderId, this.customerId, this.orderDate);
    }

}
