package Order;

import Product.Product;
import Product.ProductRepository;

import javax.swing.plaf.BorderUIResource;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderController { //mellanhand mellan användare och orderdata
    private OrderRepository orderRepository; //koppling till databas
    private ProductRepository productRepository; //hämtar produkter
    private Scanner scanner;

    public OrderController() {
        this.orderRepository = new OrderRepository();
        this.productRepository = new ProductRepository();
        this.scanner = new Scanner(System.in);
    }

    public void placeOrder(int customerId) {
        try {
            //Displays products
            ArrayList<Product> products = productRepository.getAll();
            System.out.println("Products:");
            for (Product product : products) {
                System.out.println(product);
            }

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
                return;
            }

            Order newOrder = new Order(0, customerId, new java.sql.Date(System.currentTimeMillis()));
            orderRepository.addOrder(newOrder); //skapar ny order och spara i databas

        } catch (SQLException e) {
            System.out.println("Error placing the order.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }


}
