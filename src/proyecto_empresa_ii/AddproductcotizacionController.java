/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_empresa_ii;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Window;
import proyecto_empresa_ii.modelo.Conexion;
import proyecto_empresa_ii.modelo.Cotizacion;
import proyecto_empresa_ii.modelo.Producto;
import proyecto_empresa_ii.modelo.Producto1;
import proyecto_empresa_ii.modelo.consultas;

/**
 * FXML Controller class
 *
 * @author pascaliwi
 */
public class AddproductcotizacionController implements Initializable {

    @FXML
    private ComboBox<Cotizacion> cmbcot;
    @FXML
    private ComboBox <Producto1> cmbproducto;
    @FXML
    private JFXButton btninsertar;
    @FXML
    private JFXButton btnnuevo_producto;
    @FXML
    private Label mensajesql;
    private ObservableList<Cotizacion>listacot;
    public ObservableList<Producto1> listaproducto;
    Conexion conexion;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conexion = new Conexion();
        conexion.establecerConexion();
     
        listaproducto=FXCollections.observableArrayList();
        listacot=FXCollections.observableArrayList();
    Cotizacion.llenarInformacion (listacot);
        try {
            Producto1.llenarproducto(listaproducto);
        } catch (SQLException ex) {
            Logger.getLogger(AddproductcotizacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
Producto.autocompletar1(cmbproducto, listaproducto);
Cotizacion.autocompletar(cmbcot, listacot);
    }

    
    @FXML
    private void insertarprodencot(ActionEvent event) {
    int x;
  x=consultas.Insert("INSERT INTO `pertenece`(`ID_COT`, `ID_PRODUCT`) VALUES ('"+cmbcot.getValue().getId_cot()+"','"+cmbproducto.getValue().getID_PRODUCTO()+"')");
               if(x==0){
    mensajesql.setText("Producto ingresado a cotización exitosamente");
    }}
              

    @FXML
    private void eventoLimpiar(ActionEvent event) {
        limpiar();
    }
    
   private void limpiar(){
        cmbcot.setValue(null);
        cmbproducto.setValue(null);
   }
   
   
}
