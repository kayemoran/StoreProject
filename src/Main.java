import Login.LoginController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class Main {
    public static void main(String[] args) {

        LoginController loginController = new LoginController();
        loginController.run();

    }
}