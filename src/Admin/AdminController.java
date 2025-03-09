package Admin;

import Customer.CustomerService;
import Order.OrderService;
import Product.Product;
import Product.ProductController;
import Product.ProductService;
import java.util.Scanner;
import java.sql.SQLException;

public class AdminController {
    private final Scanner scanner = new Scanner(System.in);
    private final ProductService productService = new ProductService();
    private final CustomerService customerService = new CustomerService();
    private final OrderService orderService = new OrderService();


    public void showAdminMenu (Admin admin)  throws SQLException{ //admin menyn
        while (true) { //loop för att hålla kvar användaren i menyn
            System.out.println("==== Admin Menu ===");
            System.out.println("1. Manage Products");
            System.out.println("2. Manage Customers");
            System.out.println("3. Manage Orders");
            System.out.println("4. Manage Stock");
            System.out.println("5. Manage Pricing");
            System.out.println("0. Log out");
            System.out.println("Choose an option: ");

            String choice = scanner.nextLine();
            try{
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
                    break;
                case "0":
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid input try again: ");
                    break;
            }
        }catch (NumberFormatException e) {
    System.out.println("Felaktig inmatning, vänlig ange ett giltigt svar");
            }
        }
    }
    private void manageProducts() throws SQLException{
        System.out.println("=== Manage Products Menu === ");
        System.out.println("1. Show products");
        System.out.println("2. Add product");
        System.out.println("3. Update product");
        System.out.println("4. Delete a product");
        System.out.println("0. Go back to Admin Menu");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                productService.showAllProducts();
                break;
            case "2":
                ProductController productController = new ProductController();
                productController.run();
                break;

            case "3":
                System.out.print("Produkt ID att uppdatera: ");
                int updateId = Integer.parseInt(scanner.nextLine());
                System.out.print("Ange nytt pris: ");
                double newPrice = Double.parseDouble(scanner.nextLine());

                //här ska kod för uppdatering skrivas
                Product productToUpdate = productService.getProductById(updateId);
               if (productToUpdate != null) {
                   productToUpdate.setPrice(newPrice);
                   productService.updateProduct(productToUpdate);
                   System.out.println("Produktens pris har uppdaterats ");
               }else {
                   System.out.println("Produkt hittades ej. ");
               }
               break;

            case "4":
            case "0":
                return;
            default:
                System.out.println("Felaktiv val");

        }
    }

    private void manageCustomers() throws SQLException{
        System.out.println("=== Customer menu ===");
        System.out.println("1. Lista kunder");
        System.out.println("2. Uppdatera kund");
        System.out.println("3. Ta bort kund");
        System.out.println("0. Tillbaka till Admin meny");

        String choice = scanner.nextLine();

      switch (choice){
            case "1":
                customerService.showAllUsers();
                break;
            case"2":
                System.out.print("Kund Id att uppatera: ");
                break;
               /** int customerID = Integer.parseInt(scanner.nextLine());
                System.out.print("Ny e-post: ");
                String newEmail= scanner.nextLine();
                customerService.updateCustomerEmail(customerID, newEmail);
                 //här ska kod för uppdatera kund vara
*/
            case"3":
                System.out.print("Kund ID att ta bort: ");
                int deleteID = Integer.parseInt(scanner.nextLine());
                customerService.deleteCustomer(deleteID);
                System.out.println("Kund har tagits bort");
                break;
            case"0":
                return;
            default:
                System.out.println("Felaktig val.");
        }
    }

    private void manageOrders() throws  SQLException{
        System.out.println("=== Manage Orders Menu ===");

    }

    private void manageStock() throws SQLException{
        System.out.println("=== Manage Stock Menu ===");
    }

    private void managePricing() throws SQLException{
        System.out.println("=== Manage Pricing Menu ===");
        System.out.println("1. Uppdatera produktpris");
        System.out.println("2.TJOOOOO ");
        System.out.println("3. King e du  ");

        String choice = scanner.nextLine();

        switch (choice){
            case "1":
                System.out.println("JKknjn");
                //skriv kod för uppdatera pris på produkt
                break;

            case"2":
                System.out.println("UwU");
                break;

            case "3":
                System.out.println("Wuff wuff");
                break;
        }

    }
}
