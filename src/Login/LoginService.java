package Login;

import Admin.Admin;
import Admin.AdminRepository;
import Customer.CustomerRepository;
import Customer.Customer;

import java.sql.SQLException;

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


    public Customer loginAsCustomer(String email, String password) throws SQLException { //inloggning som customer

        Customer customer = customerRepository.getCustomerByEmail(email);

        if(customer == null){
            System.out.println("No customer found");
        }
        else if(customer.getPassword().equals(password)){
            System.out.println("Congrats! You've logged in.");
            return customer;
        }
        else{
            System.out.println("Wrong password");
        }
        return null;
    }

    public Admin loginAsAdmin(String userName, String password) throws SQLException { //om man loggar in genom hårdkodad admin
        if(hardcodedAdmin.getUserName().equals(userName) && hardcodedAdmin.getPassword().equals(password)){
            System.out.println("Admin login successful!"); //meddelande
            return hardcodedAdmin;
        }


       Admin admin = adminRepository.getAdminByUserName(userName);

        if (admin != null && admin.getPassword().equals(password)) { //om man försöker logga in utan hårdkodad
            System.out.println("Admin login successful!");
            return admin;
        }
            System.out.println("No admin found with that username");
            return null;

    }

}
