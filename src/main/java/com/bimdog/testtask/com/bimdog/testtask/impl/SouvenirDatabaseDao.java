package com.bimdog.testtask.com.bimdog.testtask.impl;

import com.bimdog.testtask.dao.SouvenirDao;
import com.bimdog.testtask.model.Souvenir;

import java.util.List;

public class SouvenirDatabaseDao implements SouvenirDao {

    public SouvenirDatabaseDao(){}

    @Override
    public List<Souvenir> getAll() {
        return null;
    }

    @Override
    public Souvenir getById(Long id) {
        return null;
    }

    @Override
    public void add(Souvenir model) {

    }

    @Override
    public void delete(Souvenir model) {

    }
}
