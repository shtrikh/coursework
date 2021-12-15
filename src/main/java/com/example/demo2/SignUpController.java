package com.example.demo2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button SignUpButton;

    @FXML
    private CheckBox SignUpCheckBoxFemale;

    @FXML
    private CheckBox SignUpCheckBoxMale;

    @FXML
    private TextField SignUpCheckCountry;

    @FXML
    private TextField SignUpLastName;

    @FXML
    private TextField SignUpLogin;

    @FXML
    private TextField SignUpName;

    @FXML
    private PasswordField SignUpPassword;

    @FXML
    private ImageView imgButtonHome;

    @FXML
    void initialize() {

        SignUpButton.setOnAction(event -> {
            signUpNewUser();
        });

    }

    private void signUpNewUser() {

        DatabaseHandler dbHandler = new DatabaseHandler();

        String firstName = SignUpName.getText();
        String lastName = SignUpLastName.getText();
        String userName = SignUpLogin.getText();
        String password = SignUpPassword.getText();
        String location = SignUpCheckCountry.getText();
        String gender = "";
        if(SignUpCheckBoxMale.isSelected()){
            gender = "Male";
        } else
            gender = "Female";

        User user = new User(firstName, lastName, userName, password, location, gender);

        dbHandler.signUpUser(user);

    }

}
