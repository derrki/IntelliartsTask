import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 1. Make it work. 2. Make it right. 3. Make it fast
 */
public class Main {
    public static void main(String[] args) {
        String HOST_MYSQL = "jdbc:mysql://localhost:3306/souvenir_shop"+
                "?verifyServerCertificate=false"+
                "&useSSL=false"+
                "&requireSSL=false"+
                "&useLegacyDatetimeCode=false"+
                "&amp"+
                "&serverTimezone=UTC";
        String USERNAME_MYSQL = "root";
        String PASSWORD_MYSQL = "root";

        try {
            Connection connection = DriverManager.getConnection(HOST_MYSQL, USERNAME_MYSQL, PASSWORD_MYSQL);
            System.out.println("Приєднано до mysql");
        } catch (SQLException e) {
            System.err.println("Приєднання до бази не відбулось, по причині - " + e);
        }
    }
}
