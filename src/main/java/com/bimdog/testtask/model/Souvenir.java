package com.bimdog.testtask.model;

public class Souvenir extends Model{

    private String name;
    private int price;

    public Souvenir (){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Souvenir{" +
                "id=" + super.getId() +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
