/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_empresa_ii;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author Familia Alarcòn
 */
public class NuevasCuentasController implements Initializable {

   
    @FXML
    private JFXTextField tfCuentaCuenta;
     
    @FXML
    private JFXTextField tfCuentaNombre;

    @FXML
    private JFXTextField tfCuentaContraseña;

    @FXML
    private JFXTextField tfCuentaApellidos;

    @FXML
    private JFXTextField tfCuentaTelefono;
    
    ObservableList<String> items = FXCollections.observableArrayList("Administrador", "Usuario");

    @FXML
    private ComboBox<String> cbCuentaTipo;

    @FXML
    private JFXButton LimpiarCambios;

    @FXML
    private JFXButton btnGuardarCambios;



    public void initialize(URL url, ResourceBundle rb) {
         cbCuentaTipo.setItems(items);
    
            
       }
     @FXML
    void LimpiarDatos(ActionEvent event) {
        tfCuentaCuenta.setText(null);
        tfCuentaNombre.setText(null);
        tfCuentaContraseña.setText(null);
        tfCuentaApellidos.setText(null);
        tfCuentaTelefono.setText(null);
        cbCuentaTipo.setItems(null);
        cbCuentaTipo.setItems(items);
    }
  
}
    
