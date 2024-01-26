package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.util.Duration;

public class HelloController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ImageView Close;

    @FXML
    private Button LogInBtn;

    @FXML
    private TextField UsernameID;

    @FXML
    private TextField PasswordID;

    @FXML
    public void LogInClick(ActionEvent event) throws IOException {
        if (UsernameID.getLength() != 0 && PasswordID.getLength() != 0) {
            root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else if(UsernameID.getLength() == 0 && PasswordID.getLength() == 0){
            animateValidation(UsernameID);
            animateValidation(PasswordID);
        } else if (UsernameID.getLength() == 0) {
            animateValidation(UsernameID);
        } else if (PasswordID.getLength() == 0) {
            animateValidation(PasswordID);
        }
    }

    public void animateValidation(TextField textField) {
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(textField.translateXProperty(), 0)),
                new KeyFrame(Duration.millis(100), new KeyValue(textField.translateXProperty(), -10)),
                new KeyFrame(Duration.millis(200), new KeyValue(textField.translateXProperty(), 10)),
                new KeyFrame(Duration.millis(300), new KeyValue(textField.translateXProperty(), -10)),
                new KeyFrame(Duration.millis(400), new KeyValue(textField.translateXProperty(), 10)),
                new KeyFrame(Duration.millis(500), new KeyValue(textField.translateXProperty(), -10)),
                new KeyFrame(Duration.millis(600), new KeyValue(textField.translateXProperty(), 10)),
                new KeyFrame(Duration.millis(700), new KeyValue(textField.translateXProperty(), -10)),
                new KeyFrame(Duration.millis(800), new KeyValue(textField.translateXProperty(), 0))
        );
        //textField.getStyleClass().add("text-field-error");

        timeline.play();
    }

    public void handleClose(MouseEvent mouseEvent) {
        System.exit(0);
    }
}
