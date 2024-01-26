package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class FileChooserController {

    @FXML
    private ImageView Exit;

    @FXML
    private ImageView displayImgID;
    private static String imagePath;

    public static void setImageFilePath(String path) {
        imagePath = path;
    }

    @FXML
    public void initialize() {
        if (imagePath != null) {
            // Load the image from the file path and display it in the ImageView
            File file = new File(imagePath);
            Image image = new Image(file.toURI().toString());
            displayImgID.setImage(image);
        }
    }
}

