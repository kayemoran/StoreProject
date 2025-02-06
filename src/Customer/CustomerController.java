package Customer;

import java.sql.SQLException;
import java.util.Scanner;

public class CustomerController {

    CustomerService customerService;

    Scanner scanner;

    public CustomerController() {
        this.customerService = new CustomerService();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        // ANSI färger och formatering
        final String RESET = "\u001B[0m";
        final String RED = "\u001B[31m";
        final String GREEN = "\u001B[32m";
        final String YELLOW = "\u001B[33m";
        final String BLUE = "\u001B[34m";
        final String PURPLE = "\u001B[35m";
        final String CYAN = "\u001B[36m";
        final String BOLD = "\u001B[1m";

        // Unicode symboler
        final String CUSTOMER = "👤";
        final String LIST = "📋";
        final String ADD = "➕";
        final String SEARCH = "🔍";
        final String EXIT = "🚪";
        final String ARROW = "➜";
        final String SUCCESS = "✅";
        final String ERROR = "❌";
        final String STAR = "⭐";

        while (true) {
            try {
                // Snyggt formaterad meny med symboler
                System.out.println("\n" + BOLD + BLUE + "╔══════════════════════════╗" + RESET);
                System.out.println(BOLD + BLUE + "║    " + PURPLE + "Kundhantering " + CUSTOMER + "    " + BLUE + "║" + RESET);
                System.out.println(BOLD + BLUE + "╠══════════════════════════╣" + RESET);
                System.out.println(BOLD + BLUE + "║ " + CYAN + "1. " + LIST + " Visa alla kunder    " + BLUE + "║" + RESET);
                System.out.println(BOLD + BLUE + "║ " + CYAN + "2. " + SEARCH + " Visa en kund       " + BLUE + "║" + RESET);
                System.out.println(BOLD + BLUE + "║ " + CYAN + "3. " + ADD + " Lägg till en kund   " + BLUE + "║" + RESET);
                System.out.println(BOLD + BLUE + "║ " + RED + "0. " + EXIT + " Avsluta           " + BLUE + "║" + RESET);
                System.out.println(BOLD + BLUE + "╚══════════════════════════╝" + RESET);
                System.out.print(YELLOW + ARROW + " Välj ett alternativ: " + RESET);

                String select = scanner.nextLine();

                switch (select) {
                    case "1":
                        System.out.println(BOLD + CYAN + "\n=== Visar alla kunder " + LIST + " ===" + RESET);
                        customerService.showAllUsers();
                        System.out.println(GREEN + SUCCESS + " Alla kunder listade framgångsrikt!" + RESET);
                        break;
                    case "2":
                        System.out.println(BOLD + CYAN + "\n=== Sök kund " + SEARCH + " ===" + RESET);
                        System.out.print(YELLOW + ARROW + " Ange ID: " + RESET);
                        String idInput = scanner.nextLine();
                        int id = Integer.parseInt(idInput);
                        customerService.showUserById(id);
                        System.out.println(GREEN + SUCCESS + " Kund hittad!" + RESET);
                        break;
                    case "3":
                        System.out.println(BOLD + CYAN + "\n=== Lägg till ny kund " + ADD + " ===" + RESET);
                        System.out.print(YELLOW + ARROW + " Namn: " + RESET);
                        String name = scanner.nextLine();
                        System.out.print(YELLOW + ARROW + " Email: " + RESET);
                        String email = scanner.nextLine();
                        System.out.print(YELLOW + ARROW + " Telefon: " + RESET);
                        String phone = scanner.nextLine();
                        System.out.print(YELLOW + ARROW + " Adress: " + RESET);
                        String address = scanner.nextLine();
                        System.out.print(YELLOW + ARROW + " Lösenord: " + RESET);
                        String password = scanner.nextLine();
                        customerService.addCustomer(name, email, phone, address, password);
                        System.out.println(GREEN + SUCCESS + " Ny kund tillagd framgångsrikt!" + RESET);
                        break;
                    case "0":
                        System.out.println(PURPLE + "\n" + STAR + " Avslutar kundhantering... " + EXIT + RESET);
                        return;
                    default:
                        System.out.println(RED + ERROR + " Ogiltigt val, försök igen" + RESET);
                }
            } catch (SQLException e) {
                System.out.println(RED + ERROR + " Databasfel: " + e.getMessage() + RESET);
            } catch (Exception e) {
                System.out.println(RED + ERROR + " Ett fel uppstod: " + e.getMessage() + RESET);
                scanner.nextLine();
            }
        }
    }
}