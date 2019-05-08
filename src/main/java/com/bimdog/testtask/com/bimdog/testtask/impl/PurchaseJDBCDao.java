package com.bimdog.testtask.com.bimdog.testtask.impl;

import com.bimdog.testtask.dao.ConnectionFactory;
import com.bimdog.testtask.dao.DAOException;
import com.bimdog.testtask.dao.PurchaseDao;
import com.bimdog.testtask.model.Purchase;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class PurchaseJDBCDao implements PurchaseDao {

    private ConnectionFactory conFactory = ConnectionFactory.getInstance();

    public PurchaseJDBCDao(){}

    @Override
    public boolean add(Purchase purchase) throws DAOException {
        boolean result = false;

        try(Connection connection = conFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQLPurchase.INSERT.QUERY)){

            statement.setDate(1, new Date(purchase.getDateOfPurchase().getTime()));
            statement.setString(2, purchase.getNameSouvenir());
            statement.setInt(3, purchase.getPrice());
            statement.setString(4, purchase.getCurrency());
            result = statement.execute();

        } catch (SQLException e) {
            throw new DAOException("Can not delete purchase", e);
        }
        return result;
    }

    @Override
    public List<Purchase> getAll() throws DAOException {
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
            throw new DAOException("Can not delete purchase", e);
        }
        return allPurchase;
    }

    @Override
    public List<Purchase> getByDate(String date) throws DAOException {
        List<Purchase> purchaseOfDate = new ArrayList<>();
        Purchase purchase = null;
        ResultSet resultSet = null;
        String startDate = date + "-01-01";
        String endDate = date +"-12-31";
        try(Connection connection = conFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQLPurchase.SELECT_DATE.QUERY)) {
            statement.setString(1, startDate);
            statement.setString(2, endDate);
            resultSet = statement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    purchase = new Purchase();
                    purchase.setDateOfPurchase(resultSet.getDate("date").toString());
                    purchase.setNameSouvenir(resultSet.getString("name_souvenir"));
                    purchase.setPrice(resultSet.getInt("price"));
                    purchase.setCurrency(resultSet.getString("currency"));
                    purchaseOfDate.add(purchase);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Can not delete purchase", e);
        }
        return purchaseOfDate;
    }

    @Override
    public void delete(String date) throws DAOException {
        try(Connection connection = conFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQLPurchase.DELETE.QUERY)){
            statement.setString(1, date);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Can not delete purchase", e);
        }
    }

    enum SQLPurchase {

        INSERT("INSERT INTO purchases(date, name_souvenir, price, currency) VALUES(?, ?, ? , ?);"),
        SELECT("select * from purchases ORDER BY date"),
        SELECT_DATE("select * from purchases WHERE DATE BETWEEN ? AND ?;"),
        DELETE("delete from purchases Where date=?");

        String QUERY;

        SQLPurchase(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
