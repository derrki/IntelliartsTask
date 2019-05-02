package com.bimdog.testtask.model;

import java.io.Serializable;

public class Model implements Serializable{

    private Long id;

    public Model(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
