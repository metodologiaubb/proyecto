/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_empresa_ii;

import com.sun.prism.shader.Solid_TextureFirstPassLCD_Loader;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import proyecto_empresa_ii.modelo.Conexion;
import proyecto_empresa_ii.modelo.consultas;
import sesion.Autentificar;
import sesion.Sesion;


/**
 *
 * @author balannor
 */
public class Main extends Application {
    private Stage stage;
    Conexion con;
    consultas cons;
    Sesion sesion;
    private final double MINIMUM_WINDOW_WIDTH = 390.0;
    private final double MINIMUM_WINDOW_HEIGHT = 500.0;
    AnchorPane page;
    Scene scene;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
            con=new Conexion();
        con.establecerConexion();
        cons=new consultas(con.getConnection());
   Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setMinWidth(MINIMUM_WINDOW_WIDTH);
        primaryStage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
        primaryStage.setTitle("Iniciar Sesión");
        primaryStage.getIcons().add(new Image(this.getClass().getResource("mseal32.png").toString()));
//        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

      
       Scene scene = new Scene(root);
    primaryStage.setScene(scene);        
       primaryStage.show();
       primaryStage.setMaxHeight(MINIMUM_WINDOW_WIDTH);
       primaryStage.setMaxWidth(MINIMUM_WINDOW_WIDTH);
       //primaryStage.setOnCloseRequest((WindowEvent event1) -> System.exit(0));//Cierra todo
        
//        Scene scene = new Scene(root);
        //primaryStage.show();
    
    

     
        
    }
   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
