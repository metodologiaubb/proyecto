/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodopro;


import bd.consultas;
import java.awt.Insets;
import java.awt.print.Book;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import modelos.Cotizacion;
import sesion.Sesion;


/**
 *
 * @author jerson,pascal
 */
public class cotizaController implements Initializable {
    
    //@FXML
    
    //variables de formulario tab 3
    @FXML private TextField tfnombre;
    @FXML private TextField tfapellido;
    @FXML private TextField tftelefono;
    @FXML private TextField tfcontrasena;

    
    //variables tabla 1 tab 1
    @FXML private TableView tablacot;
    @FXML private TableColumn IDcot;
    @FXML private TableColumn FechaCot;
    @FXML private TableColumn Nproducot;
    @FXML private TableColumn descripcot;
      @FXML private Button f5boton;
    
    private Text actionStatus;
    private ObservableList data;
    
 
    
    private void handleButtonAction(ActionEvent event) {
       
    }
    
    private MetodoPro application;

    
    
    public void setApp(MetodoPro application){
        this.application = application;
        tfnombre.setText(Sesion.CurrentUser.getUSER_NOMBRE());
        tfapellido.setText(Sesion.CurrentUser.getUSER_APELLIDO());
        tftelefono.setText(Sesion.CurrentUser.getFONO_USER());
            tfcontrasena.setText(Sesion.CurrentUser.getPASS_USER());
      
 llenartablacot();

        actionStatus = new Text();
        actionStatus.setFill(Color.FIREBRICK);
    }
    
    public void llenartablacot(){
        
          data = gettablainicial();
        tablacot.setItems(data);
    }
    

    
    //extraer datos de BD :D (tabla cotizacion, loco)
    ObservableList gettablainicial() { 
        List list = new ArrayList();
    
        
     String miau;
        try{
        ResultSet resultSet=consultas.Select("SELECT `ID_COT`,`FECHA_COT`,`DESCRIPCION` FROM `cotizacion`");     
    while(resultSet.next())
    {    Cotizacion papo= new Cotizacion();
       papo.setID_COT(resultSet.getInt("ID_COT"));    
       papo.setFECHA_COT(resultSet.getString("FECHA_COT"));
       papo.setDESCRIPCION(resultSet.getString("DESCRIPCION"));
        list.add(papo);}
        }
        catch(SQLException sqlException) {
        sqlException.printStackTrace();
        System.exit(1);}     
      ObservableList data = FXCollections.observableList(list);
        return data;
    }
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IDcot.setCellValueFactory(
        new PropertyValueFactory<Cotizacion,String>("ID_COT"));
       FechaCot.setCellValueFactory(                
        new PropertyValueFactory<Cotizacion,String>("FECHA_COT"));
      descripcot.setCellValueFactory(
        new PropertyValueFactory<Cotizacion,String>("DESCRIPCION"));
    
      
      
      
      
      
        
    }    
    
}
