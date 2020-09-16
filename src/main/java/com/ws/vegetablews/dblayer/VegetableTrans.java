package com.ws.vegetablews.dblayer;

public class VegetableTrans {

    private String vegName;
    private int price;
    private float quantity;
    private float subTotal;

    public VegetableTrans(String vegName, int price, float quantity, float subTotal){
        this.vegName = vegName;
        this.price = price;
        this.quantity = quantity;
        this.subTotal = subTotal;
    }

    public String getVegName() {
        return vegName;
    }

    public void setVegName(String vegName) {
        this.vegName = vegName;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "VegetableTrans{" +
                "vegName='" + vegName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", subTotal=" + subTotal +
                '}';
    }
}
