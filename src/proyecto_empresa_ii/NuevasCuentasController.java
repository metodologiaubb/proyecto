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
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import proyecto_empresa_ii.modelo.consultas;

/**
 * FXML Controller class
 *
 * @author Familia Alarcòn
 */
public class NuevasCuentasController implements Initializable {

   
 
    @FXML
    private JFXTextField tfCuentaCuenta;

    @FXML
    private JFXTextField tfCuentaContraseña;

    @FXML
    private JFXTextField tfCuentaNombre;

    @FXML
    private JFXTextField tfCuentaApellidos;

    @FXML
    private JFXTextField tfCuentaTelefono;
    ObservableList<String> items = FXCollections.observableArrayList("Administrador", "Usuario");
    @FXML
    private ComboBox<String> cbCuentaTipo;
    
    @FXML
    private JFXButton btnGuardarCambios;

    
   

 

    public void initialize(URL url, ResourceBundle rb) {
         cbCuentaTipo.setItems(items);
    
            
       }
      /*
     @FXML
  private void LimpiarDatos(ActionEvent event) {
        tfCuentaCuenta.setText(null);
        tfCuentaNombre.setText(null);
        tfCuentaContraseña.setText(null);
        tfCuentaApellidos.setText(null);
        tfCuentaTelefono.setText(null);
        cbCuentaTipo.setItems(null);
        cbCuentaTipo.setItems(items);
    }*/
     
 @FXML
   private void GuardarCuenta(ActionEvent event) {
       int x = 2;
        if(cbCuentaTipo.getValue() == "Administrador"){
             x=consultas.Insert("INSERT INTO `"
                              + "user`"
                              + "( `USER_USERNAME`,"
                              + " `USER_PASS`,"
                              + " `USER_NOMBRE`,"
                              + " `USER_APELLIDO`,"
                              + " `USER_FONO`,"
                              + " `USER_ROL`)VALUES('"+tfCuentaCuenta.getText()+"','"+tfCuentaContraseña.getText()+"','"+tfCuentaNombre.getText()+"','"+tfCuentaApellidos.getText()+"','"+tfCuentaTelefono.getText()+"','"+2+"');");

        }
         if(cbCuentaTipo.getValue() == "Usuario"){
            x=consultas.Insert("INSERT INTO `"
                              + "user`"
                              + "(`USER_USERNAME`,"
                              + " `USER_PASS`,"
                              + " `USER_NOMBRE`,"
                              + " `USER_APELLIDO`,"
                              + " `USER_FONO`,"
                              + " `USER_ROL`)VALUES('"+tfCuentaCuenta.getText()+"','"+tfCuentaContraseña.getText()+"','"+tfCuentaNombre.getText()+"','"+tfCuentaApellidos.getText()+"','"+tfCuentaTelefono.getText()+"','"+1+"');");
    

         }
   if (x==0){
             Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
             alert2.setHeaderText("");      
             alert2.setTitle("Cuenta creada");
             alert2.setContentText("Se ha creado la cuenta: "+tfCuentaCuenta.getText());
             Optional<ButtonType> result2 = alert2.showAndWait();
             tfCuentaCuenta.setText(null);
             tfCuentaNombre.setText(null);
             tfCuentaContraseña.setText(null);
             tfCuentaApellidos.setText(null);
             tfCuentaTelefono.setText(null);
             cbCuentaTipo.setItems(null);
             cbCuentaTipo.setItems(items);
   }
}
}