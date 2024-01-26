package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import java.util.Map;

public class DealerItemsController {
    //============================== Items Table ============================//
    @FXML
    private TableView<Dealers> ItemDetailID;

    @FXML
    private TableColumn<Dealers, String> itemBrandID;

    @FXML
    private TableColumn<Dealers, String> itemNameID;

    @FXML
    private TableColumn<Dealers, Double> itemPriceID;

    @FXML
    private TableColumn<Dealers, Integer> itemQuantityID;

    public static ObservableList<Dealers> dealerItemList;

// ========================================================================= //
    public void initialize() {

    itemNameID.setCellValueFactory(new PropertyValueFactory<>("itemName"));
    itemBrandID.setCellValueFactory(new PropertyValueFactory<>("itemBrand"));
    itemPriceID.setCellValueFactory(new PropertyValueFactory<>("price"));
    itemQuantityID.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }
    public void displayItems(String selectedID){
        FileHandling fileHandling = new FileHandling();
        ReadfileResult result = fileHandling.DealerItems(selectedID);
        Map<String, List<Object>> hashmap = result.getHashmap();
        int NoOfLines = result.getNoOfLines();
        dealerItemList = FXCollections.observableArrayList(); // Initialize the observable list
        for (int i = 0; i < NoOfLines; i++) {
            String itemName = (String) hashmap.get("col1").get(i);
            String itemBrand = (String) hashmap.get("col2").get(i);
            double price = (double) hashmap.get("col3").get(i);
            int quantity = (Integer) hashmap.get("col4").get(i);

            Dealers dealerItems = new Dealers(itemName, itemBrand, price, quantity);
            dealerItemList.add(dealerItems);
        }
        ItemDetailID.setItems(dealerItemList);
        }


}
