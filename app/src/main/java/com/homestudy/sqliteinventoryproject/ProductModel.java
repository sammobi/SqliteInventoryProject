package com.homestudy.sqliteinventoryproject;

import java.lang.String; /**
 * Created by Simpalm on 2/16/16.
 */
public class ProductModel {

    public String name, price, quantity, code, description;

    public ProductModel(String name, String quantity, String price, String code, String description) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.code = code;
        this.description = description;
    }
}
