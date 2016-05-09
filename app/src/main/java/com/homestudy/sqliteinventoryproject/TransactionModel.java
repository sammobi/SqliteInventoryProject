package com.homestudy.sqliteinventoryproject;

/**
 * Created by Simpalm on 2/16/16.
 */
public class TransactionModel {

    public String name, price, quantity, totalPrice;

    public TransactionModel(String name, String quantity, String price, String totalPrice) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
    }
}
