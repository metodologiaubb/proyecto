/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_empresa_ii;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import proyecto_empresa_ii.modelo.Conexion;
import proyecto_empresa_ii.modelo.Marca;
import proyecto_empresa_ii.modelo.Proveedor;
import proyecto_empresa_ii.modelo.consultas;

/**
 * FXML Controller class
 *
 * @author balannor
 */
public class AddProductController implements Initializable {
    

    @FXML
    private JFXTextField jtfnombre_producto;
    @FXML
    private JFXTextField jtfum_producto;
    @FXML
    private ComboBox<Marca> cmbmarca_producto;
    private JFXTextField jtfvalor_producto;
    @FXML
    private Label mensajesql;
    @FXML
    private JFXButton btnguardar_producto;
    @FXML
    private JFXButton btnnuevo_producto;
    @FXML
    void eventoLimpiar(ActionEvent event) {
        limpiar();
    }
    private ObservableList<Marca>       listamarcas;
   // private ObservableList<Proveedor>   listaproveedor;
    Conexion conexion;
    String date1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                 conexion = new Conexion();
        conexion.establecerConexion();
llename();
    }    
    

    
    private void limpiar(){
        jtfnombre_producto.setText(null);
    //    date_pentrega.setValue(null);
        jtfum_producto.setText(null);
        cmbmarca_producto.setValue(null);
        cmbmarca_producto.getItems().clear();
        //cmbproveedor_producto.setValue(null);
      //  jtfvalor_producto.setText(null);
      llename();
    };
public void llename(){
   
        listamarcas     =FXCollections.observableArrayList();
    //    listaproveedor  =FXCollections.observableArrayList();
    //    Proveedor.llenarInformacion (conexion.getConnection(), listaproveedor);
        Marca.llenarInformacion     (conexion.getConnection(), listamarcas);
   //     Proveedor.autocompletar(cmbproveedor_producto, listaproveedor);
        Marca.autocompletar         (cmbmarca_producto, listamarcas);
    
}
    @FXML
    private void insertarproducto(ActionEvent event) {int x;
      //  date1();
     x=consultas.Insert("INSERT INTO `producto`(`NOMBRE_PRODUCTO`,`U_MEDIDA`, `ID_MARCA`) VALUES ('"+jtfnombre_producto.getText()+"','"+jtfum_producto.getText()+"','"+cmbmarca_producto.getValue().getId_marca()+"');");
        if(x==0){
    mensajesql.setText("Producto ingresado exitosamente");
    }}

   
}