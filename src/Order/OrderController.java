package Order;

import Product.Product;
import Product.ProductRepository;
import Product.ProductService;

import javax.swing.plaf.BorderUIResource;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderController { //mellanhand mellan användare och orderdata
    private OrderRepository orderRepository; //koppling till databas
    private ProductRepository productRepository; //hämtar produkter
    private ProductService productService;
    private OrderService orderService;
    private Scanner scanner;

    public OrderController() {
        this.orderRepository = new OrderRepository();
        this.productRepository = new ProductRepository();
        this.productService = new ProductService();
        this.orderService = new OrderService();
        this.scanner = new Scanner(System.in);
    }

    public void placeOrder(int customerId) {
        try {
            //Displays products
            ArrayList<Product> products = productService.showAllProducts();
            System.out.println("Products:");
            for (Product product : products) {
                System.out.println(product);
            }

            ArrayList<OrderProduct> orderProducts = new ArrayList<>();
            boolean addingProducts = true;

            while (addingProducts) {
                System.out.print("Enter the Product ID you want to buy: ");
                int productId = Integer.parseInt(scanner.nextLine());

                System.out.println("Enter quantity: ");
                int quantity = Integer.parseInt(scanner.nextLine());

                Product selectedProduct = null;
                for (Product product : products) {
                    if (product.getProductId() == productId) {
                        selectedProduct = product;
                        break;
                    }

                }

                if (selectedProduct == null) {
                    System.out.println("Invalid Product ID.");

                } else {
                    Order newOrder = new Order(0,customerId, new java.sql.Date(System.currentTimeMillis()));
                    OrderProduct orderProduct = new OrderProduct(newOrder, selectedProduct, quantity, selectedProduct.getPrice());
                    orderProducts.add(orderProduct);
                }


                System.out.println("Do you want to continue shopping? (yes/no): ");
                String answer = scanner.nextLine();
                if ("no".equalsIgnoreCase(answer)) {
                    addingProducts = false;
                }
            }


            Order newOrder = new Order(0, customerId, new java.sql.Date(System.currentTimeMillis()));
            orderService.addOrder(newOrder);

            for (OrderProduct orderProduct : orderProducts) {
                System.out.println();
                System.out.println("Product: " + orderProduct.getProduct().getName() + " x " +
                        orderProduct.getQuantity());
                System.out.println("Unit Price: " + orderProduct.getUnitPrice());
                System.out.println("Total Price: " + orderProduct.getTotalPrice());
                orderService.addOrderProduct(orderProduct);
                productService.updateStockQuantity(orderProduct.getProduct().getProductId(), orderProduct.getQuantity());
            }

            System.out.println("Continuing to payment...");
            System.out.println();
            orderService.proceedToPayment(newOrder);

        } catch (SQLException e) {
            System.out.println("Error placing the order.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }


}
