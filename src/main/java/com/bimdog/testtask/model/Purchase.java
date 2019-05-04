package com.bimdog.testtask.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Purchase extends Model{

    private long dateOfPurchase;
    private String nameSouvenir;
    private int price;
    private String currency;

    public Purchase (){}

    public long getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(String dateOfPurchase) {
        long oneDay = 86400000;
        Date  date = null;
        try {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-M-dd");
        date = simpleDateFormat.parse(dateOfPurchase);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.dateOfPurchase = date.getTime() +oneDay;
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
    public int hashCode() {
        int result = (int) (dateOfPurchase ^ (dateOfPurchase >>> 32));
        result = 31 * result + nameSouvenir.hashCode();
        result = 31 * result + price;
        result = 31 * result + currency.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "nameSouvenir='" + nameSouvenir + '\'' +
                ", dateOfPurchase=" + dateOfPurchase +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                '}';
    }
}
