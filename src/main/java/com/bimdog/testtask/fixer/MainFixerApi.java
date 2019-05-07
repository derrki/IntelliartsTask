package com.bimdog.testtask.fixer;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MainFixerApi {

    private static final Gson GSON = new Gson();

    private String jsonReader() {

        String query = "http://data.fixer.io/api/latest?access_key=4ddf9e165aa94a9f00ef76b6fdf34612&base=EUR";

        HttpURLConnection connection = null;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            connection = (HttpURLConnection) new URL(query).openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setConnectTimeout(250);
            connection.setReadTimeout(250);
            connection.connect();

            if(HttpURLConnection.HTTP_OK == connection.getResponseCode()){
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;
                while ((line = in.readLine()) != null){
                    stringBuilder.append(line);
                }
            } else {
                System.out.println("fail: " + connection.getResponseCode() + " " + connection.getResponseMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null)
                connection.disconnect();
        }
        String jsonResult = stringBuilder.toString();
        return jsonResult;
    }

    private CurrencyExchange jsonParameter(String stringJson){
        CurrencyExchange currencyExchange = GSON.fromJson(stringJson, CurrencyExchange.class);
        return currencyExchange;
    }

    public double getCurrentRate(String name, List<Currency> list){
        double d = 0.0;
        for (Currency currency: list){
            if(currency.getName().equals(name)){
                d = currency.getRate();
            }
        }
        return d;
    }

    public double convert(String from, String to, double amount){

        double result = 0.0;

       result = getCurrentRate(to, jsonParameter(jsonReader()).getCurrencyList());

        return result;
    }
}
