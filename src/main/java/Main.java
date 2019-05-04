import com.bimdog.testtask.com.bimdog.testtask.impl.PurchaseDatabaseDao;
import com.bimdog.testtask.dao.ConnectionFactory;
import com.bimdog.testtask.model.Purchase;

import java.sql.*;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws SQLException, ParseException {

        ConnectionFactory conFactory = ConnectionFactory.getInstance();

        PurchaseDatabaseDao purchaseDao = new PurchaseDatabaseDao();

        Purchase purchase = new Purchase();
        purchase.setDateOfPurchase("1990-01-01");
        purchase.setNameSouvenir("Bool");
        purchase.setPrice(1000);
        purchase.setCurrency("GBP");

        purchaseDao.add(purchase);

        try (Connection connection = conFactory.getConnection();
                Statement statement = connection.createStatement();
                ){

            System.out.println("Приєднано до mysql");
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


}
