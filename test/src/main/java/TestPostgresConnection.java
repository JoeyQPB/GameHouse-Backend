import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestPostgresConnection {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://172.17.0.2:5432/";
        String user = "admin";
        String password = "admin123";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            if (connection != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.out.println("msg:" + e.getMessage());
            System.out.println("cause:" + e.getCause());
            System.out.println(e);
        }
    }
}
