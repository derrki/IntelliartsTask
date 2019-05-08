package com.bimdog.testtask.controller;

import com.bimdog.testtask.dao.DAOException;
import com.bimdog.testtask.dao.PurchaseDao;
import com.bimdog.testtask.fixer.MainFixerApi;
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
                clearPurchase(comandLine);}
            else if (comandLine.contains("report")) {
                reportPurchase(comandLine);
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

    private void addPurchase(String comandLine) {

        String datePurchase = splitComandLine(comandLine)[1];
        int price = Integer.parseInt(splitComandLine(comandLine)[2]);
        String currency = splitComandLine(comandLine)[3];
        String name = splitComandLine(comandLine)[4];
        if(name.contains("_")){
            name = name.replace("_", " ");
        }

        Purchase purchase = new Purchase();
        purchase.setDateOfPurchase(datePurchase);
        purchase.setNameSouvenir(name);
        purchase.setPrice(price);
        purchase.setCurrency(currency);

        purchaseDao.add(purchase);

        showAllPurchase();
    }

    private void showAllPurchase() {
        List<Purchase> allPurchase = purchaseDao.getAll();

        for (Purchase p : allPurchase) {
            System.out.println(p.getDateOfPurchase());
            System.out.println(p);
        }
    }

    private void clearPurchase(String comandLine) {

        String datePurchase = splitComandLine(comandLine)[1];

        try {
            purchaseDao.delete(datePurchase);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        showAllPurchase();
    }

    private void reportPurchase(String comandLine) {

        MainFixerApi mainFixerApi = new MainFixerApi();
        String datePurchase = splitComandLine(comandLine)[1];
        String currency = splitComandLine(comandLine)[2];

        double result = 0.0;

        List<Purchase> allPurchase = purchaseDao.getByDate(datePurchase);
        for (Purchase p : allPurchase) {
            p.getCurrency();
            p.getPrice();
            result = result + mainFixerApi.convert(p.getCurrency(), currency, p.getPrice());
        }
        System.out.println(result + " " + currency);

    }

    private void doHelp() {
        view.write("Існуючі команди:");
        view.write("\tpurchase 2019-04-25 2 USD T-shirt");
        view.write("\t\tдля введення даних про покупки в форматі: команда дата ціна валюта назва товару");
        view.write("\tall");
        view.write("\t\tдля відображення всіх покупок");
        view.write("\tclear");
        view.write("\t\tдля видалення покупки з бази даних по даті");
        view.write("\treport");
        view.write("\t\tдля підрахунку загальної суми всіх покупок");
        view.write("\thelp");
        view.write("\t\tдля відображення всіх існуючих команд");
        view.write("\texit");
        view.write("\t\tдля виходу з програми");
    }

    String[] splitComandLine(String comandLine){
        return comandLine.split(" ");
    }

}
