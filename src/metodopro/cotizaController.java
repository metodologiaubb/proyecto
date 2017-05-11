/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodopro;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sesion.Sesion;


/**
 *
 * @author jerson
 */
public class cotizaController implements Initializable {
    
    //@FXML
    @FXML
    private TextField tfnombre;
    @FXML
    private TextField tfapellido;
    @FXML
    private TextField tftelefono;
    @FXML
    private TextField tfcontrasena;
    @FXML
    private TableView tablacot;
    @FXML
    private TableColumn IDcot;
    @FXML
    private TableColumn FechaCot;
    @FXML
    private TableColumn Nproducot;
    @FXML
    private TableColumn descripcot;
    @FXML
    private void handleButtonAction(ActionEvent event) {
       
    }
    
    private MetodoPro application;
    
    
    public void setApp(MetodoPro application){
        this.application = application;
        tfnombre.setText(Sesion.CurrentUser.getUSER_NOMBRE());
        tfapellido.setText(Sesion.CurrentUser.getUSER_APELLIDO());
        tftelefono.setText(Sesion.CurrentUser.getFONO_USER());
        tfcontrasena.setText(Sesion.CurrentUser.getPASS_USER());
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
