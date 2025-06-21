import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtility {
    private static final String URL = "jdbc:postgresql://localhost:5434/football_management";
    private static final String USER = "postgres";
    private static final String PASSWORD = "leandre";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
