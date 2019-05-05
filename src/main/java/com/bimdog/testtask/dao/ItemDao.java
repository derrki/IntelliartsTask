package com.bimdog.testtask.dao;

import com.bimdog.testtask.model.Model;

import java.util.List;

public interface ItemDao <T extends Model>{

    public List<T> getAll();

    public List<T> getByDate(String date);

    public boolean add(T model);

    public void delete(T model);

}
