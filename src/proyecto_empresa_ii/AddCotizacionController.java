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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import proyecto_empresa_ii.modelo.Conexion;
import proyecto_empresa_ii.modelo.consultas;

/**
 *
 * @author jerson
 */
public class AddCotizacionController {

    private JFXTextField jtfdescripcion_cotizacion;
    @FXML
    private JFXButton btnnuevo_cotizacion;
    @FXML
    private JFXButton btninsertar_cotizacion;
    @FXML
    private TextArea txtdescripcion_cotizacion;

    @FXML
    private void eventoLimpiar(ActionEvent event) {
        limpiar();  
    }
            Conexion conexion;
    
      public void initialize(URL url, ResourceBundle rb) {
        conexion = new Conexion();
        conexion.establecerConexion();
    }    
    
     
   
    
      private void limpiar(){
         txtdescripcion_cotizacion.setText(null);
    };

    private void insertarcot(ActionEvent event) {
        
        consultas.Insert("");
    }

    @FXML
    private void insertarproducto(ActionEvent event) {
    }
    
}
