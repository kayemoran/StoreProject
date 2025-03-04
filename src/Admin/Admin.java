package Admin;

import Super.User;

public class Admin extends User {

    public Admin(int id, String userName, String password) {
        super(id, userName, password);
    }

    public Admin() {
        super (0, "", "");
    }
}
