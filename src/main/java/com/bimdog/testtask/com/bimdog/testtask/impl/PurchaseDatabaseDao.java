package com.bimdog.testtask.com.bimdog.testtask.impl;

import com.bimdog.testtask.dao.ConnectionFactory;
import com.bimdog.testtask.dao.PurchaseDao;
import com.bimdog.testtask.model.Purchase;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PurchaseDatabaseDao implements PurchaseDao {

    private ConnectionFactory conFactory = ConnectionFactory.getInstance();

    public PurchaseDatabaseDao(){}

    @Override
    public boolean add(Purchase purchase) {
        boolean result = false;

        try(Connection connection = conFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQLPurchase.INSERT.QUERY)){

            statement.setDate(1, new Date(purchase.getDateOfPurchase()));
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
    public void delete(Purchase model) {

    }

    @Override
    public List<Purchase> getAll() {
        return null;
    }

    @Override
    public Purchase getByDate(Long date) {
        return null;
    }

    enum SQLPurchase {

        INSERT("INSERT INTO purchases(date, name_souvenir, price, currency) VALUES(?, ?, ? , ?);");

        String QUERY;

        SQLPurchase(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
