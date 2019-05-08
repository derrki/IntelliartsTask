package com.bimdog.testtask.controller;

import com.bimdog.testtask.com.bimdog.testtask.impl.PurchaseJDBCDao;
import com.bimdog.testtask.dao.PurchaseDao;
import com.bimdog.testtask.view.Console;
import com.bimdog.testtask.view.View;

public class Main {
    public static void main(String[] args) {
        View view = new Console();
        PurchaseDao purchaseDao = new PurchaseJDBCDao();

        Controller controller = new Controller(view, purchaseDao);
        controller.run();

    }
}
