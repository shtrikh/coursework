package com.example.demo2;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

import javafx.fxml.Initializable;
import javafx.scene.control.TableView;


public class chooseTariffController implements Initializable{


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ChooseTariffButton;


    @FXML
    private TableView<AllTariffs> TableColum;

    @FXML
    private Label label;

    @FXML
    private TableColumn<AllTariffs, String> TableChooseName;

    ObservableList<AllTariffs> allTarifs = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        ChooseTariffButton.setOnAction( actionEvent -> {
            label.setText("Choose succsesful");
        });

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
                while (rs.next()) {
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


        TableChooseName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        TableColum.setItems(allTarifs);
    }


}