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


/**
 *
 * @author jerson
 */
public class cotizaController implements Initializable {
    
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
       
    }
    
    private MetodoPro application;
    
    
    public void setApp(MetodoPro application){
        this.application = application;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
