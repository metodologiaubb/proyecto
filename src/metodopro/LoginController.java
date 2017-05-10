/*
 * Copyright (c) 2012, Oracle and/or its affiliates. All rights reserved.
 */
package metodopro;

import bd.consultas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import modelos.User;

/**
 * Login Controller.
 */
public class LoginController extends AnchorPane implements Initializable {

    @FXML
    TextField userId;
    @FXML
    PasswordField password;
    @FXML
    Button login;
    @FXML
    Label errorMessage;

    private MetodoPro application;
    
    
    public void setApp(MetodoPro application){
        this.application = application;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorMessage.setText("");
    }

    public void processLogin(ActionEvent event) {
        if (application == null){
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            errorMessage.setText("Hola " + userId.getText());
        } else {
            if (!application.userLogging(userId.getText(), password.getText())){
                errorMessage.setText("Usuario desconocido " + userId.getText());
            }
        }
       
    }
}
