package com.bimdog.testtask.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class Purchase extends Model {

    private Date dateOfPurchase;
    private int price;
    private String currency;
    private String nameSouvenir;

    public Purchase(String dateOfPurchase, int price, String currency, String nameSouvenir) {

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-M-dd");
            this.dateOfPurchase = new Date(simpleDateFormat.parse(dateOfPurchase).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.nameSouvenir = nameSouvenir;
        this.price = price;
        this.currency = currency;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public String getNameSouvenir() {
        return nameSouvenir;
    }

    public int getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
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
        return nameSouvenir + " " + price + " " + currency;
    }
}
