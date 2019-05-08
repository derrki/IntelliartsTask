package com.bimdog.testtask.dao;

import com.bimdog.testtask.model.Model;

import java.util.List;

public interface ItemDao <T extends Model>{

    boolean add(T model);

    List<T> getAll();

    List<T> getByDate(String date);

    void delete(String date) throws DAOException;

}
