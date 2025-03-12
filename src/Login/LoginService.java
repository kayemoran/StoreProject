package Login;

import Admin.Admin;
import Admin.AdminRepository;
import Customer.CustomerRepository;
import Customer.Customer;

import java.sql.SQLException;
import java.util.Optional;

/**
 * A class that handles login logic for users.
 */
public class LoginService {

    CustomerRepository customerRepository;
    AdminRepository adminRepository;
    //LoginController loginController;

    /**
     * Constructor that initializes the customer and admin repositories.
     */
    public LoginService() {
        this.customerRepository = new CustomerRepository();
        this.adminRepository = new AdminRepository();
        //this.loginController = new LoginController();
    }

    //hårdkodad admin konto
    private final Admin hardcodedAdmin = new Admin(1, "admin", "admin123");


    /**
     * Method that allows customer to log in with email and password.
     *
     * @param email customer's email
     * @param password customer's password
     * @return the customer object, null if login fails
     */
    public Customer loginAsCustomer(String email, String password) { //inloggning som customer
        try {
            //Hämtar customer med angiven email
            Customer customer = customerRepository.getCustomerByEmail(email);

            if (customer == null) {
                System.out.println("No customer found");
            } else if (customer.getPassword().equals(password)) {
                //Om lösenord matchar, logga in
                System.out.println("Congrats! You've logged in.");
                return customer;
            } else {
                System.out.println("Wrong password");
            }
        }catch (SQLException e){
            System.out.println("Ett fel inträffade vi kommunikation med databas: " + e.getMessage());
        }catch (Exception e){
            //Skriver ut ett felmeddelande
            System.out.println("Ett oväntat fel inträffade: "+ e.getMessage());
        }
        return null;
    }

    /**
     * Method that allows admind to log in with username and password.
     * @param userName admin's username
     * @param password admin's password
     * @return the admin object, null if login fails
     */
    public Admin loginAsAdmin(String userName, String password) { //om man loggar in genom hårdkodad admin
        try {
            if (hardcodedAdmin.getUserName().equals(userName) && hardcodedAdmin.getPassword().equals(password)) { // hårdkodad admin
                System.out.println("Admin login successful!"); //meddelande
                return hardcodedAdmin;
            }


            Admin admin = adminRepository.getAdminByUserName(userName); //hämtar admin från databas

            if (admin != null && admin.getPassword().equals(password)) { //om admin finns i databas och lösenordet matchar
                System.out.println("Admin login successful!");
                return admin;
            }
            System.out.println("No admin found with that username");
        } catch (SQLException e) {
            System.out.println("Ett fel intrffade vid kommunikation med databas: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ett oväntat fel inträffade: " + e.getMessage());
        }
        return null;
    }
}

