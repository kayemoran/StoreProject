package Login;

import Admin.Admin;
import Customer.Customer;
import Customer.CustomerController;
import Customer.CustomerService;
import Order.OrderController;
import Order.OrderRepository;
import Product.Product;
import Product.ProductRepository;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class LoginController {

    LoginService loginService;
    OrderController orderController;
    CustomerService customerService;
    ProductRepository productRepository;
    OrderRepository orderRepository;

    // Scanner för användarinput
    Scanner scanner;

    public LoginController() {
        // Skapa instanser av nödvändiga objekt
        this.orderController = new OrderController();
        this.loginService = new LoginService();
        this.customerService = new CustomerService();
        this.productRepository = new ProductRepository();
        this.orderRepository = new OrderRepository();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            try {
                System.out.println("\n=== Login ===");
                System.out.println("1. Log in as customer");
                System.out.println("2. Log in as Admin");
                System.out.println("3. Register new customer");
                System.out.println("0. Exit");
                System.out.print("Choose from one of the options: ");

                String select = scanner.nextLine();

                switch (select) {
                    case "1":
                        System.out.println("Enter email:");
                        String email = scanner.nextLine();
                        System.out.println("Enter password");
                        String password = scanner.nextLine();
                        Customer customer = loginService.loginAsCustomer(email, password);

                        if (customer != null) {
                            System.out.println("Welcome, " + customer.getUserName() + "!");
                            showCustomerMenu(customer); //Shows menu for customers
                            return; // Stop login menu from showing after login
                        }
                        break;
                    case "2":
                        System.out.println("Enter name:");
                        String adminUsername = scanner.nextLine();
                        System.out.println("Enter password");
                        String adminPassword = scanner.nextLine();
                        Admin admin = loginService.loginAsAdmin(adminUsername, adminPassword);
                        //  loginService.loginAsAdmin(userName, adminPassword);

                        if (admin != null) {
                            System.out.println("Welcome Admin, " + admin.getUserName() + "!");
                            showAdminMenu(admin); //visa meny för admins
                            return;
                        }
                        break;

                    case "3":
                        System.out.println("Enter name");
                        String username = scanner.nextLine();

                        System.out.println("Enter email");
                        String eMail = scanner.nextLine();

                        System.out.println("Enter phone number");
                        String phoneNmbr = scanner.nextLine();

                        System.out.println("Enter address");
                        String address = scanner.nextLine();

                        System.out.println("Enter password");
                        String userPassword = scanner.nextLine();

                        customerService.addCustomer(username, eMail, phoneNmbr, address, userPassword);
                        break;
                    case "0":
                        System.out.println("Exiting customer management...");
                        return;
                    default:
                        System.out.println("Input not valid. Please try again.");
                        break;
                }
            } catch (SQLException e) {
                // Hantera databasfel
                System.out.println("Ett fel uppstod vid databasanrop: " + e.getMessage());
            } catch (Exception e) {
                // Hantera övriga fel (t.ex. felaktig input)
                System.out.println("Ett oväntat fel uppstod: " + e.getMessage());
                scanner.nextLine(); // Rensa scanner-bufferten vid felinmatning
            }
        }
    }


    private void showAdminMenu (Admin admin)  throws SQLException{ //admin menyn
        while (true) {
            System.out.println("==== Admin Menu ===");
            System.out.println("1. Manage Products");
            System.out.println("2. Manage Customers");
            System.out.println("3. Manage Stock");
            System.out.println("4. Manage Pricing");
            System.out.println("0. Log out");
            System.out.println("Choose an option: ");

           String choice = scanner.nextLine();
/**
            switch (choice){
                case "1":
                    manageProducts();
                    break;
                case "2":
                    manageCustomers();
                    break;
                case "3":
                    manageOrders();
                    break;
                case "4":
                    manageStock();
                    break;
                case "5":
                    managePricing();
                case "0":
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid input try again: ");
                    break;
            }*/
        }
    }

    private void showCustomerMenu(Customer customer) throws SQLException {
        while (true) {
            System.out.println("\n=== Customer Menu ===");
            System.out.println("1. View products");
            System.out.println("2. Place an order");
            System.out.println("3. Order history");
            System.out.println("0. Log out");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    //Retrieve products
                    ArrayList<Product> products = productRepository.getAll();

                    //Print out products
                    System.out.println("Products: ");
                    for (Product product : products) {
                        System.out.println(product);
                    }
                    break;
                case "2":
                    orderController.placeOrder(customer.getId());
                    break;
                case "3":
                    orderRepository.getAll();
                    break;
                case "0":
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Input not valid. Please try again.");
                    break;


            }
        }
    }
}
