package com.example.demo;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

public class AddItemsController {

    @FXML
    private  TextField Brand;

    @FXML
    private  TextField Category;

    @FXML
    private TextField Code;

    @FXML
    private  DatePicker Date;

    @FXML
    private  TextField Name;

    @FXML
    private  TextField Price;

    @FXML
    private  TextField Quantity;
    @FXML
    private Label dateError;

    @FXML
    private Label priceError;

    @FXML
    private Label quantityError;

    @FXML
    private Button addBtn;

    @FXML
    private Button cancelBtn;
    // Method to populate the text fields with data from the selected row
    @FXML
    private Button updateID;
    private Items clickItem;

    //    public void initialize(){
//        setTexts();
//    }
    public void setButtonVisibility(boolean button1Visible, boolean button2Visible) {
        addBtn.setVisible(button1Visible);
        updateID.setVisible(button2Visible);
    }



    public boolean validatePrice() {
        try {
            Double.parseDouble(Price.getText());
            Price.setStyle("-fx-text-fill: black;");
            priceError.setVisible(false);
            return true;
        } catch (NumberFormatException e) {
            Price.setStyle("-fx-text-fill: red;");
            priceError.setText("Invalid price. Please enter a valid value.");
            priceError.setVisible(true);
            return false;
        }
    }

    public boolean validateQuantity() {
        try {
            Integer.parseInt(Quantity.getText());
            Quantity.setStyle("-fx-text-fill: black;");
            quantityError.setVisible(false);
            return true;
        } catch (NumberFormatException e) {
            Quantity.setStyle("-fx-text-fill: red;");
            quantityError.setText("Invalid Quantity. Please enter a valid value.");
            quantityError.setVisible(true);
            return false;
        }
    }

    public boolean validateDate() {
        if (Date.getValue() != null) {
            Date.setStyle("-fx-text-fill: black;");
            dateError.setVisible(false);
            return true;
        } else {
            Date.setStyle("-fx-text-fill: red;");
            dateError.setText("Invalid Date. Please enter a valid value.");
            dateError.setVisible(true);
            return false;
        }
    }

    public boolean validateInputs() {
        return !Code.getText().isEmpty()
                && !Name.getText().isEmpty()
                && !Brand.getText().isEmpty()
                && !Price.getText().isEmpty()
                && !Category.getText().isEmpty()
                && validateDate()
                && validateQuantity()
                && validatePrice();
    }

    @FXML
    public void AddClick(MouseEvent event) throws IOException {
        if (validateInputs()) {
            String itemCode = Code.getText();
            String itemName = Name.getText();
            String itemBrand = Brand.getText();
            double itemPrice = Double.parseDouble(Price.getText());
            int itemQuantity = Integer.parseInt(Quantity.getText());
            String itemCategory = Category.getText();
            LocalDate itemDate = Date.getValue();

            Items item = new Items(itemCode, itemName, itemBrand, itemPrice, itemQuantity, itemCategory, itemDate);  // Creation of an object,item
            ItemsController.getList().add(item);
           // System.out.println(Arrays.toString(new String[]{Arrays.toString(ItemsController.list.toArray()).toString()}));
            clearInputs();
            sort sorter = new sort();  // Creation of an object,sort
            sort.Bubblesort bubblesort = sorter.new Bubblesort();
            bubblesort.bubbleSort(ItemsController.list);
        }
    }

    @FXML
    public void cancelClick(MouseEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

     ItemsController itemsController = new ItemsController();
    @FXML
    public void updateBtnClick(MouseEvent event) {
        Items clickedItem = ItemsController.getClickedItem();
        if (validateInputs()) {
            String itemCode = Code.getText();
            System.out.println(itemCode);
            String itemName = Name.getText();
            String itemBrand = Brand.getText();
            double itemPrice = Double.parseDouble(Price.getText());
            int itemQuantity = Integer.parseInt(Quantity.getText());
            String itemCategory = Category.getText();
            LocalDate itemDate = Date.getValue();
            ItemsController.getClickedItem().setItemCode(itemCode);
            ItemsController.getClickedItem().setItemName(itemName);
            ItemsController.getClickedItem().setItemBrand(itemBrand);
            ItemsController.getClickedItem().setPrice(Double.parseDouble(String.valueOf(itemPrice)));
            ItemsController.getClickedItem().setQuantity(Integer.parseInt(String.valueOf(itemQuantity)));
            ItemsController.getClickedItem().setCategory(itemCategory);
            ItemsController.getClickedItem().setDate(itemDate);
            clearInputs();

        }

            clearInputs();

        }

    public Items getTexts() {
        if (validateInputs()) {
            String itemCode = Code.getText();
           String itemName = Name.getText();
            String itemBrand = Brand.getText();
            double itemPrice = Double.parseDouble(Price.getText());
            int itemQuantity = Integer.parseInt(Quantity.getText());
            String itemCategory = Category.getText();
            LocalDate itemDate = Date.getValue();
           return new Items(itemCode, itemName, itemBrand, itemPrice, itemQuantity, itemCategory, itemDate);
//
        }
       return null; // validate this
    }
    //private Items clickItem;
    public void setItemTableFields(Items clickedItem){
     if (validateInputs()) {
        String itemCode = Code.getText();
        String itemName = Name.getText();
        String itemBrand = Brand.getText();
        double itemPrice = Double.parseDouble(Price.getText());
        int itemQuantity = Integer.parseInt(Quantity.getText());
        String itemCategory = Category.getText();
        LocalDate itemDate = Date.getValue();
       clickedItem.setItemCode(itemCode);
       clickedItem.setItemName(itemName);
       clickedItem.setItemBrand(itemBrand);
       clickedItem.setPrice(Double.parseDouble(String.valueOf(itemPrice)));
       clickedItem.setQuantity(Integer.parseInt(String.valueOf(itemQuantity)));
       clickedItem.setCategory(itemCategory);
       clickedItem.setDate(itemDate);

//
//
    }}
    public Items clickItem(Items clickItem){
        this.clickItem = clickItem;
        return clickItem;
    }


    public void setTexts(Items clickedItem){

       Code.setText(String.valueOf(clickedItem.getItemCode()));
        Name.setText(String.valueOf(clickedItem.getItemName()));
        Brand.setText(String.valueOf(clickedItem.getItemBrand()));
        Price.setText(String.valueOf(clickedItem.getPrice()));
        Quantity.setText(String.valueOf(clickedItem.getQuantity()));
        Category.setText(String.valueOf(clickedItem.getCategory()));
        Date.setValue(clickedItem.getDate());

   }

    public void clearInputs(){
        Code.clear();
        Name.clear();
        Brand.clear();
        Price.clear();
        Quantity.clear();
        Category.clear();
        Date.setValue(null);
    }


    }



