/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodopro;

import bd.conexion;
import bd.consultas;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sesion.Autentificar;
import sesion.Sesion;

/**
 *
 * @author jerson
 */
public class MetodoPro extends Application {
    private Stage stage;
    private final double MINIMUM_WINDOW_WIDTH = 390.0;
    private final double MINIMUM_WINDOW_HEIGHT = 500.0;
    conexion con;
    consultas cons;
    Sesion sesion;
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
        stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
//        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
//        
//        Scene scene = new Scene(root);
        gotoLogin();
        primaryStage.show();
         con=new conexion();
         cons=new consultas(con.getconexion());
    }
     public boolean userLogging(String username, String password){
        System.out.println("got user id " + username + " password " + password);
        if (Autentificar.validar(username, password)) {
            System.out.println("OK");
            sesion =new Sesion(Autentificar.getSQLuser());
            gotoCotiza();
            return true;
        } else {
            System.out.println("NOT OK");
            return false;
        }
    }
       private void gotoLogin() {
        try {
            metodopro.LoginController login = (metodopro.LoginController) replaceSceneContent("Login.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(MetodoPro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
       private void gotoCotiza() {
        try {
            cotizaController cotiza = (cotizaController) replaceSceneContent("cotiza.fxml");
            cotiza.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(MetodoPro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = MetodoPro.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(MetodoPro.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }
        
        // Store the stage width and height in case the user has resized the window
        double stageWidth = stage.getWidth();
        if (!Double.isNaN(stageWidth)) {
            stageWidth -= (stage.getWidth() - stage.getScene().getWidth());
        }
        
        double stageHeight = stage.getHeight();
        if (!Double.isNaN(stageHeight)) {
            stageHeight -= (stage.getHeight() - stage.getScene().getHeight());
        }
        
        Scene scene = new Scene(page);
        if (!Double.isNaN(stageWidth)) {
            page.setPrefWidth(stageWidth);
        }
        if (!Double.isNaN(stageHeight)) {
            page.setPrefHeight(stageHeight);
        }
        
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Application.launch(MetodoPro.class, (java.lang.String[])null);
    }
    
}
