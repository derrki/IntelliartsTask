package com.bimdog.testtask.model;

import java.util.GregorianCalendar;

public class Purchase extends Model{

    private Souvenir souvenir;
    private GregorianCalendar date;
    private String currency;

    public Purchase (){}

    public Souvenir getSouvenir() {
        return souvenir;
    }

    public void setSouvenir(Souvenir souvenir) {
        this.souvenir = souvenir;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
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
                "id=" + super.getId() +
                ", souvenir=" + souvenir +
                ", date=" + date +
                ", currency='" + currency + '\'' +
                '}';
    }
}
