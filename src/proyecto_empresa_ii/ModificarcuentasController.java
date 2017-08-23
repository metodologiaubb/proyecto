/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_empresa_ii;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import proyecto_empresa_ii.modelo.Conexion;
import proyecto_empresa_ii.modelo.User;
import proyecto_empresa_ii.modelo.consultas;
import sesion.Sesion;

/**
 * FXML Controller class
 *
 * @author Familia Alarcòn
 */
public class ModificarcuentasController implements Initializable {

    @FXML
    private Label texto;
    @FXML
    private JFXTextField tfMirarCuenta;

    @FXML
    private JFXTextField tfMirarContraseña;

    @FXML
    private JFXTextField tfMirarNombre;

    @FXML
    private JFXTextField tfMirarApellidos;

    @FXML
    private JFXTextField tfMirarTelefono;

    @FXML
    private ComboBox<User> cbCuentaTipo;
    private ObservableList<User> lista;
    
    @FXML
    private JFXTextField tfMirarTipo;

    @FXML
    private JFXTextField tfcuenta;

    @FXML
    private JFXTextField tfcontraseña;

    @FXML
    private JFXTextField tfnombre;

    @FXML
    private JFXTextField tfapellidos;

    @FXML
    private JFXTextField tftelefono;

    @FXML
    private JFXTextField tftipo;
    ObservableList<String> items = FXCollections.observableArrayList("Administrador", "Usuario");
    @FXML
    private ComboBox<String> cbCuenta;

    @FXML
    private JFXTextField tfMirarID1;

    @FXML
    private JFXButton confirmar;
    private Conexion con;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    con= new Conexion();
    con.establecerConexion();
    lista=FXCollections.observableArrayList();
    User.llenar_informacion(con.getConnection(), lista);
    cbCuentaTipo.setItems(lista);
    cbCuenta.setItems(items);
    confirmar.setVisible(false);
    }    

    @FXML
  void Mostrarcuentas(ActionEvent event) {
      Statement statement;
      
        try {
            
            statement = con.getConnection().createStatement();
            ResultSet res;
            res=statement.executeQuery("SELECT ID_USER,"
                                             +"USER_USERNAME,"
                                             + "USER_PASS, "
                                             + "USER_NOMBRE,"
                                             + "USER_APELLIDO,"
                                             + "USER_FONO,"
                                             + "USER_ROL "
                                             + "FROM user "
                                             + "where USER_USERNAME = '"+cbCuentaTipo.getValue()+"'");
    while (res.next()){  
        if(res.getInt("USER_ROL")== 1){
            tfMirarID1.setText(String.valueOf(res.getInt("ID_USER")));
            tfMirarCuenta.setText(res.getString("USER_USERNAME")); 
            tfMirarContraseña.setText(res.getString("USER_PASS")); 
            tfMirarNombre.setText(res.getString("USER_NOMBRE"));
            tfMirarApellidos.setText(res.getString("USER_APELLIDO"));
            tfMirarTelefono.setText(res.getString("USER_FONO"));
           
            tfMirarTipo.setText("Usuario");
        
        }else{
            tfMirarID1.setText(String.valueOf(res.getInt("ID_USER")));
            tfMirarCuenta.setText(res.getString("USER_USERNAME")); 
            tfMirarContraseña.setText(res.getString("USER_PASS")); 
            tfMirarNombre.setText(res.getString("USER_NOMBRE"));
            tfMirarApellidos.setText(res.getString("USER_APELLIDO"));
            tfMirarTelefono.setText(res.getString("USER_FONO"));
         
            tfMirarTipo.setText("Administrador");

        }
       } }catch (SQLException ex) {
            Logger.getLogger(ModificarcuentasController.class.getName()).log(Level.SEVERE, null, ex);
        }
      confirmar.setVisible(true);
      if(String.valueOf(Sesion.CurrentUser.getID_USER()).equals(tfMirarID1.getText()))
           { 
              cbCuenta.setVisible(false);
              texto.setVisible(false);
           }
    }

    @FXML
    private void confirmarcambios(ActionEvent event) {
       int x=5;
         if(cbCuenta.getValue()=="Administrador"&& tfcuenta.getText().length()!=0  && tfcontraseña.getText().length()!=0 && tfnombre.getText().length()!=0 && tfapellidos.getText().length()!=0 && tftelefono.getText().length()!=0){
          x=consultas.Insert("UPDATE `user` SET `USER_USERNAME` ='"+tfcuenta.getText()+"',`USER_PASS`='"+tfcontraseña.getText()+"',`USER_NOMBRE`='"+tfnombre.getText()+"',`USER_APELLIDO`='"+tfapellidos.getText()+"',`USER_FONO`='"+tftelefono.getText()+"',`USER_ROL`='"+2+"'WHERE ID_USER='"+tfMirarID1.getText()+"';");
         } 
         if(cbCuenta.getValue()=="Usuario" && tfcuenta.getText().length()!=0 && tfcontraseña.getText().length()!=0 && tfnombre.getText().length()!=0 && tfapellidos.getText().length()!=0 && tftelefono.getText().length()!=0){
          x=consultas.Insert("UPDATE `user` SET `USER_USERNAME` ='"+tfcuenta.getText()+"',`USER_PASS`='"+tfcontraseña.getText()+"',`USER_NOMBRE`='"+tfnombre.getText()+"',`USER_APELLIDO`='"+tfapellidos.getText()+"',`USER_FONO`='"+tftelefono.getText()+"',`USER_ROL`='"+1+"'WHERE ID_USER='"+tfMirarID1.getText()+"';");
         }
         if(String.valueOf(Sesion.CurrentUser.getID_USER()).equals(tfMirarID1.getText()))
           { 
          x=consultas.Insert("UPDATE `user` SET `USER_USERNAME` ='"+tfcuenta.getText()+"',`USER_PASS`='"+tfcontraseña.getText()+"',`USER_NOMBRE`='"+tfnombre.getText()+"',`USER_APELLIDO`='"+tfapellidos.getText()+"',`USER_FONO`='"+tftelefono.getText()+"',`USER_ROL`='"+2+"'WHERE ID_USER='"+tfMirarID1.getText()+"';");
 
           }
       //  x=consultas.Insert("UPDATE `user` SET `USER_USERNAME` ='"+tfcuenta.getText()+"',`USER_PASS`='"+tfcontraseña.getText()+"',`USER_NOMBRE`='"+tfnombre.getText()+"',`USER_APELLIDO`='"+tfapellidos.getText()+"',`USER_FONO`='"+tftelefono.getText()+"'WHERE ID_USER='"+tfMirarID1.getText()+"';");
       
       if(x==0)
       { 
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setHeaderText("");      
            alert2.setTitle("Datos cambiados");
            alert2.setContentText("Se han cambiado los datos ");
            Optional<ButtonType> result = alert2.showAndWait();
            lista=null;
            cbCuentaTipo.setValue(null);
            cbCuentaTipo.getItems().clear();
            lista=FXCollections.observableArrayList();
            User.llenar_informacion(con.getConnection(), lista);
            cbCuentaTipo.setItems(lista);
            tfcuenta.setText(null); 
            tfcontraseña.setText(null); 
            tfnombre.setText(null);
            tfapellidos.setText(null);
            tftelefono.setText(null);
            tfMirarTipo.setText(null);
            tfMirarCuenta.setText(null);
            tfMirarContraseña.setText(null);
            tfMirarNombre.setText(null);
            cbCuenta.setValue(null);
            tfMirarApellidos.setText(null);
            confirmar.setVisible(false);
            tfMirarTelefono.setText(null);
       }
            
    }
    
}


