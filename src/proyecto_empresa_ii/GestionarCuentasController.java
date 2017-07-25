
package proyecto_empresa_ii;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import proyecto_empresa_ii.modelo.Conexion;
import proyecto_empresa_ii.modelo.User;
import proyecto_empresa_ii.modelo.consultas;


/**
 * FXML Controller class
 *
 * @author Familia Alarcòn
 */
public class GestionarCuentasController implements Initializable {

    @FXML
    private JFXTextField tfIDcuenta;
    
    @FXML
    private JFXTextField tfMirarCuenta;

    @FXML
    private JFXTextField tfMirarContraseña;
    
    ObservableList<String> items = FXCollections.observableArrayList("Administrador", "Usuario");
    @FXML
    private ComboBox<String> cbcambiartipo;
    
    @FXML
    private JFXTextField tfMirarNombre;

    @FXML
    private JFXTextField tfMirarApellidos;

    @FXML
    private JFXTextField tfMirarTelefono;

    @FXML
    private JFXTextField tfMirarTipo;

    @FXML
    private ComboBox<User> cbCuentaTipo;
    
    private ObservableList<User> lista;
    
    @FXML
    private JFXButton btnEliminarCuenta;

    @FXML
    private JFXButton btnModificarCuenta;
    
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
    cbcambiartipo.setItems(items);
    int x;

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
            tfIDcuenta.setText(String.valueOf(res.getInt("ID_USER")));
            tfMirarCuenta.setText(res.getString("USER_USERNAME")); 
            tfMirarContraseña.setText(res.getString("USER_PASS")); 
            tfMirarNombre.setText(res.getString("USER_NOMBRE"));
            tfMirarApellidos.setText(res.getString("USER_APELLIDO"));
            tfMirarTelefono.setText(res.getString("USER_FONO"));
            tfMirarTipo.setText("Usuario");
             cbcambiartipo.setValue(null);
        
        }else{
            tfIDcuenta.setText(String.valueOf(res.getInt("ID_USER")));
            tfMirarCuenta.setText(res.getString("USER_USERNAME")); 
            tfMirarContraseña.setText(res.getString("USER_PASS")); 
            tfMirarNombre.setText(res.getString("USER_NOMBRE"));
            tfMirarApellidos.setText(res.getString("USER_APELLIDO"));
            tfMirarTelefono.setText(res.getString("USER_FONO"));
            tfMirarTipo.setText("Administrador");
            cbcambiartipo.setValue(null);
        }
       } }catch (SQLException ex) {
            Logger.getLogger(GestionarCuentasController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    @FXML
    void EliminarCuentas(ActionEvent event){
      int x;
      x=consultas.Insert("DELETE  "
                       + "FROM user "
                       + "WHERE ID_USER ='"+tfIDcuenta.getText()+"'");   
     if(x==0){
            tfIDcuenta.setText(null);
            tfMirarCuenta.setText(null); 
            tfMirarContraseña.setText(null); 
            tfMirarNombre.setText(null);
            tfMirarApellidos.setText(null);
            tfMirarTelefono.setText(null);
            tfMirarTipo.setText(null);
            lista=null;
            cbCuentaTipo.setValue(null);
            cbCuentaTipo.getItems().clear();
            lista=FXCollections.observableArrayList();
            User.llenar_informacion(con.getConnection(), lista);
            cbCuentaTipo.setItems(lista);
            cbcambiartipo.setValue(null);
    }
    
    } 

  @FXML
   void ModificarCuentas(ActionEvent event) {
         int x=5;
         if(cbcambiartipo.getValue() == "Administrador"){
          x=consultas.Insert("UPDATE `user` SET `USER_USERNAME` ='"+tfMirarCuenta.getText()+"',`USER_PASS`='"+tfMirarContraseña.getText()+"',`USER_NOMBRE`='"+tfMirarNombre.getText()+"',`USER_APELLIDO`='"+tfMirarApellidos.getText()+"',`USER_FONO`='"+tfMirarTelefono.getText()+"',`USER_ROL`='"+2+"'WHERE ID_USER='"+tfIDcuenta.getText()+"';");
         } 
          if(cbcambiartipo.getValue() == "Usuario"){
          x=consultas.Insert("UPDATE `user` SET `USER_USERNAME` ='"+tfMirarCuenta.getText()+"',`USER_PASS`='"+tfMirarContraseña.getText()+"',`USER_NOMBRE`='"+tfMirarNombre.getText()+"',`USER_APELLIDO`='"+tfMirarApellidos.getText()+"',`USER_FONO`='"+tfMirarTelefono.getText()+"',`USER_ROL`='"+1+"'WHERE ID_USER='"+tfIDcuenta.getText()+"';");
         }
         
         x=consultas.Insert("UPDATE `user` SET `USER_USERNAME` ='"+tfMirarCuenta.getText()+"',`USER_PASS`='"+tfMirarContraseña.getText()+"',`USER_NOMBRE`='"+tfMirarNombre.getText()+"',`USER_APELLIDO`='"+tfMirarApellidos.getText()+"',`USER_FONO`='"+tfMirarTelefono.getText()+"'WHERE ID_USER='"+tfIDcuenta.getText()+"';");
      if(x==0){
            lista=null;
            cbCuentaTipo.setValue(null);
            cbCuentaTipo.getItems().clear();
            lista=FXCollections.observableArrayList();
            User.llenar_informacion(con.getConnection(), lista);
            cbCuentaTipo.setItems(lista);
            tfMirarCuenta.setText(null); 
            tfMirarContraseña.setText(null); 
            tfMirarNombre.setText(null);
            tfMirarApellidos.setText(null);
            tfMirarTelefono.setText(null);
            tfMirarTipo.setText(null);
            cbcambiartipo.setValue(null);
            
      }
    }
       @FXML
    void cambiar(ActionEvent event) {
       tfMirarTipo.setText(cbcambiartipo.getValue());
    }

}

