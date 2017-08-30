/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_empresa_ii;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import static proyecto_empresa_ii.FXMLDocumentController.conexion;
import proyecto_empresa_ii.modelo.Marca;
import proyecto_empresa_ii.modelo.consultas;

/**
 * FXML Controller class
 *
 * @author pascaliwi
 */
public class addmarcacontroller implements Initializable {

    @FXML
    private JFXTextField tfwnombre_marca;
    @FXML
    private JFXButton btnguardar_marca;
    @FXML
    private JFXButton btnnuevo_proveedor;
    @FXML
    private Label mensajesql;

    /**
     * Initializes the controller class.
     */
    private  ComboBox<Marca> cmbmarca;
    private ObservableList<Marca>       listamarcas1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        
// TODO
    }    

    @FXML
    private void insertarproducto(ActionEvent event) {
              int x;
  x=consultas.Insert("INSERT INTO `marca`(`NOMBRE_MARCA`) VALUES ('"+tfwnombre_marca.getText()+"');");
               if(x==0){
    mensajesql.setText("Marca ingresada");
    listamarcas1=null;
       cmbmarca.setValue(null);
  cmbmarca.getItems().clear();
    listamarcas1  =FXCollections.observableArrayList();
           Marca.llenarInformacion(conexion.getConnection(), listamarcas1);
            Marca.autocompletar(cmbmarca, listamarcas1);
            tfwnombre_marca.setText(null); 
    }
               
        
    }
    public void setcomobox(ComboBox<Marca> cmbmarc,ObservableList<Marca> listamarca){
               cmbmarca=cmbmarc;
               listamarcas1=listamarca;
    }

    @FXML
    private void eventoLimpiar(ActionEvent event) {
        tfwnombre_marca.setText(null);   
    }
    
}
