package com.bimdog.testtask.controller;

import com.bimdog.testtask.com.bimdog.testtask.impl.PurchaseDatabaseDao;
import com.bimdog.testtask.dao.PurchaseDao;
import com.bimdog.testtask.fixer.MainFixerApi;
import com.bimdog.testtask.view.Console;
import com.bimdog.testtask.view.View;

public class Main {
    public static void main(String[] args) {
        View view = new Console();
        PurchaseDao purchaseDao = new PurchaseDatabaseDao();


        MainFixerApi mainFixerApi = new MainFixerApi();
        System.out.println(mainFixerApi.convert("USD", "USD", 25));


        MainController controller = new MainController(view, purchaseDao);
        controller.run();

    }
}
