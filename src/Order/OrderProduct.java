package Order;

import Product.Product;

public class OrderProduct {
    private int orderProductId;
    private Order order;
    private Product product;
    private int quantity;
     private double unitPrice;

     public OrderProduct(Order order, Product product, int quantity, double unitPrice) {
         this.order = order;
         this.product = product;
         this.quantity = quantity;
         this.unitPrice = product.getPrice();
     }

     public int getOrderProductId() {
         return orderProductId;
     }

     public Order getOrder() {
         return order;
     }

     public Product getProduct() {
         return product;
     }

     public int getQuantity() {
         return quantity;
     }

     public double getUnitPrice() {
         return unitPrice;
     }

     public double getTotalPrice() {
         return unitPrice * quantity;
     }
}

