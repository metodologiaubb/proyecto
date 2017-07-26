
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

    @FXML
    private JFXTextField tfMirarTipo;

    @FXML
    private ComboBox<User> cbCuentaTipo;
    
    private ObservableList<User> lista;
    
    @FXML
    private JFXButton btnEliminarCuenta;
    
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
    int x;
 
          btnEliminarCuenta.setVisible(false);

      

    }
    @FXML
    void Mostrarcuentas(ActionEvent event) {
     Statement statement;
     String m,n;
     btnEliminarCuenta.setVisible(true);
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

        
        }else{
            tfIDcuenta.setText(String.valueOf(res.getInt("ID_USER")));
            tfMirarCuenta.setText(res.getString("USER_USERNAME")); 
            tfMirarContraseña.setText(res.getString("USER_PASS")); 
            tfMirarNombre.setText(res.getString("USER_NOMBRE"));
            tfMirarApellidos.setText(res.getString("USER_APELLIDO"));
            tfMirarTelefono.setText(res.getString("USER_FONO"));
            tfMirarTipo.setText("Administrador");

        }
       } }catch (SQLException ex) {
            Logger.getLogger(GestionarCuentasController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    
    @FXML
    void EliminarCuentas(ActionEvent event){ 
      int x;
      x=consultas.Insert("DELETE  "
                       + "FROM user Join cotizacion "
                       + "WHERE ID_USER ='"+tfIDcuenta.getText()+"'");   
     if(x==0){
             if(String.valueOf(Sesion.CurrentUser.getID_USER()).equals(tfIDcuenta.getText())){
             Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
             alert2.setHeaderText("");      
             alert2.setTitle("Datos cambiados");
             alert2.setContentText("Se han Eliminado los datos de la cuenta que estaba usando, el programa se cerrara");
             Optional<ButtonType> result2 = alert2.showAndWait();
             if (result2.get() == ButtonType.OK){
             System.exit(0);
             }if (result2.get() == ButtonType.CLOSE){
                 System.exit(0);  
             }
             if (result2.get() == ButtonType.NO){
                 System.exit(0);  
             }
          }else{
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
             tfMirarTipo.setText(null);
             lista=null;
             cbCuentaTipo.setValue(null);
             cbCuentaTipo.getItems().clear();
             lista=FXCollections.observableArrayList();
             User.llenar_informacion(con.getConnection(), lista);
             cbCuentaTipo.setItems(lista);

              btnEliminarCuenta.setVisible(false);

             }
        }
     
    }

  
  

}

