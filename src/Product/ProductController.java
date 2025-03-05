package Product;

import java.sql.SQLException;
import java.util.Scanner;


public class ProductController {

    // Service-lager för kundhantering, hanterar affärslogik
    ProductRepository productRepository;
    ProductService productService;

    // Scanner för användarinput
    Scanner scanner;

    public ProductController() {
        // Skapa instanser av nödvändiga objekt
        this.productService = new ProductService();
        this.productRepository = new ProductRepository();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Huvudloop för kundhantering
     * Visar meny och hanterar användarval
     */
    public void run() {
        while (true) {
            try {
                // Skriv ut menyalternativ direkt i run-metoden för tydlighet
                System.out.println("\n=== Produkthantering ===");
                System.out.println("1. Visa alla produkter");
                System.out.println("2. Visa en produkt");
                System.out.println("3. Lägg till en produkt");
                System.out.println("0. Avsluta");
                System.out.print("Välj ett alternativ: ");

                // Läs användarens val
                int select = scanner.nextInt();

                // Hantera användarens val
                switch (select) {
                    case 1:
                        // Anropa service-lagret för att visa alla kunder
                        productService.showAllProducts();
                        break;
                    case 2:
                        // Anropa service-lagret för att visa en kund baserat på id
                        System.out.println("Ange ID");
                        int id = scanner.nextInt();
                        //customerService.showUserById(id);
                        break;
                    case 3:
                        System.out.println("Ange namn");
                        String name = scanner.next();
                        System.out.println("Ange beskrivning");
                        String description = scanner.next();
                        System.out.println("Ange pris");
                        double price = scanner.nextDouble();
                        System.out.println("Ange antal i lager");
                        int stockQuantity = scanner.nextInt();

                        Product product = new Product(name, description, price, stockQuantity);
                        productService.addProduct(product);
                        break;
                    case 0:
                        System.out.println("Avslutar kundhantering...");
                        return;
                    default:
                        System.out.println("Ogiltigt val, försök igen");
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
}