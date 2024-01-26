package com.example.demo;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemsController extends FileHandling{ // Use of Inheritance

    @FXML
    private TableColumn<Items, String> ItemBrandID;

    @FXML
    private TableColumn<Items, String> ItemCategoryID;

    @FXML
    private TableColumn<Items, LocalDate> ItemDateID;

    @FXML
    private TableColumn<Items, String> ItemID;

    @FXML
    private TableColumn<Items, Button> ItemImageID;

    @FXML
    private TableColumn<Items, String> ItemNameID;

    @FXML
    private TableColumn<Items, Double> ItemPriceID;

    @FXML
    private TableColumn<Items, Integer> ItemQuantityID;

    @FXML
    private TableColumn<Items,Button> ViewImageID;

    @FXML
    public TableView<Items> itemsTable; // Used in addItemsController


    @FXML
    private ImageView updateID;


    // Create a field for the list
    public static ObservableList<Items> list; // Used in addItemsController

    public static ObservableList<Items> getList() {
        return list;
    } // Used in addItemsController

    public static Items clickItem; // Used in addItemsController

    public static Items getClickedItem() {
        return clickItem;
    }

    @FXML
    public void getAddView(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddItems.fxml"));
            Parent parent = loader.load();
            AddItemsController addItemsController = loader.getController();
            addItemsController.setButtonVisibility(true, false); // Pass the visibility values as needed
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ItemsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    public void DeleteClick(MouseEvent event) {
        int selectedID = itemsTable.getSelectionModel().getSelectedIndex();
        itemsTable.getItems().remove(selectedID);

    }


    @FXML
    public void UpdateClick(MouseEvent event) {
        clickItem = itemsTable.getSelectionModel().getSelectedItem();
        //String name = clickedItem.getItemName();
        //System.out.println(name);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddItems.fxml"));
            Parent parent = loader.load();
            AddItemsController addItemsController = loader.getController();
            addItemsController.setButtonVisibility(false, true); // Pass the visibility values as needed
            addItemsController.setTexts(clickItem);
            addItemsController.clickItem(clickItem);
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ItemsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    @FXML
    public void refreshTable(MouseEvent event) {
        sort sorter = new sort();
        sort.Bubblesort bubblesort = sorter.new Bubblesort();
        bubblesort.bubbleSort(ItemsController.list);
        Platform.runLater(() -> itemsTable.refresh());
    }

    @FXML
    public void saveClick(MouseEvent event) throws IOException {
        FileHandling fileHandling = new FileHandling();
        fileHandling.SavingItems();
    }

    public void GetExistingInfo() {
        ReadfileResult result = readItemDetails();
        Map<String, List<Object>> hashmap = result.getHashmap();
        int NoOfLines = result.getNoOfLines();

        list = FXCollections.observableArrayList(); // Initialize the list
        for (int i = 0; i < NoOfLines; i++) {
            String itemCode = (String) hashmap.get("col1").get(i);
            String itemName = (String) hashmap.get("col2").get(i);
            String itemBrand = (String) hashmap.get("col3").get(i);
            double price = (double) hashmap.get("col4").get(i);
            int quantity = (int) hashmap.get("col5").get(i);
            String category = (String) hashmap.get("col6").get(i);
            LocalDate date = (LocalDate) hashmap.get("col7").get(i);

            Items item = new Items(itemCode, itemName, itemBrand, price, quantity, category, date);
            list.add(item);
        }
        sort sorter = new sort();
        sort.Bubblesort bubblesort = sorter.new Bubblesort();
        bubblesort.bubbleSort(list);
    }


    public void initialize() {
        // Initialize your TableColumn bindings and other UI setup code here
        // Call GetExistingInfo() to populate the list
        GetExistingInfo();
        String buttonStyle = "-fx-background-color: rgba(40,105,148,0.75); " +
                "-fx-text-fill: white; " +
                "-fx-padding: 8px 16px; " +
                "-fx-font-size: 14px; " +
                "-fx-cursor: hand; " +
                "-fx-border-radius: 9px; " +
                "-fx-background-radius: 8px;";

        String buttonHoverStyle = "-fx-background-color: #1E88E5;";
        String buttonPressedStyle = "-fx-background-color: #0D47A1;";
        String buttonAnimationStyle = "-fx-translate-x: 0px; -fx-translate-y: 0px;";
        ItemID.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        ItemNameID.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        ItemBrandID.setCellValueFactory(new PropertyValueFactory<>("itemBrand"));
        ItemPriceID.setCellValueFactory(new PropertyValueFactory<>("price"));
        ItemQuantityID.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        ItemCategoryID.setCellValueFactory(new PropertyValueFactory<>("category"));
        ItemDateID.setCellValueFactory(new PropertyValueFactory<>("date"));
        ItemImageID.setCellValueFactory(new PropertyValueFactory<>("Button"));
        ViewImageID.setCellValueFactory(new PropertyValueFactory<>("Button"));

        // Set a custom cell factory for the ItemImageID column
        ItemImageID.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Items, Button> call(TableColumn<Items, Button> param) {
                return new TableCell<>() {

                    private final Button button = new Button("Image");

                    {
                        button.setStyle(buttonStyle);
                    }

                    @Override
                    protected void updateItem(Button item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(button);
                        }
                    }
                };
            }
        });

        itemsTable.setItems(list);
    }


}






