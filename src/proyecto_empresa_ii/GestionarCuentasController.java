
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
import proyecto_empresa_ii.modelo.Conexion;
import proyecto_empresa_ii.modelo.User;
import proyecto_empresa_ii.modelo.consultas;
import sesion.Sesion;



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
 
    @FXML
    private JFXTextField tfMirarNombre;

    @FXML
    private JFXTextField tfMirarApellidos;

    @FXML
    private JFXTextField tfMirarTelefono;

    private JFXTextField tfMirarTipo;

    @FXML
    private ComboBox<User> cbCuentaTipo;
    
    private ObservableList<User> lista;
    
    @FXML
    private JFXButton btnEliminarCuenta;
    
   private Conexion con;
    @FXML
    private JFXButton btnGestionarCuenta;
    
     ObservableList<String> items = FXCollections.observableArrayList("Administrador", "Usuario");
    @FXML
    private ComboBox<String> cbCuentaTipo1;

     
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
    cbCuentaTipo1.setItems(items);
    int x;
           cbCuentaTipo1.setVisible(false);
          btnEliminarCuenta.setVisible(false);
          btnGestionarCuenta.setVisible(false);

      

    }
    @FXML
    void Mostrarcuentas(ActionEvent event) {
     Statement statement;
     String m,n;
     btnEliminarCuenta.setVisible(true);
      btnGestionarCuenta.setVisible(true);
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
                                             + "WHERE USER_USERNAME = '"+cbCuentaTipo.getValue()+"'");
    while (res.next()){  
        if(res.getInt("USER_ROL")== 1){
            tfIDcuenta.setText(String.valueOf(res.getInt("ID_USER")));
            tfMirarCuenta.setText(res.getString("USER_USERNAME")); 
            tfMirarContraseña.setText(res.getString("USER_PASS")); 
            tfMirarNombre.setText(res.getString("USER_NOMBRE"));
            tfMirarApellidos.setText(res.getString("USER_APELLIDO"));
            tfMirarTelefono.setText(res.getString("USER_FONO"));
            cbCuentaTipo1.setVisible(true);
            cbCuentaTipo1.setValue("Usuario");
                 cbCuentaTipo1.setDisable(false);
      

        }else{
            tfIDcuenta.setText(String.valueOf(res.getInt("ID_USER")));
            tfMirarCuenta.setText(res.getString("USER_USERNAME")); 
            tfMirarContraseña.setText(res.getString("USER_PASS")); 
            tfMirarNombre.setText(res.getString("USER_NOMBRE"));
            tfMirarApellidos.setText(res.getString("USER_APELLIDO"));
            tfMirarTelefono.setText(res.getString("USER_FONO"));
            cbCuentaTipo1.setVisible(true);
            cbCuentaTipo1.setValue("Administrador");
                 cbCuentaTipo1.setDisable(false);
         
            if(String.valueOf(Sesion.CurrentUser.getID_USER()).equals(tfIDcuenta.getText())){
                    btnEliminarCuenta.setVisible(false);
                    cbCuentaTipo1.setDisable(true);
            }

        }
       } }catch (SQLException ex) {
            Logger.getLogger(GestionarCuentasController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    
    @FXML
    void EliminarCuentas(ActionEvent event){ 
      int x;
      x=consultas.Insert("UPDATE `user` SET "
              + "`HABILITADO`=0 "
              + "WHERE ID_USER="+tfIDcuenta.getText()+";");
                          
     if(x==0){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setHeaderText("");      
             alert.setTitle("Datos Eliminados");
             alert.setContentText("La cuenta: "+tfMirarCuenta.getText()+" Se ha eliminado");
             Optional<ButtonType> result = alert.showAndWait();
             tfIDcuenta.setText(null);
             tfMirarCuenta.setText(null); 
             tfMirarContraseña.setText(null); 
             tfMirarNombre.setText(null);
             tfMirarApellidos.setText(null);
             tfMirarTelefono.setText(null);
             lista=null;
             cbCuentaTipo.setValue(null);
             cbCuentaTipo.getItems().clear();
             lista=FXCollections.observableArrayList();
             User.llenar_informacion(con.getConnection(), lista);
             cbCuentaTipo.setItems(lista);
             btnEliminarCuenta.setVisible(false);
             btnGestionarCuenta.setVisible(false);
             }
        }

    @FXML
    private void editarcuentas(ActionEvent event) {

               int x=5;
  if(cbCuentaTipo1.getValue()=="Administrador"&& tfMirarCuenta.getText().length()!=0  && tfMirarContraseña.getText().length()!=0 && tfMirarNombre.getText().length()!=0 && tfMirarApellidos.getText().length()!=0 && tfMirarTelefono.getText().length()!=0){
          x=consultas.Insert("UPDATE `user` SET `USER_USERNAME` ='"+tfMirarCuenta.getText()+"',`USER_PASS`='"+tfMirarContraseña.getText()+"',`USER_NOMBRE`='"+tfMirarNombre.getText()+"',`USER_APELLIDO`='"+tfMirarApellidos.getText()+"',`USER_FONO`='"+tfMirarTelefono.getText()+"',`USER_ROL`='"+2+"'WHERE ID_USER='"+tfIDcuenta.getText()+"';");
         } 
    if(cbCuentaTipo1.getValue()=="Usuario" && tfMirarCuenta.getText().length()!=0  && tfMirarContraseña.getText().length()!=0 && tfMirarNombre.getText().length()!=0 && tfMirarApellidos.getText().length()!=0 && tfMirarTelefono.getText().length()!=0){
          x=consultas.Insert("UPDATE `user` SET `USER_USERNAME` ='"+tfMirarCuenta.getText()+"',`USER_PASS`='"+tfMirarContraseña.getText()+"',`USER_NOMBRE`='"+tfMirarNombre.getText()+"',`USER_APELLIDO`='"+tfMirarApellidos.getText()+"',`USER_FONO`='"+tfMirarTelefono.getText()+"',`USER_ROL`='"+1+"'WHERE ID_USER='"+tfIDcuenta.getText()+"';");
         } 
  if(String.valueOf(Sesion.CurrentUser.getID_USER()).equals(tfIDcuenta.getText()))
           { 
          x=consultas.Insert("UPDATE `user` SET `USER_USERNAME` ='"+tfMirarCuenta.getText()+"',`USER_PASS`='"+tfMirarContraseña.getText()+"',`USER_NOMBRE`='"+tfMirarNombre.getText()+"',`USER_APELLIDO`='"+tfMirarApellidos.getText()+"',`USER_FONO`='"+tfMirarTelefono.getText()+"',`USER_ROL`='"+2+"'WHERE ID_USER='"+tfIDcuenta.getText()+"';");

    }
  if(x==0){
    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setHeaderText("");      
            alert2.setTitle("Datos modificados");
            alert2.setContentText("Se han modificado los datos de "+tfMirarCuenta.getText()+"");
             Optional<ButtonType> result = alert2.showAndWait();
    }
    }}

  
  



