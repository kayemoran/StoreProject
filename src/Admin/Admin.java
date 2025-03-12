package Admin;

import Super.User;

/**
 * Admin class represents as admin user in the system.
 * Extends the User class.
 * Has a constructor for creating admin users.
 */
public class Admin extends User {

    /**
     * Constructor to create an Admin object.
     * @param id unique identifier for admin
     * @param userName username of admin
     * @param password password of admin
     */
    public Admin(int id, String userName, String password) {
        super(id, userName, password);
    }

    public Admin() {
        super (0, "", "");
    }
}
