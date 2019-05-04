package com.bimdog.testtask.com.bimdog.testtask.impl;

import com.bimdog.testtask.dao.ConnectionFactory;
import com.bimdog.testtask.dao.PurchaseDao;
import com.bimdog.testtask.model.Purchase;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class PurchaseDatabaseDao implements PurchaseDao {

    private ConnectionFactory conFactory = ConnectionFactory.getInstance();

    public PurchaseDatabaseDao(){}

    @Override
    public boolean add(Purchase purchase) {
        boolean result = false;

        try(Connection connection = conFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQLPurchase.INSERT.QUERY)){

            statement.setDate(1, new Date(purchase.getDateOfPurchase().getTime()));
            statement.setString(2, purchase.getNameSouvenir());
            statement.setInt(3, purchase.getPrice());
            statement.setString(4, purchase.getCurrency());
            result = statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    @Override
    public List<Purchase> getAll() {
        List<Purchase> allPurchase = new ArrayList<>();
        Purchase purchase = null;
        try(Connection connection = conFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQLPurchase.SELECT.QUERY)) {
            if (resultSet != null) {
                while (resultSet.next()) {
                    purchase = new Purchase();
                    purchase.setDateOfPurchase(resultSet.getDate("date").toString());
                    purchase.setNameSouvenir(resultSet.getString("name_souvenir"));
                    purchase.setPrice(resultSet.getInt("price"));
                    purchase.setCurrency(resultSet.getString("currency"));
                    allPurchase.add(purchase);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allPurchase;
    }

    @Override
    public void delete(Purchase model) {

    }

    @Override
    public Purchase getByDate(Long date) {
        return null;
    }

    enum SQLPurchase {

        INSERT("INSERT INTO purchases(date, name_souvenir, price, currency) VALUES(?, ?, ? , ?);"),
        SELECT("select * from purchases");

        String QUERY;

        SQLPurchase(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
