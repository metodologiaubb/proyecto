/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodopro;

import bd.consultas;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import modelos.User;

/**
 *
 * @author jerson
 */
public class FXMLDocumentController implements Initializable {
    consultas cs;
    //@FXML
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
        int x;
        x=cs.Insert(User.Insert_User("jexxmt", "holi123", "8858376", 3, "jerson", "martinez"));//Prueba de funcionamiento
        if(x==0){
            System.out.println("insercion exitosa");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cs=new consultas();
    }    
    
}
