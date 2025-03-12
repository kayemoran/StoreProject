package Order;

import Product.Product;

/**
 * A class that represents a product in an order.
 * Links a product with an order.
 */
public class OrderProduct {
    private int orderProductId;
    private Order order;
    private Product product;
    private int quantity;
     private double unitPrice;

    /**
     * Constructor to create an object of OrderProduct.
     * @param order the order the product is in
     * @param product the product being ordered
     * @param quantity the quantity of the product
     * @param unitPrice the price of the product
     */
     public OrderProduct(Order order, Product product, int quantity, double unitPrice) {
         this.order = order;
         this.product = product;
         this.quantity = quantity;
         this.unitPrice = product.getPrice();
     }

     public int getOrderProductId() {
         return orderProductId;
     }

    /**
     * Gets the order the product belongs to.
     * @return the order the products belongs to
     */
     public Order getOrder() {
         return order;
     }

    /**
     * Gets the product that belongs to the order.
     * @return the product being ordered
     */
     public Product getProduct() {
         return product;
     }

    /**
     * Gets the quantity of the product being ordered.
     * @return the quantity of the product being ordered
     */
     public int getQuantity() {
         return quantity;
     }

    /**
     * Gets the unit price of the product being ordered.
     * @return the unit price
     */
     public double getUnitPrice() {
         return unitPrice;
     }

    /**
     * Gets the total price of the product based on the quantity and unit price.
     * @return the total price
     */
     public double getTotalPrice() {
         return unitPrice * quantity;
     }
}

