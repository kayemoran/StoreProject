package Order;

import Product.Product;
import Product.ProductRepository;
import Product.ProductService;

import javax.swing.plaf.BorderUIResource;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Controller class that handles user interaction in relation with placing orders.
 * Manages the process of ordering.
 */
public class OrderController { //mellanhand mellan användare och orderdata
    private OrderRepository orderRepository; //koppling till databas
    private ProductRepository productRepository; //hämtar produkter
    private ProductService productService;
    private OrderService orderService;
    private Scanner scanner;

    /**
     * Constructor that initializes the controller.
     * Sets up repositories, services and objects.
     */
    public OrderController() {
        this.orderRepository = new OrderRepository();
        this.productRepository = new ProductRepository();
        this.productService = new ProductService();
        this.orderService = new OrderService();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Lets the customer place an order.
     * Customer can select products and quantities.
     * The customer's order gets saved in the database.
     * @param customerId the ID of the customer who's placing the order
     */
    public void placeOrder(int customerId) {
        try {
            //Displays products
            ArrayList<Product> products = productService.showAllProducts();
            System.out.println("Products:");
            for (Product product : products) {
                System.out.println(product);
            }

            //En lista för att lagra produkter kunden ska beställa
            ArrayList<OrderProduct> orderProducts = new ArrayList<>();
            boolean addingProducts = true;

            while (addingProducts) {
                System.out.print("Enter the Product ID you want to buy: ");
                int productId = Integer.parseInt(scanner.nextLine());

                //Hitta den valda produkten från produktlistan
                Product selectedProduct = null;
                for (Product product : products) {
                    if (product.getProductId() == productId) {
                        selectedProduct = product;
                        break;
                    }

                }

                if (selectedProduct == null) {
                    System.out.println("Invalid Product ID.");
                    continue;
                }

                int quantity;
                while (true) {
                    System.out.println("Enter quantity: ");
                    quantity = Integer.parseInt(scanner.nextLine());

                    //Om det inte finns tillräckligt med lager
                    if (quantity > selectedProduct.getStockQuantity()) {
                        System.out.println("Sorry, only " + selectedProduct.getStockQuantity() + " available in stock.");
                    } else {
                        break;
                    }
                }

                //Skapar ny order
                Order newOrder = new Order(0,customerId, new java.sql.Date(System.currentTimeMillis()));
               //Ny orderProduct, produkt som ingår i ordern
                OrderProduct orderProduct = new OrderProduct(newOrder, selectedProduct, quantity, selectedProduct.getPrice());
               //Lägger till i listan
                orderProducts.add(orderProduct);


                System.out.println("Do you want to continue shopping? (yes/no): ");
                String answer = scanner.nextLine();
                if ("no".equalsIgnoreCase(answer)) {
                    addingProducts = false;
                }
            }

            //Skapa ny order och lägg till i databasen
            Order newOrder = new Order(0, customerId, new java.sql.Date(System.currentTimeMillis()));
            orderService.addOrder(newOrder);

            for (OrderProduct orderProduct : orderProducts) {
                System.out.println();
                System.out.println("Product: " + orderProduct.getProduct().getName() + " x " +
                        orderProduct.getQuantity());
                System.out.println("Unit Price: " + orderProduct.getUnitPrice());
                System.out.println("Total Price: " + orderProduct.getTotalPrice());

                //Lägger till produkterna i ordern i databasen
                orderService.addOrderProduct(orderProduct);
                //Uppdaterar lagersaldot för produkterna
                productService.updateStockQuantity(orderProduct.getProduct().getProductId(), orderProduct.getQuantity());
            }

            System.out.println();
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
