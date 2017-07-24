
package proyecto_empresa_ii;

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

    }
    @FXML
    void Mostrarcuentas(ActionEvent event) {
     Statement statement;
        try {
            statement = con.getConnection().createStatement();
            ResultSet res;
            res=statement.executeQuery("SELECT USER_USERNAME,"
                                             + "USER_PASS, "
                                             + "USER_NOMBRE,"
                                             + "USER_APELLIDO,"
                                             + "USER_FONO,USER_ROL "
                                             + "FROM user "
                                             + "where USER_USERNAME = '"+cbCuentaTipo.getValue()+"'");
    while (res.next()){  
        if(res.getInt("USER_ROL")== 1){
            tfMirarCuenta.setText(res.getString("USER_USERNAME")); 
            tfMirarContraseña.setText(res.getString("USER_PASS")); 
            tfMirarNombre.setText(res.getString("USER_NOMBRE"));
            tfMirarApellidos.setText(res.getString("USER_APELLIDO"));
            tfMirarTelefono.setText(res.getString("USER_FONO"));
            tfMirarTipo.setText("Usuario");   
        
        }else{
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

}
 


