package com.bimdog.testtask.controller;

import com.bimdog.testtask.com.bimdog.testtask.impl.PurchaseDatabaseDao;
import com.bimdog.testtask.dao.PurchaseDao;
import com.bimdog.testtask.model.Purchase;
import com.bimdog.testtask.view.Console;
import com.bimdog.testtask.view.View;

public class MainController {

    private View view;
    private PurchaseDao purchaseDao;

    public  MainController(View view, PurchaseDao purchaseDao){
       this.view = view;
       this.purchaseDao = purchaseDao;
    }

    public void run(){
        addPurchase();
    }

   private void addPurchase() {
        view.write("Привіт користувач. Введи команду для початку роботи");
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
