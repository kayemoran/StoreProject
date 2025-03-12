package Customer;

import Super.User; //ärver user

/**
 * The class represents a customer in the system.
 */
public class Customer extends User {

    private String name;
    private String email;
    private String phone;
    private String address;

    /**
     * Creates a new customer with the specified details.
     * @param id customer's ID
     * @param name customer's name
     * @param userName customer's username
     * @param phone customer's phone number
     * @param address customer's address
     * @param password customer's password
     */
    public Customer(int id, String name, String userName, String phone, String address, String password) {
        super(id, userName, password);
        this.name = name;
        this.email = email; //är denna korrekt?
        this.phone = phone;
        this.address = address;

    }

    /**
     * Gets the customer's name.
     * @return the customer's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the customer's name.
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the customer's email.
     * @return the customer's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the customer's email.
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the customer's phone number.
     * @return the customer's phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the customer's phone number.
     *
     * @param phone the new phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }


    /**
     * Gets the customer's address.
     * @return the customer's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the customer's address.
     * @param adress the new address
     */
    public void setAddress(String adress) {
        this.address = adress;
    }


    /**
     * A toString method that returns a string representation of the customer.
     * @return customer details.
     */
    @Override
    public String toString() {
        return "Customer.Customer{" +
                "customerId=" + getId() +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", password='" + getPassword() + '\'' +
                '}';
    }
}