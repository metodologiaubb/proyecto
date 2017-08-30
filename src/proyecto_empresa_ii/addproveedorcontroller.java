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
import proyecto_empresa_ii.modelo.Proveedor;
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
    private JFXTextField jtftelefono_proveedor;
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
    private ObservableList<Proveedor>   listaproveedor;
    private ComboBox<Proveedor> cmbproveedor1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void insertarproducto(ActionEvent event) {
        
          int x=5;
          if(jtfnombre_proveedor.getText().length()!=0 && jtftelefono_proveedor.getText().length()!=0 && jtfn_pro_proveedor.getText().length()!=0){
  x=consultas.Insert("INSERT INTO `proveedor`(`NOMBRE_PROVEEDOR`, `DCTO_PROVEEDOR`, `C_PRO_DCTO`) VALUES ('"+jtfnombre_proveedor.getText()+"','"+jtftelefono_proveedor.getText()+"','"+jtfn_pro_proveedor.getText()+"')");
          } if(x==0){
    mensajesql.setText("Proveedor ingresado ");
     listaproveedor=null;
      //  cmbproveedor1.setValue(null);
        cmbproveedor1.getItems().clear();
        listaproveedor=FXCollections.observableArrayList();
        Proveedor.llenarInformacion(conexion.getConnection(), listaproveedor);
        Proveedor.autocompletar(cmbproveedor1, listaproveedor);
        limpiar();
    }else{
           mensajesql.setText("Complete todos los campos");
          }
    }

    @FXML
    private void eventoLimpiar(ActionEvent event) {
        limpiar();
        
    }
    private void limpiar(){
        jtfnombre_proveedor.setText(null);
        jtftelefono_proveedor.setText(null);
        jtfn_pro_proveedor.setText(null);
        
        
    }
    public void setlistabaox(ComboBox<Proveedor> cmbproveedor,ObservableList<Proveedor>  listaproveedo){
        listaproveedor=listaproveedo;
        cmbproveedor1=cmbproveedor;
    }
    
}
