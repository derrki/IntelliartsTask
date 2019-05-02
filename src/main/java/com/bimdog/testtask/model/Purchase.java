package com.bimdog.testtask.model;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Purchase implements Serializable{

    private Long id;
    private Souvenir souvenir;
    private GregorianCalendar date;
    private String currency;

    public Purchase (){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Souvenir getSouvenir() {
        return souvenir;
    }

    public void setSouvenir(Souvenir souvenir) {
        this.souvenir = souvenir;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", souvenir=" + souvenir +
                ", date=" + date +
                ", currency='" + currency + '\'' +
                '}';
    }
}
