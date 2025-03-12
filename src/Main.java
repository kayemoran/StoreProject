import Login.LoginController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

/**
 * The main class for the application.
 * Initiates login process.
 * Creates an instance of the LoginController and starts the login flow.
 */
public class Main {

    public static void main(String[] args) {

        LoginController loginController = new LoginController();
        loginController.run();

    }
}