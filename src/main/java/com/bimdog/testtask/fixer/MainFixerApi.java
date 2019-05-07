package com.bimdog.testtask.fixer;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainFixerApi {

    private static final Gson GSON = new Gson();

    public static void main(String[] args) {

        String query = "http://data.fixer.io/api/latest?access_key=4ddf9e165aa94a9f00ef76b6fdf34612&base=EUR";

        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) new URL(query).openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setConnectTimeout(250);
            connection.setReadTimeout(250);

            connection.connect();

            StringBuilder stringBuilder = new StringBuilder();

            if(HttpURLConnection.HTTP_OK == connection.getResponseCode()){
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;
                while ((line = in.readLine()) != null){
                    stringBuilder.append(line);
                    stringBuilder.append("\n");
                }
                System.out.println(stringBuilder.toString());
                //jsonParameter(stringBuilder);
                System.out.println(getCurrentRate("UAH", jsonParameter(stringBuilder).getCurrencyList()));
            } else {
                System.out.println("fail: " + connection.getResponseCode() + " " + connection.getResponseMessage());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null)
                connection.disconnect();
        }
    }

    public static CurrencyExchange jsonParameter(StringBuilder stringBuilder){
        CurrencyExchange currencyExchange = GSON.fromJson(stringBuilder.toString(), CurrencyExchange.class);
        System.out.println(currencyExchange.getCurrencyList());
        return currencyExchange;
    }

    public static double getCurrentRate(String name, List<Currency> list){
        double d = 0.0;
        for (Currency currency: list){
            if(currency.getName().equals(name)){
                d = currency.getRate();
            }
        }
        return d;
    }

}
