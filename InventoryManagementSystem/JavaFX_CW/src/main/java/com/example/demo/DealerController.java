package com.example.demo;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DealerController {

    @FXML
    private TableColumn< Dealers,String > DealerContactNoID;

    @FXML
    private TableColumn<Dealers,String > DealerID;

    @FXML
    private TableColumn<Dealers,String > DealerLocationID;

    @FXML
    private TableColumn<Dealers,String > DealerNameID;

    @FXML
    private TableView<Dealers> DealerTable;

    @FXML
    private TableColumn<Dealers,String > ItemsID;

    @FXML
    private JFXButton randomBtn;

    @FXML
    private TextField searchField;

    private static ObservableList<Dealers> Dealerlist;
    private static ObservableList<Dealers>getList(){return Dealerlist;}

    FileHandling fileHandling = new FileHandling();
    @FXML
    public void viewItemsClick(MouseEvent event) {
        Dealers selectedDealer = DealerTable.getSelectionModel().getSelectedItem();
        if (selectedDealer != null) {
            String selectedID = selectedDealer.getDealerID();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DealerItems.fxml"));
                Parent parent = loader.load();
                DealerItemsController dealerItemsController = loader.getController();
                dealerItemsController.displayItems(selectedID);
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ItemsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    @FXML
    public void randomClick(MouseEvent event) {
        GetExistingInfo();
        DealerTable.setItems(Dealerlist);
    }
    public  List<Integer> generateRandomIntegers() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers, new Random());
        System.out.println(numbers);
        // Get the first 'count' elements from the shuffled list
        return numbers.subList(0, 4);
    }


    public void GetExistingInfo() {
        ReadfileResult result = fileHandling.ReadDealerDetails();
        Map<String, List<Object>> hashmap = result.getHashmap();
        int NoOfLines = result.getNoOfLines();
        List<Integer> randomNo = generateRandomIntegers();
        Dealerlist = FXCollections.observableArrayList(); // Initialize the list
        for (int i = 0; i < NoOfLines; i++) {
            if (i!=0 && randomNo.contains(i)){
            String dealerID = (String) hashmap.get("col1").get(i);
            String dealerName = (String) hashmap.get("col2").get(i);
            String contactNo = (String) hashmap.get("col3").get(i);
            String location = (String) hashmap.get("col4").get(i);


            Dealers dealers = new Dealers(dealerID, dealerName,contactNo, location);
            Dealerlist.add(dealers);}
        }
        sort sorter = new sort();
        sort.InsertionSort insertionSorter = sorter.new InsertionSort();
        insertionSorter.insertionSort(Dealerlist);
        searchFilter();

    }

    public void searchFilter() {
        FilteredList<Dealers> filterData= new FilteredList<>(Dealerlist, e->true);
        searchField.setOnKeyReleased(e->{


            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                filterData.setPredicate((Predicate<? super Dealers >) dealer->{
                    if(newValue==null){
                        return true;
                    }
                    String toLowerCaseFilter = newValue.toLowerCase();
                    if(dealer.getDealerID().contains(newValue)){
                        return true;
                    }else  if(dealer.getDealerName().toLowerCase().contains(toLowerCaseFilter)){
                        return true;
                    }else  if(dealer.getContactNo().contains(toLowerCaseFilter)){
                        return true;
                    }else  if(dealer.getLocation().toLowerCase().contains(toLowerCaseFilter)){
                        return true;
                    }

                    return false;
                });
            });

            final SortedList<Dealers> customers = new SortedList<>(filterData);
            customers.comparatorProperty().bind(DealerTable.comparatorProperty());
            DealerTable.setItems(customers);

        });

    }
    public void initialize() {

        DealerID.setCellValueFactory(new PropertyValueFactory<>("dealerID"));
        DealerNameID.setCellValueFactory(new PropertyValueFactory<>("dealerName"));
        DealerContactNoID.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        DealerLocationID.setCellValueFactory(new PropertyValueFactory<>("location"));

    }

}
