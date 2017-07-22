/*
 * Copyright (c) 2012, Oracle and/or its affiliates. All rights reserved.
 */
package proyecto_empresa_ii;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import sesion.Autentificar;
import sesion.Sesion;


/**
 * Login Controller.
 */
public class LoginController extends AnchorPane implements Initializable {

    @FXML
    private AnchorPane ap;
    @FXML
    TextField userId;
    @FXML
    PasswordField password;
    @FXML
    Button login;
    @FXML
    Label errorMessage;
    public static String id; 
    public static String pass; 
   


    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        errorMessage.setText("");
        
        
        
        
    }

    public void processLogin(ActionEvent event) throws IOException {
 
            if (!userLogging(userId.getText(), password.getText())){
                errorMessage.setText("Usuario desconocido " + userId.getText());
            }
            
        }
       private boolean userLogging(String username, String password) throws IOException{
           
                System.out.println("got user id " + username + " password " + password);
        if (Autentificar.validar(username, password)) {
            System.out.println("OK");
                Stage stage1  = (Stage) login.getScene().getWindow();
    stage1.close();
            Sesion sesion =new Sesion(Autentificar.getSQLuser());

        FXMLLoader fXMLLoader = new FXMLLoader(
                    getClass().getResource("FXMLDocument.fxml")
            );
            Parent root1 = (Parent) fXMLLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Panel de cotizaciones");
            stage.setScene(new Scene(root1));
            root1.addEventFilter(
                KeyEvent.KEY_PRESSED, eventt -> {
                    if (eventt.getCode().equals(KeyCode.F11)) {
                        stage.setFullScreen(true);
                    }
                }
            );
            stage.show();
            stage.setOnCloseRequest((WindowEvent event1) -> System.exit(0));//Cierra todo
         stage.getIcons().add(new Image(this.getClass().getResource("mseal32.png").toString()));
            return true;
        } else {
            System.out.println("NOT OK");
            System.err.print("asdasd");
            return false;
        }
       }
    }

