package com.example.demo2;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.demo2.DatabaseHandler;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class allTariffsController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<AllTariffs> Table;

    @FXML
    private TableColumn<AllTariffs, Integer> TableMegabytes;

    @FXML
    private TableColumn<AllTariffs, Integer> TableMinutes;

    @FXML
    private TableColumn<AllTariffs, String> TableName;

    @FXML
    private TableColumn<AllTariffs, Integer> TablePrice;

    @FXML
    private TableColumn<AllTariffs, Integer> TableUsers;

    @FXML
    private Button ButtonBack;

    ObservableList<AllTariffs> allTarifs = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        DatabaseHandler dbhandler = new DatabaseHandler();
        PreparedStatement pst = null;



        try {
            pst = dbhandler.getDbConnection().prepareStatement("SELECT * FROM alltariffs");
        } catch (SQLException var9) {
            var9.printStackTrace();
        }

        try {
            ResultSet rs = pst.executeQuery();

            try {
                while(rs.next()) {
                    this.allTarifs.add(new AllTariffs(rs.getString(2), rs.getInt(3),
                            rs.getInt(4), rs.getInt(5), rs.getInt(6)));
                }
            } catch (Throwable var10) {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (Throwable var8) {
                        var10.addSuppressed(var8);
                    }
                }

                throw var10;
            }

            if (rs != null) {
                rs.close();
            }
        } catch (SQLException var11) {
            var11.printStackTrace();
        }



        TableName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        TablePrice.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getPrice()).asObject());
        TableUsers.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getUsers()).asObject());
        TableMinutes.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getMinutes()).asObject());
        TableMegabytes.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getMegabytes()).asObject());
        Table.setItems(allTarifs);


        ButtonBack.setOnAction(event -> {
            openNewScene("menu.fxml");
        });
    }


    public void openNewScene(String window){
        ButtonBack.getScene().getWindow().hide();

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
