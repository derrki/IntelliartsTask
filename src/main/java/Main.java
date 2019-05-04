import com.bimdog.testtask.dao.ConnectionFactory;

import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 1. Make it work. 2. Make it right. 3. Make it fast
 */
public class Main {

    public static final long oneDay = 86400000;

    public static void main(String[] args) throws SQLException, ParseException {

        ConnectionFactory conFactory = ConnectionFactory.getInstance();

        String sql = "INSERT INTO purchases(date, name_souvenir, price, currency) VALUES(?, ?, ? , ?);";

        try (Connection connection = conFactory.getConnection();
                Statement statement = connection.createStatement();
                PreparedStatement pStatementTest = connection.prepareStatement(sql)){

            System.out.println("Приєднано до mysql");

            pStatementTest.setDate(1, new Date(parseDate("2007-10-10")));
            pStatementTest.setString(2, "pen");
            pStatementTest.setInt(3, 100);
            pStatementTest.setString(4, "UAH");
            pStatementTest.execute();

            ResultSet resultSet = statement.executeQuery("select * from purchases WHERE date='1985-01-01'");
            while (resultSet.next()){
                System.out.print(resultSet.getDate("date") + " ");
                System.out.print(resultSet.getString("name_souvenir") + " ");
                System.out.print(resultSet.getInt("price") + " ");
                System.out.print(resultSet.getString("currency"));
                System.out.println();
            }
        } catch (SQLException e) {
            System.err.println("Приєднання до бази не відбулось, по причині - " + e);
        }

    }

    public static long parseDate(String stringFormat) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-M-dd");
        java.util.Date date = simpleDateFormat.parse(stringFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.getTimeInMillis() + oneDay;
    }
}
