/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_empresa_ii;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import proyecto_empresa_ii.modelo.Conexion;
import proyecto_empresa_ii.modelo.Cotizacion;
import proyecto_empresa_ii.modelo.Producto;
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
    private ComboBox<Producto> cmbproducto;
    @FXML
    private JFXButton btninsertar;
    @FXML
    private JFXButton btnnuevo_producto;
    @FXML
    private Label mensajesql;
    
        private ObservableList<Cotizacion>       listacot;
    private ObservableList<Producto>   listaproducto;
    Conexion conexion;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conexion = new Conexion();
        conexion.establecerConexion();
        
        listacot=FXCollections.observableArrayList();
        listaproducto=FXCollections.observableArrayList();
    Cotizacion.llenarInformacion (listacot);
    Producto.llenarInformacion (conexion.getConnection(), listaproducto);
    Cotizacion.autocompletar(cmbcot, listacot);
    Producto.autocompletar(cmbproducto,listaproducto);
    }    

    @FXML
    private void insertarprodencot(ActionEvent event) {
    int x;
  x=consultas.Insert("INSERT INTO `pertenece`(`ID_COT`, `ID_PRODUCT`) VALUES ('"+cmbcot.getValue().getId_cot()+"','"+cmbproducto.getValue().getId_producto()+"')");
               if(x==0){
    mensajesql.setText("Producto ingresado a cotización exitosamente");
    }
               
    }

    @FXML
    private void eventoLimpiar(ActionEvent event) {
        limpiar();
    }
    
   private void limpiar(){
        cmbcot.setValue(null);
        cmbproducto.setValue(null);
   }
}
