package Login;

import Admin.AdminRepository;
import Customer.*;

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

    public Customer loginAsCustomer(String email, String password) throws SQLException {

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

    public void loginAsAdmin(String name, String password){

    }

}
