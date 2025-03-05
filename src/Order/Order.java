package Order;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;


public class Order {
    private int orderId;
    private int customerId;
    private Date orderDate;

    public Order(int orderId, int customerId, Date orderDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public int getOrderId() {
        return orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String toString() {
        return String.format("Order ID: %-5d | Customer ID: %-5d | Order Date: %-20s",
                this.orderId, this.customerId, this.orderDate);
    }

}
