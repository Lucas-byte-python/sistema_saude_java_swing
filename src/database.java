import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {
    private static final String URL = "jdbc:postgresql://localhost:5432/plano_saude";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "lucas2007";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
