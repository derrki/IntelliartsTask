package com.bimdog.testtask.com.bimdog.testtask.impl;

import com.bimdog.testtask.dao.PurchaseDao;
import com.bimdog.testtask.model.Purchase;

import java.util.List;

public class PurchaseDatabaseDao implements PurchaseDao {

    public PurchaseDatabaseDao(){}

    @Override
    public List<Purchase> getAll() {
        return null;
    }

    @Override
    public Purchase getById(Long id) {
        return null;
    }

    @Override
    public void add(Purchase model) {

    }

    @Override
    public void delete(Purchase model) {

    }
}
