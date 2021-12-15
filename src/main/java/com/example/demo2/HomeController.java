package com.example.demo2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button allTariffsButton;

    @FXML
    private Button chooseTariffButton;

    @FXML
    void initialize() {
        allTariffsButton.setOnAction(event -> {
            openNewScene("allTariffs.fxml");
        });
        chooseTariffButton.setOnAction(event -> {
            openNewScene("chooseTariff.fxml");
        });

    }
    public void openNewScene(String window){
        allTariffsButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

}

