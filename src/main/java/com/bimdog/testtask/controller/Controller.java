package com.bimdog.testtask.controller;

import com.bimdog.testtask.dao.DAOException;
import com.bimdog.testtask.dao.PurchaseDao;
import com.bimdog.testtask.fixer.MainFixerApi;
import com.bimdog.testtask.model.Purchase;
import com.bimdog.testtask.view.View;
import java.util.List;

public class Controller {

    private View view;
    private PurchaseDao purchaseDao;

    public Controller(View view, PurchaseDao purchaseDao) {
        this.view = view;
        this.purchaseDao = purchaseDao;
    }

    public void run() {

        view.write("Привіт користувач. Введи команду для початку роботи або help для одержання списку команд");

        while (true) {
            String comandLineRead = view.read();
            String comandLine = formatComandLine(comandLineRead);

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
        double price = Double.parseDouble(splitComandLine(comandLine)[2]);
        String currency = splitComandLine(comandLine)[3];
        String name = splitComandLine(comandLine)[4];

        //видалення нижнього підкреслення
        if(name.contains("_")){
            name = name.replace("_", " ");
        }

        Purchase purchase = new Purchase(datePurchase, price, currency, name);

        try {
            purchaseDao.add(purchase);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        showAllPurchase();
    }

    private void showAllPurchase() {
        List<Purchase> allPurchase = null;

        try {
            allPurchase = purchaseDao.getAll();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        String printDate = null;
        String printDateNext = null;
        if (allPurchase != null) {
            for (Purchase p : allPurchase) {
                printDate = p.getDateOfPurchase().toString();

                if (!printDate.equals(printDateNext)){
                    System.out.println();
                    System.out.println(printDate);
                    System.out.println(p);

                } else {

                    System.out.println(p);
                }
                printDateNext = printDate;
            }
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

        List<Purchase> allPurchase = null;
        try {
            allPurchase = purchaseDao.getByDate(datePurchase);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        if(allPurchase != null) {
            for (Purchase p : allPurchase) {
                p.getCurrency();
                p.getPrice();
                result = result + mainFixerApi.convert(p.getCurrency(), currency, p.getPrice());
                result = Math.round(result * 100.0) / 100.0;
            }
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

   private String[] splitComandLine(String comandLine){
        return comandLine.split(" ");
    }

    //перевіряє чи введена команда має скобки. Вирізає частину команди, що внесена в скобки, видаляє скобки і вставляє назад.
    //якщо в скобках міститься пробіл то він заміняється на нижне підкреслення, щоб назва товару записалась в одну комірку масиву
   private String formatComandLine(String comandLineRead){

        if (comandLineRead.contains("\"")) {
            int elementPosition = comandLineRead.indexOf("\"");
            String cutLine = comandLineRead.subSequence(elementPosition, comandLineRead.length()).toString();
            String cutLineCorrect = cutLine.replace("\"", "");
            cutLineCorrect = cutLineCorrect.replace(" ", "_");
            return comandLineRead.replace(cutLine, cutLineCorrect);
        } else {
            return comandLineRead;
        }
    }
}
