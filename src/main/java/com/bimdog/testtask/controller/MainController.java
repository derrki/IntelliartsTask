package com.bimdog.testtask.controller;

import com.bimdog.testtask.dao.PurchaseDao;
import com.bimdog.testtask.model.Purchase;
import com.bimdog.testtask.view.View;

import java.util.List;

public class MainController {

    private View view;
    private PurchaseDao purchaseDao;

    public  MainController(View view, PurchaseDao purchaseDao){
       this.view = view;
       this.purchaseDao = purchaseDao;
    }

    public void run(){

        view.write("Привіт користувач. Введи команду для початку роботи або help для одержання списку команд");
        //addPurchase();

        while (true) {
            String comandLine = view.reag();

            if (comandLine.equals("all")) {
                showAllPurchase();
            } else if (comandLine.equals("help")) {
                doHelp();
            } else {
                view.write("Не існуюча команда: " + comandLine);
            }
        }
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
        List<Purchase> allPurchase= purchaseDao.getAll();

        for (Purchase p : allPurchase) {
            System.out.println(p);
        }
    }

    private void addPurchase() {

        String comandLine = view.reag();
        String[] itemComand = comandLine.split(" ");
        String comand = itemComand[0];
        String datePurchase = itemComand[1];
        int price = Integer.parseInt(itemComand[2]);
        String currency = itemComand[3];
        String name = itemComand[4];

        Purchase purchase = new Purchase();
        purchase.setDateOfPurchase(datePurchase);
        purchase.setNameSouvenir(name);
        purchase.setPrice(price);
        purchase.setCurrency(currency);

        purchaseDao.add(purchase);
    }

}
