package com.example.demo;

import javafx.scene.control.Button;
import javafx.scene.image.Image;

import java.time.LocalDate;

public class Dealers {

    private String dealerID;
    private String dealerName;
    private String contactNo;
    private String location;
    private String itemName;
    private String itemBrand;
    private double price;
    private int quantity;

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

    public Dealers(String dealerID, String dealerName, String contactNo, String location) {
        this.dealerID = dealerID;
        this.dealerName = dealerName;
        this.contactNo = contactNo;
        this.location = location;

    }           //Use of polymorphism
    public Dealers(String itemName, String itemBrand, double price, int quantity) {
        this.itemName = itemName;
        this.itemBrand = itemBrand;
        this.price = price;
        this.quantity = quantity;
    }

    public String getDealerID() {
        return dealerID;
    }

    public void setDealerID(String dealerID) {
        this.dealerID = dealerID;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}


