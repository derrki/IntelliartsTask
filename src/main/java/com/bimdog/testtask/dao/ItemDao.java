package com.bimdog.testtask.dao;

import com.bimdog.testtask.model.Model;

import java.util.List;

public interface ItemDao <T extends Model>{

    boolean add(T model) throws DAOException;

    List<T> getAll() throws DAOException;

    List<T> getByDate(String date) throws DAOException;

    void delete(String date) throws DAOException;

}
