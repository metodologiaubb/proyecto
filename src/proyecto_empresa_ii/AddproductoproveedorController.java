/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_empresa_ii;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import proyecto_empresa_ii.modelo.Conexion;
import proyecto_empresa_ii.modelo.Producto;
import proyecto_empresa_ii.modelo.Producto1;
import proyecto_empresa_ii.modelo.Proveedor;
import proyecto_empresa_ii.modelo.consultas;

/**vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
 *
 * @author pascaliwi
 */
public class AddproductoproveedorController implements Initializable{
    @FXML
    private Label mensajesql;
    @FXML
    private ComboBox<Proveedor> cmbpprove;
    @FXML
    private ComboBox<Producto1> cmbppro;
    @FXML
    private JFXDatePicker datepentrega;
    @FXML
    private JFXTextField jtfvalor;
    @FXML
    private JFXTextField jtfdescuento;
    @FXML
    private JFXButton btnvincularproductoproveedor;
    @FXML
    private JFXButton limpiarproductoproveedor;
    @FXML
    private JFXButton btnnuevoproducto;
    @FXML
    private Label labeldescuento;
    
    static int valorfinal;
        Conexion con;
    String date1;
    ObservableList<Proveedor>   listaproveedor;
    ObservableList<Producto>   listaproductos;
        ObservableList<Producto1>   listaproductoss;
    @FXML 
    private void nuevoproducto(){
         try {
            FXMLLoader fXMLLoader = new FXMLLoader(
                    getClass().getResource("AddProduct.fxml")
            );
            Parent root1 = (Parent) fXMLLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("Nuevo producto");
            stage.setScene(new Scene(root1));
            /*Evento Dragg and drop*/
       
            root1.setOnMousePressed((MouseEvent event1) -> {
                double xOffset = event1.getSceneX();
                double yOffset = event1.getSceneY();
            });
            root1.setOnMouseDragged((MouseEvent event1) -> {
                double xOffset = 0;
                stage.setX(event1.getScreenX() - xOffset);
                double yOffset = 0;
                stage.setY(event1.getScreenY() - yOffset);
            });
            
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(
                    FXMLDocumentController.class.getName()).log(
                            Level.SEVERE, null, ex
                    );
        }
        
    }
    
    @FXML
    private void eventoLimpiar(ActionEvent event) {
        limpiar();
       
    }
    
    @FXML
    private void eventoDescuento(ActionEvent event){
        //char a=event.
    }
    private void date1() {
      String repo=datepentrega.getValue().toString();
   String[] partes = repo.split("-");
   date1=partes[0]+partes[1]+partes[2];}
    
    private void limpiar(){
        cmbpprove.setValue(null);
        datepentrega.setValue(null);
        cmbppro.setValue(null); 
        jtfvalor.setText(null);
     cmbpprove.getItems().clear();
     cmbppro.getItems().clear();
     mensajesql.setText(null);
        llename();
    }
    @FXML
    private void vincularproductoproveedor(ActionEvent event) {
        int x=5;
        int b,c;
        if(Integer.parseInt(jtfdescuento.getText())<=100 && Integer.parseInt(jtfdescuento.getText())>=0){
        b=Integer.parseInt(jtfdescuento.getText());
        c=Integer.parseInt(jtfvalor.getText());
        valorfinal=c-((b*c)/100);
        System.out.println("el valor final es:"+valorfinal);
        x=consultas.Insert("INSERT INTO `producto_proveedor`(`ID_PROVEEDOR`,`ID_PRODUCTO`, `VALOR`,`PENTREGA`,`VALOR_FINAL`) VALUES ('"+cmbpprove.getValue().getId_proveedor()+"','"+cmbppro.getValue().getID_PRODUCTO()+"','"+jtfvalor.getText()+"','"+date1+"','"+valorfinal+"');");
        }else{
         mensajesql.setText("El descuento ingresado no es correcto");
        }
           
      date1();
  
     
     if(x==0){
    mensajesql.setText("Producto "+cmbppro.getValue().getNOMBRE_PRODUCTO()+" vinculado a "+cmbpprove.getValue().getNombre_proveedor()+" exitosamente");
     
        }
        
    }
    private void llename(){
       listaproveedor  =FXCollections.observableArrayList();
       Proveedor.llenarInformacion (con.getConnection(), listaproveedor);
       Proveedor.autocompletar(cmbpprove, listaproveedor);
       listaproductos =FXCollections.observableArrayList();
         listaproductoss =FXCollections.observableArrayList();
      // Producto.llenarInformacion(listaproductos);
          try {
            Producto1.llenarproducto(listaproductoss);
        } catch (SQLException ex) {
            Logger.getLogger(AddproductcotizacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
          Producto.autocompletar1(cmbppro, listaproductoss);
    }
      
   

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       con = new Conexion();
       con.establecerConexion();
       llename();
      jtfdescuento.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
        if (! isNowFocused) {
        int b,c;
        if(Integer.parseInt(jtfdescuento.getText())<=100 && Integer.parseInt(jtfdescuento.getText())>=0){
        b=Integer.parseInt(jtfdescuento.getText());
        c=Integer.parseInt(jtfvalor.getText());
        valorfinal=c-((b*c)/100); 
        labeldescuento.setText(""+valorfinal);
        }
       }
     });
//      labeldescuento.textProperty().addListener((obs, oldText, newText) -> 
//    jtfdescuento.setText(""+valorfinal));
    
}
   
}
    

