package pl.piotrgrochowiecki;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

    public static final String SERVER_URL = "jdbc:mysql://localhost:3306";
    public static final String USER = "root";
    public static final String PASSWORD = "coderslab";

    public static Connection connect() throws SQLException {
        Connection connection = DriverManager.getConnection(SERVER_URL, USER, PASSWORD);
        return connection;
    }
}
