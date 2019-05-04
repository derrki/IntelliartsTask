package com.bimdog.testtask.model;

import java.util.GregorianCalendar;

public class Purchase extends Model{


    private String nameSouvenir;
    private GregorianCalendar dateOfPurchase;
    private int price;
    private String currency;

    public Purchase (){}

    public String getNameSouvenir() {
        return nameSouvenir;
    }

    public void setNameSouvenir(String nameSouvenir) {
        this.nameSouvenir = nameSouvenir;
    }

    public GregorianCalendar getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(GregorianCalendar dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
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
    public String toString() {
        return "Purchase{" +
                "nameSouvenir='" + nameSouvenir + '\'' +
                ", dateOfPurchase=" + dateOfPurchase +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Purchase purchase = (Purchase) o;

        if (price != purchase.price) return false;
        if (!nameSouvenir.equals(purchase.nameSouvenir)) return false;
        if (!dateOfPurchase.equals(purchase.dateOfPurchase)) return false;
        return currency.equals(purchase.currency);

    }

    @Override
    public int hashCode() {
        int result = nameSouvenir.hashCode();
        result = 31 * result + dateOfPurchase.hashCode();
        result = 31 * result + price;
        result = 31 * result + currency.hashCode();
        return result;
    }
}
