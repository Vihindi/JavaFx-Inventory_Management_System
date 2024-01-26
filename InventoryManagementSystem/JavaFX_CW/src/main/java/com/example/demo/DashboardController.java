package com.example.demo;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DashboardController implements Initializable {
    @FXML
    private ImageView Exit;

    @FXML
    private Label Menu;

    @FXML
    private Label MenuBack;

    @FXML
    private AnchorPane slider;
    @FXML
    private AnchorPane mainAnchor;

    private boolean isMenuVisible = false; // Track the visibility state of the menu
    @FXML
    private BorderPane MainPain;

    @FXML
    private StackPane contentArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize the default content
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("Home.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);

            //Parent fxml2 = FXMLLoader.load(getClass().getResource("Home.fxml"));
           //mainAnchor.getChildren().removeAll();
            //mainAnchor.getChildren().setAll(fxml2);

        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }

        Exit.setOnMouseClicked(event -> {
            System.exit(0);
        });

        slider.setTranslateX(-176);

        Menu.setOnMouseClicked(event -> {
            if (!isMenuVisible) {
                showMenu();
            } else {
                hideMenu();
            }
        });

        MenuBack.setOnMouseClicked(event -> {
            if (!isMenuVisible) {
                showMenu();
            } else {
                hideMenu();
            }
        });
    }

    @FXML
    public void DashboardAction(ActionEvent event) throws IOException {
        loadFXML("Home.fxml",contentArea);


    }

    @FXML
    public void DealersAction(ActionEvent event) throws IOException {
        loadFXML("Dealer.fxml", contentArea);
    }

    @FXML
    public void ItemsAction(ActionEvent event) throws IOException {
        loadFXML("Items.fxml", contentArea);
    }

    @FXML
    public void LogOutAction(ActionEvent event) {
        // Add your logic for LogOut action here
    }

    @FXML
    public void ProfileAction(ActionEvent event) {
        // Add your logic for Profile action here
    }

    public void loadFXML(String fxmlFile, Pane container) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource(fxmlFile));
        container.getChildren().removeAll();
        container.getChildren().setAll(fxml);
    }
    public void showMenu() {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(slider);

        slide.setToX(0);
        slide.play();

        slide.setOnFinished((ActionEvent e) -> {
            Menu.setVisible(false);
            MenuBack.setVisible(true);
        });

        isMenuVisible = true;
    }

    public void hideMenu() {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(slider);

        slide.setToX(-slider.getWidth());
        slide.play();

        slide.setOnFinished((ActionEvent e) -> {
            Menu.setVisible(true);
            MenuBack.setVisible(false);
        });

        isMenuVisible = false;
    }
}
