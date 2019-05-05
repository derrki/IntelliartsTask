package com.bimdog.testtask.controller;

import com.bimdog.testtask.dao.PurchaseDao;
import com.bimdog.testtask.model.Purchase;
import com.bimdog.testtask.view.View;

import java.util.List;

public class MainController {

    private View view;
    private PurchaseDao purchaseDao;

    public MainController(View view, PurchaseDao purchaseDao) {
        this.view = view;
        this.purchaseDao = purchaseDao;
    }

    public void run() {

        view.write("Привіт користувач. Введи команду для початку роботи або help для одержання списку команд");

        while (true) {
            String comandLine = view.read();

            if (comandLine.contains("\"")) {
                int cod = comandLine.indexOf("\"");
                String midleLine = comandLine.subSequence(cod, comandLine.length()).toString();
                String midleLineCorrect = midleLine.replace("\"", "");
                midleLineCorrect = midleLineCorrect.replace(" ", "_");
                comandLine = comandLine.replace(midleLine, midleLineCorrect);

            }

            if (comandLine.contains("purchase")) {
                addPurchase(comandLine);
            } else if (comandLine.equals("all")) {
                showAllPurchase();
            } else if (comandLine.contains("clear")) {
                clearPurchase(comandLine);
            } else if (comandLine.equals("help")) {
                doHelp();
            } else if (comandLine.equals("exit")) {
                view.write("Сеанс роботи з програмою завершено. До зустрічі");
                System.exit(0);
            } else {
                view.write("Не існуюча команда: " + comandLine);
            }
        }
    }

    private void clearPurchase(String comandLine) {

        String[] itemComand = comandLine.split(" ");
        String comand = itemComand[0];
        String datePurchase = itemComand[1];

        purchaseDao.delete(datePurchase);
    }

    private void doHelp() {
        view.write("Існуючі команди:");
        view.write("\tpurchase 2019-04-25 2 USD T-shirt");
        view.write("\t\tдля введення даних про покупки в форматі: команда дата ціна валюта назва товару");
        view.write("\tall");
        view.write("\t\tдля відображення всіх покупок");
        view.write("\thelp");
        view.write("\t\tдля відображення всіх існуючих команд");
    }

    private void showAllPurchase() {
        List<Purchase> allPurchase = purchaseDao.getAll();

        for (Purchase p : allPurchase) {
            System.out.println(p);
        }
    }

    private void addPurchase(String comandLine) {
        String[] itemComand = comandLine.split(" ");
        String comand = itemComand[0];
        String datePurchase = itemComand[1];
        int price = Integer.parseInt(itemComand[2]);
        String currency = itemComand[3];
        String name = itemComand[4];
        if(name.contains("_")){
            name = name.replace("_", " ");
        }

        Purchase purchase = new Purchase();
        purchase.setDateOfPurchase(datePurchase);
        purchase.setNameSouvenir(name);
        purchase.setPrice(price);
        purchase.setCurrency(currency);

        purchaseDao.add(purchase);
    }

}
