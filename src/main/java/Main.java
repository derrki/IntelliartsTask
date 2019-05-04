import java.sql.*;

/**
 * 1. Make it work. 2. Make it right. 3. Make it fast
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        String HOST_MYSQL = "jdbc:mysql://localhost:3306/souvenir_shop"+
                "?verifyServerCertificate=false"+
                "&useSSL=false"+
                "&requireSSL=false"+
                "&useLegacyDatetimeCode=false"+
                "&amp"+
                "&serverTimezone=UTC";
        String USERNAME_MYSQL = "root";
        String PASSWORD_MYSQL = "root";
        Connection connection = null;

        String sql = "INSERT INTO test(testcol) VALUES(?);";
        connection = DriverManager.getConnection(HOST_MYSQL, USERNAME_MYSQL, PASSWORD_MYSQL);

        try (PreparedStatement pStatementTest = connection.prepareStatement(sql)){

            System.out.println("Приєднано до mysql");

            pStatementTest.setString(1, "testData");
            pStatementTest.execute();

        } catch (SQLException e) {
            System.err.println("Приєднання до бази не відбулось, по причині - " + e);
        }finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
