package Admin;

import Customer.CustomerService;
import Order.OrderService;
import Product.Product;
import Product.ProductController;
import Product.ProductService;

import java.util.ArrayList;
import java.util.Scanner;
import java.sql.SQLException;

public class AdminController {
    private final Scanner scanner = new Scanner(System.in); //scannar inmatning från användare
    private final ProductService productService = new ProductService();
    private final CustomerService customerService = new CustomerService();
    private final OrderService orderService = new OrderService();
//skapar instans av services för att hantera ....

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
    private void manageProducts() throws SQLException{ //hanterar produkt menyn
      try {
        System.out.println("=== Manage Products Menu === ");
        System.out.println("1. Show products");
        System.out.println("2. Add product");
        System.out.println("3. Update product");
        System.out.println("4. Delete a product");
        System.out.println("0. Go back to Admin Menu");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1": //visar alla produkter
                ArrayList<Product> products = productService.showAllProducts();
                if (products.isEmpty()){
                    System.out.println("Inga produkter hittades i databass");
                }
                break;
            case "2": //lägger till en ny produkt
                ProductController productController = new ProductController();
                productController.run();
                break;

            case "3": //uppdaterar produkt
                System.out.print("Produkt ID att uppdatera: ");
                try {
                    int updateId = Integer.parseInt(scanner.nextLine()); //läs produkt id
                    System.out.print("Ange nytt pris: ");
                    double newPrice = Double.parseDouble(scanner.nextLine());

                    //här ska kod för uppdatering skrivas
                    Product productToUpdate = productService.getProductById(updateId); //hämtar produkt baserat på id
                    if (productToUpdate != null) {
                        productToUpdate.setPrice(newPrice); //uppdaterar priset på produkt
                        productService.updateProduct(productToUpdate); //uppdaterar produkt i databas
                        System.out.println("Produktens pris har uppdaterats ");
                    } else {
                        System.out.println("Produkt hittades ej. ");
                    }
                }catch (NumberFormatException e){
                    System.out.println("Produkt hittades ej.");
                }
               break;

            case "4":
            case "0":
                return;
            default:
                System.out.println("Felaktiv val");

        }
    } catch (SQLException e) {
          System.out.println("Ett fel inträffade");
        }
      }

    private void manageCustomers() throws SQLException{ //hanterar kund meny
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

    private void manageOrders() throws  SQLException{ //hanterar ordermny
        System.out.println("=== Manage Orders Menu ===");


    }

    private void manageStock() throws SQLException{ //hanterar lagerhantering
        System.out.println("=== Manage Stock Menu ===");
        System.out.println("=== Manage Orders Menu ===");
    }


    private void managePricing() throws SQLException{ //hanterar prissättning
        System.out.println("=== Manage Pricing Menu ===");
        System.out.println("1. Uppdatera produktpris");
        System.out.println("0.Gå tillbaka till Admin Meny");

        String choice = scanner.nextLine();

        switch (choice){
            case "1":
                updateProductPrice();
                //uppdatera pris på produkt
                break;

            case"0":
                return;
            default:
                System.out.println("Felaktig val");
        }
    }
    private void updateProductPrice() throws SQLException{ //uppdatera pris
        System.out.print("Ange produktens ID: ");

        try{
            int productId = Integer.parseInt(scanner.nextLine()); //läs produktens id

            Product product = productService.getProductById(productId); //hämtar produkt baserat på id
            if (product == null) {
                System.out.println("Ingen produkt hittades med det ID:t");
                return;
            }
            System.out.println("Nuvarande pris: "+ product.getPrice());
            System.out.print("Ange det nya priset: ");

            double newPrice = Double.parseDouble(scanner.nextLine()); //läser in nya priset

            if (newPrice < 0){
                System.out.println("Priset kan inte vara negativt. Försök igen.");
                return;
            }
            product.setPrice(newPrice); //uppdaterar priset
            productService.updateProduct(product); //uppdaterar proudkten i databas

            System.out.println("Produktens pris har uppdaterats till "+ newPrice);
        } catch (NumberFormatException e){
            System.out.println("Felaktig inmatning, vänligen ange ett giltigt nummer");
        }catch (SQLException e){
            System.out.println("Ett fel vid kommunikation med databasen: "+e.getMessage());
        }
    }
}
