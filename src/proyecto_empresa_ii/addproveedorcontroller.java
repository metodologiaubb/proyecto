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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import proyecto_empresa_ii.modelo.consultas;

/**
 * FXML Controller class
 *
 * @author pascaliwi
 */
public class addproveedorcontroller implements Initializable {

    @FXML
    private JFXTextField jtfnombre_proveedor;
    @FXML
    private JFXTextField jtfdcto_proveedor;
    @FXML
    private JFXTextField jtfn_pro_proveedor;
    @FXML
    private JFXButton btnguardar_proveedor;
    @FXML
    private JFXButton btnnuevo_proveedor;
    @FXML
    private Label mensajesql;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void insertarproducto(ActionEvent event) {
        
          int x;
  x=consultas.Insert("INSERT INTO `proveedor`(`NOMBRE_PROVEEDOR`, `DCTO_PROVEEDOR`, `C_PRO_DCTO`) VALUES ('"+jtfnombre_proveedor.getText()+"','"+jtfdcto_proveedor.getText()+"','"+jtfn_pro_proveedor.getText()+"')");
               if(x==0){
    mensajesql.setText("Proveedor ingresado ");
    }
    }

    @FXML
    private void eventoLimpiar(ActionEvent event) {
        limpiar();
        
    }
    private void limpiar(){
        jtfnombre_proveedor.setText(null);
        jtfdcto_proveedor.setText(null);
        jtfn_pro_proveedor.setText(null);
        
        
    }
    
}