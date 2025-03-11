package Login;

import Admin.Admin;
import Admin.AdminRepository;
import Customer.CustomerRepository;
import Customer.Customer;

import java.sql.SQLException;
import java.util.Optional;

public class LoginService {

    CustomerRepository customerRepository;
    AdminRepository adminRepository;
    //LoginController loginController;

    public LoginService() {
        this.customerRepository = new CustomerRepository();
        this.adminRepository = new AdminRepository();
        //this.loginController = new LoginController();
    }
    private final Admin hardcodedAdmin = new Admin(1, "admin", "admin123"); //hårdkodad kod för inlgnning av admin


    public Customer loginAsCustomer(String email, String password) { //inloggning som customer
        try {
            Customer customer = customerRepository.getCustomerByEmail(email);

            if (customer == null) {
                System.out.println("No customer found");
            } else if (customer.getPassword().equals(password)) {
                System.out.println("Congrats! You've logged in.");
                return customer;
            } else {
                System.out.println("Wrong password");
            }
        }catch (SQLException e){
            System.out.println("Ett fel inträffade vi kommunikation med databas: " + e.getMessage());
        }catch (Exception e){
            System.out.println("Ett oväntat fel inträffade: "+ e.getMessage());
        }
        return null;
    }

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

