package com.bimdog.testtask.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class Purchase extends Model{

    private Date dateOfPurchase;
    private String nameSouvenir;
    private int price;
    private String currency;

    public Purchase (){}

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(String dateOfPurchase) {
        long oneDay = 86400000;
        java.util.Date date = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-M-dd");
            date = simpleDateFormat.parse(dateOfPurchase);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.dateOfPurchase = new Date(date.getTime());
    }

    public String getNameSouvenir() {
        return nameSouvenir;
    }

    public void setNameSouvenir(String nameSouvenir) {
        this.nameSouvenir = nameSouvenir;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Purchase purchase = (Purchase) o;

        if (dateOfPurchase != purchase.dateOfPurchase) return false;
        if (price != purchase.price) return false;
        if (!nameSouvenir.equals(purchase.nameSouvenir)) return false;
        return currency.equals(purchase.currency);

    }



    @Override
    public String toString() {
        return dateOfPurchase +" " + nameSouvenir + " " + " " + price + " " + currency;
    }
}
