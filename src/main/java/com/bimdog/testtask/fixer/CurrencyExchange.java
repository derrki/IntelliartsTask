package com.bimdog.testtask.fixer;

import java.util.ArrayList;
import java.util.List;

public class CurrencyExchange {
    private boolean success;
    private float timestamp;
    private String base;
    private String date;
    Rates rates;

    public List<Currency> getCurrencyList(){
        List<Currency> currencyList = new ArrayList<Currency>();
        currencyList.add(new Currency("EUR", rates.getEUR()));
        currencyList.add(new Currency("PLN", rates.getPLN()));
        currencyList.add(new Currency("UAH", rates.getUAH()));
        currencyList.add(new Currency("USD", rates.getUSD()));

        return currencyList;
    }

    public boolean getSuccess() {
        return success;
    }

    public float getTimestamp() {
        return timestamp;
    }

    public String getBase() {
        return base;
    }

    public String getDate() {
        return date;
    }

    public Rates getRates() {
        return rates;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setTimestamp(float timestamp) {
        this.timestamp = timestamp;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setRates(Rates ratesObject) {
        this.rates = rates;
    }
}
class Rates {

    private float EUR;
    private float PLN;
    private float UAH;
    private float USD;

    public float getEUR() {
        return EUR;
    }

    public float getPLN() {
        return PLN;
    }

    public float getUAH() {
        return UAH;
    }

    public float getUSD() {
        return USD;
    }

    public void setEUR(float EUR) {
        this.EUR = EUR;
    }

    public void setPLN(float PLN) {
        this.PLN = PLN;
    }

    public void setUAH(float UAH) {
        this.UAH = UAH;
    }

    public void setUSD(float USD) {
        this.USD = USD;
    }
}
