package com.example.demo;

import javafx.scene.control.Button;
import javafx.scene.image.Image;

import java.time.LocalDate;

public class Items {
    private String itemCode;
    private String itemName;
    private String itemBrand;
    private double price;
    private int quantity;
    private String category;
    private LocalDate date;
    private Button Action;

    public Items(String itemCode,String itemName,String itemBrand,double price,int quantity,String category,LocalDate date){
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemBrand = itemBrand;
        this.price = price;
        this.quantity =quantity;
        this.category = category;
        this.date = date;
        this.Action = Action;
    }



    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemBrand() {
        return itemBrand;
    }

    public void setItemBrand(String itemBrand) {
        this.itemBrand = itemBrand;
    }

    public double getPrice() {
        return price;
    }


    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    private Image image;


    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public void setAction(Button action) {
        this.Action = action;
    }

    public Button getAction() {
        return Action;
    }


}
