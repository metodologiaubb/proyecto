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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
    private ComboBox<Proveedor> cmbproveedor_producto;
    @FXML
    private ComboBox<Marca> cmbmarca_producto;
    @FXML
    private JFXTextField jtfvalor_producto;
    @FXML
    private DatePicker date_pentrega;
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
    private ObservableList<Proveedor>   listaproveedor;
    Conexion conexion;
    String date1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conexion = new Conexion();
        conexion.establecerConexion();
        listamarcas     =FXCollections.observableArrayList();
        listaproveedor  =FXCollections.observableArrayList();
        Proveedor.llenarInformacion (conexion.getConnection(), listaproveedor);
        Marca.llenarInformacion     (conexion.getConnection(), listamarcas);
        Proveedor.autocompletar(cmbproveedor_producto, listaproveedor);
        Marca.autocompletar         (cmbmarca_producto, listamarcas);
    }    
    
       private void date1() {
         String repo=date_pentrega.getValue().toString();
   String[] partes = repo.split("-");
   date1=partes[0]+partes[1]+partes[2];
    }
    
    private void limpiar(){
        jtfnombre_producto.setText(null);
        date_pentrega.setValue(null);
        jtfum_producto.setText(null);
        cmbmarca_producto.setValue(null);
        cmbproveedor_producto.setValue(null);
        jtfvalor_producto.setText(null);    
    };

    @FXML
    private void insertarproducto(ActionEvent event) {int x;
        date1();
        x=consultas.Insert("INSERT INTO `producto`(`NOMBRE_PRODUCTO`, `U_MEDIDA`, `PENTREGA`, `ID_PROVEEDOR`, `ID_MARCA`, `VALOR`) VALUES ('"+jtfnombre_producto.getText()+"','"+jtfum_producto.getText()+"','"+date1+"','"+cmbproveedor_producto.getValue().getId_proveedor()+"','"+cmbmarca_producto.getValue().getId_marca()+"','"+jtfvalor_producto.getText()+"');");
       if(x==0){
    mensajesql.setText("Producto ingresado exitosamente");
    }
    
    
    
}
}